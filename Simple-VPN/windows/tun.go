package main

import (
	"encoding/binary"
	"fmt"
	"net"
	"os/exec"

	"golang.zx2c4.com/wintun"
)

func CreateTun(name string, ip string, netmask string) (*wintun.Adapter, wintun.Session, error) {
	adapter, err := wintun.CreateAdapter(name, name+" Tunnel", nil)
	if err != nil {
		return nil, wintun.Session{}, fmt.Errorf("failed to create adapter: %w", err)
	}

	session, err := adapter.StartSession(0x200000)
	if err != nil {
		adapter.Close()
		return nil, wintun.Session{}, fmt.Errorf("failed to start session: %w", err)
	}

	if err := configureIP(name, ip, netmask); err != nil {
		session.End()
		adapter.Close()
		return nil, wintun.Session{}, fmt.Errorf("failed to configure IP: %w", err)
	}

	return adapter, session, nil
}

func configureIP(adapterName, ip, netmask string) error {
	cmd := exec.Command("netsh", "interface", "ip", "set", "address",
		"name="+adapterName, "static", ip, netmask)
	if err := cmd.Run(); err != nil {
		return fmt.Errorf("netsh command failed: %w", err)
	}
	return nil
}

func ReadPacket(session wintun.Session) ([]byte, error) {
	packet, err := session.ReceivePacket()
	if err != nil {
		return nil, err
	}

	data := make([]byte, len(packet))
	copy(data, packet)
	session.ReleaseReceivePacket(packet)

	decrypted:=XOREncryption(data, 42)

	return decrypted, nil
}

func WritePacket(session wintun.Session, data []byte) error {
	encrypted := XOREncryption(data, 42)

	// uncomment this line to print encrypted data
	// fmt.Printf("Encrypted data: %v\n", encrypted)

	packet, err := session.AllocateSendPacket(len(encrypted))
	if err != nil {
		return err
	}

	copy(packet, encrypted)
	session.SendPacket(packet)
	return nil
}

func SendFramedPacket(conn net.Conn, data []byte) error {
	length := make([]byte, 4)
	binary.BigEndian.PutUint32(length, uint32(len(data)))
	if _, err := conn.Write(length); err != nil {
		return err
	}
	if _, err := conn.Write(data); err != nil {
		return err
	}
	return nil
}

func ReceiveFramedPacket(conn net.Conn) ([]byte, error) {
	length := make([]byte, 4)
	if _, err := conn.Read(length); err != nil {
		return nil, err
	}
	
	packetLen := binary.BigEndian.Uint32(length)
	if packetLen > 65535 {
		return nil, fmt.Errorf("packet too large: %d", packetLen)
	}

	data := make([]byte, packetLen)
	totalRead := 0
	for totalRead < int(packetLen) {
		n, err := conn.Read(data[totalRead:])
		if err != nil {
			return nil, err
		}
		totalRead += n
	}
	
	return data, nil
}

func XOREncryption(data []byte, key byte) []byte {
	out := make([]byte, len(data))
	for i := 0; i < len(data); i++ {
		out[i] = data[i] ^ key
	}
	return out
}

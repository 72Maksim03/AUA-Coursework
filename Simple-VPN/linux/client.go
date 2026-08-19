package main

import (
	"encoding/binary"
	"fmt"
	"io"
	"log"
	"net"
)

func main() {
	fmt.Println("Starting VPN client")

	ifce, err := CreateTun("VPNClient")
	if err != nil {
		log.Fatal("Failed to create TUN adapter:", err)
	}
	defer ifce.Close()

	ConfigureTun(ifce.Name(), "10.10.0.2")

	conn, err := net.Dial("tcp", "192.168.5.6:9999") // server IP
	if err != nil {
		log.Fatal("Failed to connect to server:", err)
	}
	defer conn.Close()

	fmt.Println("Connected to VPN server")

	go func() {
		for {
			packet := ReadPacket(ifce)
			if packet == nil {
				continue
			}
			if err := SendFramedPacket(conn, packet); err != nil {
				log.Println("Error sending to server:", err)
				return
			}
		}
	}()

	for {
		packet, err := ReceiveFramedPacket(conn)
		if err != nil {
			log.Println("Connection closed:", err)
			return
		}
		WritePacket(ifce, packet)
	}
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
	lengthBuf := make([]byte, 4)
	if _, err := io.ReadFull(conn, lengthBuf); err != nil {
		return nil, err
	}

	packetLen := binary.BigEndian.Uint32(lengthBuf)
	if packetLen > 65535 {
		return nil, fmt.Errorf("packet too large: %d", packetLen)
	}

	data := make([]byte, packetLen)
	_, err := io.ReadFull(conn, data)
	if err != nil {
		return nil, err
	}
	return data, nil
}

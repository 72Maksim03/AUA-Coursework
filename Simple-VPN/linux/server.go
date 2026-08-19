package main

import (
	"encoding/binary"
	"fmt"
	"io"
	"log"
	"net"

	"github.com/songgao/water"
)

const MaxPacketSize = 0x200000

func main() {
	fmt.Println("Starting VPN server")

	ifce, err := CreateTun("VPNServer")
	if err != nil {
		log.Fatal("Failed to create TUN adapter:", err)
	}
	defer ifce.Close()

	ConfigureTun(ifce.Name(), "10.10.0.1")

	ln, err := net.Listen("tcp", ":9999")
	if err != nil {
		log.Fatal("Failed to listen on port 9999:", err)
	}
	fmt.Println("Server listening on port 9999")

	for {
		conn, err := ln.Accept()
		if err != nil {
			log.Println("Failed to accept connection:", err)
			continue
		}
		fmt.Println("New client connected:", conn.RemoteAddr())
		go handleClient(conn, ifce)
	}
}

func handleClient(conn net.Conn, ifce *water.Interface) {
	defer conn.Close()

	go func() {
		for {
			packet := ReadPacket(ifce)
			if packet == nil {
				continue
			}
			if err := SendFramedPacket(conn, packet); err != nil {
				log.Println("Error sending packet to client:", err)
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
	if packetLen > MaxPacketSize {
		return nil, fmt.Errorf("packet too large: %d", packetLen)
	}

	data := make([]byte, packetLen)
	_, err := io.ReadFull(conn, data)
	if err != nil {
		return nil, err
	}
	return data, nil
}

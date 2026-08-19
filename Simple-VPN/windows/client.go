package main

import (
	"fmt"
	"log"
	"net"
)

func main() {
	fmt.Println("Starting VPN client")

	adapter, session, err := CreateTun("VPNClient", "10.10.0.2", "255.255.255.0")
	if err != nil {
		log.Fatal("Failed to create TUN adapter: ", err)
	}
	defer adapter.Close()
	defer session.End()

	conn, err := net.Dial("tcp", "192.168.5.6:9999") // server IP
	if err != nil {
		log.Fatal("Failed to connect to server: ", err)
	}
	defer conn.Close()

	fmt.Println("Connected to VPN server")

	go func() {
		for {
			packet, err := ReadPacket(session)
			if err != nil {
				continue
			}
			if err := SendFramedPacket(conn, packet); err != nil {
				log.Println("Error sending to server: ", err)
				return
			}
		}
	}()

	for {
		packet, err := ReceiveFramedPacket(conn)
		if err != nil {
			log.Println("Connection closed: ", err)
			return
		}
		if err := WritePacket(session, packet); err != nil {
			log.Println("Error writing to TUN: ", err)
			return
		}
	}
}

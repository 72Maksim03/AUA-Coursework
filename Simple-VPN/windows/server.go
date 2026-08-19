package main

import (
	"fmt"
	"log"
	"net"

	"golang.zx2c4.com/wintun"
)

func main() {
	fmt.Println("Starting VPN server")

	adapter, session, err := CreateTun("VPNServer", "10.10.0.1", "255.255.255.0")
	if err != nil {
		log.Fatal("Failed to create TUN adapter: ", err)
	}
	defer adapter.Close()
	defer session.End()

	ln, err := net.Listen("tcp", ":9999")
	if err != nil {
		log.Fatal("Failed to start server: ", err)
	}
	defer ln.Close()

	fmt.Println("Server listening on port 9999")

	for {
		conn, err := ln.Accept()
		if err != nil {
			log.Println("Failed to accept connection: ", err)
			continue
		}
		fmt.Println("New client connected: ", conn.RemoteAddr())
		go handleClient(conn, session)
	}
}

func handleClient(conn net.Conn, session wintun.Session) {
	defer conn.Close()

	go func() {
		for {
			packet, err := ReadPacket(session)
			if err != nil {
				continue
			}
			if err := SendFramedPacket(conn, packet); err != nil {
				log.Println("Error sending to client: ", err)
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

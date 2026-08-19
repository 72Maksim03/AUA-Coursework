package main

import(
	"log"
	"os/exec"

	"github.com/songgao/water"
)

func CreateTun(name string) (*water.Interface, error){
	config := water.Config{
		DeviceType: water.TUN,
	}
	config.Name=name

	ifce, err := water.New(config)
	if err != nil {
		return nil, err
	}
	return ifce, nil
}

func XOREncryption(data []byte, key byte)[]byte{
	out := make([]byte, len(data))
	for i := range len(data){
		out[i] = data[i] ^ key
	}
	return out
}

func ReadPacket(ifce *water.Interface) []byte {
	buf := make([]byte, 65535)
	n, err := ifce.Read(buf)
	if err != nil {
		return nil
	}
	decrypted := XOREncryption(buf[:n], 42)
	return decrypted
}

func WritePacket(ifce *water.Interface, data []byte){
	encrypted := XOREncryption(data, 42)
	// uncomment this line to print encrypted data
	// log.Printf("Encrypted data: ", encrypted)
	_, err := ifce.Write(encrypted)
	if err != nil {
		log.Println("Error writing packet: ", err)
	}
}

func ConfigureTun(ifaceName, ip string){
	cmd := exec.Command("ip", "link", "set", "dev", ifaceName, "up")
	if err := cmd.Run(); err != nil {
		log.Fatal("Failed to bring interface up: ", err)
	}

	cmd = exec.Command("ip", "addr", "add", ip+"/24", "dev", ifaceName)
	if err := cmd.Run(); err != nil{
		log.Fatal("Failed to assign IP address: ", err)
	}
	log.Println("Configured interface", ifaceName, "with IP", ip)
}
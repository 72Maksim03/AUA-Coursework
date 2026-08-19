# Simple VPN (Linux & Windows)

This project contains a minimal VPN implementation using TUN interfaces.  
There are two separate versions of the code:

- `linux/` – Go VPN server/client for Linux using TUN
- `windows/` – Go VPN server/client for Windows using TUN (Wintun)

Each version contains everything needed to build and run on its platform.

---

# How to Build and Use

Before building the project, you need to set the correct **server IP and port** inside the code.  
To find your local IP:

- Windows: `ipconfig`
- Linux: `ip a`

---

## Windows

### Build (in the `windows/` folder)
```
go build -o server.exe server.go tun.go
go build -o client.exe client.go tun.go
```

# Run (as Administrator)
.\server.exe
.\client.exe

---

## Linux

### Build (you can also use `go build` instead)
```
sudo go run server.go tun.go
sudo go run client.go tun.go
```
Or you can build binaries:
```
go build -o server server.go tun.go
go build -o client client.go tun.go
```
### Run (requires sudo for TUN)
```
sudo ./server
sudo ./client
```
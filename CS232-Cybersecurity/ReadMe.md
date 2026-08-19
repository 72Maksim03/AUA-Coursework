# BadUSB Behavioral Detection

A small cybersecurity project that demonstrates **BadUSB/HID injection detection through behavioral monitoring**.

The project consists of two components:

1. A simulated BadUSB payload implemented using Arduino/DigiKeyboard.
2. A Python-based host detector that monitors keyboard activity, processes, command lines, and temporary files for behavioral indicators associated with automated HID attacks.

The project was developed as part of **CS232 — Cybersecurity** at the American University of Armenia.

## Architecture

```text
                 ┌──────────────────────┐
                 │   USB HID Payload    │
                 │   DigiKeyboard       │
                 └──────────┬───────────┘
                            │
                            ▼
                  Automated keystrokes
                            │
                            ▼
                 ┌──────────────────────┐
                 │      Windows Host    │
                 │                      │
                 │  cmd.exe             │
                 │  Recon commands      │
                 │  Temporary files     │
                 └──────────┬───────────┘
                            │
                            ▼
                 ┌──────────────────────┐
                 │   BadUSB Detector    │
                 │      Detector.py     │
                 └──────────┬───────────┘
                            │
                            ▼
                 Behavioral Correlation
                            │
                            ▼
                         Alert
```

## Components

### 1. USB HID Attack Simulation

`hid_attack_simulation.ino` implements a simple HID payload using the **DigiKeyboard** library.

The payload:

1. Waits for the Windows host to initialize.
2. Opens the Windows Run dialog.
3. Starts `cmd.exe`.
4. Changes to the temporary directory.
5. Executes basic system reconnaissance commands.
6. Writes the collected information to `info.txt`.
7. Exits the command shell.

The simulated commands include:

```text
whoami
hostname
ipconfig
```

The payload is intended for **controlled laboratory environments** to generate observable behavior for the detection component.

### 2. BadUSB Detector

`Detector.py` is a Python-based behavioral monitoring tool.

It uses:

- `pynput` for keyboard activity monitoring
- `psutil` for process monitoring
- Python `threading` for concurrent monitoring
- Python `logging` for event and alert logging

## Detection Logic

The detector monitors several behavioral indicators.

### High Keystroke Rate

Automated HID devices can type significantly faster and more consistently than a human.

The detector tracks recent keystrokes and generates a signal when the average interval between keystrokes falls below the configured threshold.

```python
KEYSTROKE_RATE_THRESHOLD_MS = 35
```

### Process Creation

The detector monitors newly created processes and records:

- process name
- command line
- parent process
- parent command line

This information is used to identify suspicious process execution chains.

### Reconnaissance Commands

The detector searches process and parent-process command lines for commands commonly associated with system reconnaissance:

```text
whoami
hostname
ipconfig
systeminfo
net user
net localgroup
reg query
wmic
```

### Download / Exfiltration Indicators

The detector also looks for commands associated with downloading or transferring data:

```text
curl
wget
invoke-webrequest
downloadstring
bitsadmin
```

### Suspicious Shell Parents

Unexpected relationships between command shells and their parent processes can indicate suspicious execution.

The detector checks whether newly created `cmd.exe` or `powershell.exe` processes originate from a predefined set of trusted parent processes.

### Temporary File Creation

The detector monitors the Windows temporary directory for newly created files with extensions commonly associated with scripts or collected text data:

```text
.txt
.ps1
.bat
.vbs
```

## Alert Correlation

Individual indicators do not automatically generate an alert.

Instead, detected signals are correlated over a **10-second window**.

Each signal receives a score:

| Indicator                     | Score |
| ------------------------------ | ----: |
| High keystroke rate           |     3 |
| Shell from untrusted parent   |     2 |
| Download/exfiltration command |     2 |
| Reconnaissance command        |     1 |
| Suspicious temporary file     |     1 |
| Output redirected to file     |     1 |

An alert is generated when the accumulated score reaches:

```text
ALERT_THRESHOLD = 3
```

This reduces reliance on a single indicator and allows multiple weak signals to be combined into a stronger detection.

## Requirements

### Detector

Python 3.x with:

```bash
pip install psutil pynput
```

### USB Payload

The payload requires:

- a compatible Arduino/DigiKeyboard device
- Arduino IDE
- DigiKeyboard library
- a controlled Windows test environment

## Running the Detector

Start the detector before connecting or executing the test payload:

```bash
python Detector.py
```

The detector records events in:

```text
hid_monitor.log
```

Alerts are written to the console and log file.

## Example Detection Flow

A simulated HID attack may produce a sequence similar to:

```text
High keystroke rate
        +
cmd.exe execution
        +
whoami
        +
hostname
        +
ipconfig
        +
info.txt created in %TEMP%
        ↓
Behavioral score exceeds threshold
        ↓
ALERT
```

## Limitations

This project is a lightweight proof of concept rather than a production endpoint detection system.

Potential limitations include:

- legitimate automation may trigger high-keystroke-rate detection;
- command-string matching can produce false positives;
- trusted parent-process lists are static;
- process polling may miss very short-lived processes;
- the detector does not directly identify USB HID device identity;
- the detection logic is specific to Windows behavior;
- sophisticated HID attacks may avoid the monitored indicators.

A production implementation would benefit from Windows-native telemetry, device-level monitoring, ETW/Sysmon telemetry, stronger process lineage analysis, and more robust behavioral correlation.

## Security Context

The project demonstrates a simplified defensive approach to **BadUSB/HID injection detection**.

Rather than attempting to identify a malicious USB device solely from its hardware identity, the detector focuses on the **behavior produced after HID injection**.

This approach allows multiple host-level indicators to be correlated into a single detection signal.

## Academic Context

Developed as part of **CS232 — Cybersecurity** at the **American University of Armenia**.

## Author

**Maksim Petrosyan**

[GitHub](https://github.com/72Maksim03)

import time
import threading
import logging
import psutil
import collections
from datetime import datetime
from pathlib import Path
from pynput import keyboard as kb
import os

logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s [%(levelname)s] %(message)s',
    handlers=[
        logging.FileHandler("hid_monitor.log"),
        logging.StreamHandler()
    ]
)

ALERT_THRESHOLD = 3

KEYSTROKE_RATE_THRESHOLD_MS = 35

RECON_COMMANDS = [
    "whoami", "hostname", "ipconfig",
    "systeminfo", "net user", "net localgroup",
    "reg query", "wmic",
]

EXFIL_COMMANDS = [
    "curl", "wget", "invoke-webrequest",
    "downloadstring", "bitsadmin",
]

TRUSTED_PARENTS = {
    "explorer.exe", "code.exe", "windowsterminal.exe",
    "openconsole.exe", "devenv.exe", "idea64.exe",
    "pycharm64.exe", "svchost.exe", "services.exe",
    "taskhostw.exe", "msiexec.exe",
}

IGNORED_PROCESSES = {
    "conhost.exe", "smartscreen.exe", "wermgr.exe",
    "backgroundtaskhost.exe", "runtimebroker.exe",
    "searchprotocolhost.exe", "searchfilterhost.exe",
}


class BadUSBDetector:
    def __init__(self):
        self.recent_events = []
        self.correlation_window = 10
        self.lock = threading.Lock()
        self.seen_pids = set()

        self.key_times = collections.deque(maxlen=20)
        self.key_lock = threading.Lock()

    def record_event(self, signal: str, score: int):
        now = time.time()
        with self.lock:
            self.recent_events.append((now, signal, score))
            self.recent_events = [
                e for e in self.recent_events
                if now - e[0] <= self.correlation_window
            ]
            total_score = sum(e[2] for e in self.recent_events)
            signals = [e[1] for e in self.recent_events]

        if total_score >= ALERT_THRESHOLD:
            self.alert(f"Score={total_score} | Signals: {', '.join(signals)}")
            with self.lock:
                self.recent_events.clear()

    def on_key_press(self, key):
        now = time.time() * 1000

        with self.key_lock:
            self.key_times.append(now)

            if len(self.key_times) < 10:
                return

            window = list(self.key_times)

            avg_interval = (window[-1] - window[0]) / len(window)

            if avg_interval < KEYSTROKE_RATE_THRESHOLD_MS:
                self.record_event(
                    f"high_keystroke_rate({avg_interval:.1f}ms/key)",
                    score=3
                )

                self.key_times.clear()

    def start_keystroke_monitor(self):
        logging.info(f"Keystroke monitor started (threshold: {KEYSTROKE_RATE_THRESHOLD_MS}ms/key)")
        listener = kb.Listener(on_press=self.on_key_press)
        listener.daemon = True
        listener.start()

    def monitor_processes(self):
        with self.lock:
            for proc in psutil.process_iter(['pid']):
                self.seen_pids.add(proc.info['pid'])
        logging.info(f"Ignoring {len(self.seen_pids)} existing processes at startup.")

        while True:
            time.sleep(0.5)
            try:
                for proc in psutil.process_iter(['pid', 'name', 'ppid', 'cmdline']):
                    pid = proc.info['pid']
                    if pid in self.seen_pids:
                        continue

                    with self.lock:
                        self.seen_pids.add(pid)

                    name = (proc.info['name'] or "").lower()
                    cmdline = " ".join(proc.info['cmdline'] or []).lower()

                    if name in IGNORED_PROCESSES:
                        continue

                    try:
                        parent = psutil.Process(proc.info['ppid'])
                        parent_name = parent.name().lower()
                        parent_cmdline = " ".join(parent.cmdline() or []).lower()
                    except Exception:
                        parent_name = "unknown"
                        parent_cmdline = ""

                    full_context = f"{cmdline} {parent_cmdline}"

                    logging.info(
                        f"NEW PROCESS: {name} | "
                        f"CMD: {cmdline[:80]} | "
                        f"PARENT: {parent_name} | "
                        f"PARENT_CMD: {parent_cmdline[:80]}"
                    )

                    recon_hits = [t for t in RECON_COMMANDS if t in full_context]
                    for hit in recon_hits:
                        self.record_event(f"recon_cmd({hit})", score=1)

                    for pattern in EXFIL_COMMANDS:
                        if pattern in full_context:
                            self.record_event(f"exfil_cmd({pattern})", score=2)

                    if name in {"cmd.exe", "powershell.exe"}:
                        if parent_name not in TRUSTED_PARENTS:
                            self.record_event(
                                f"shell_from_untrusted_parent({parent_name})",
                                score=2
                            )

                    if ">>" in full_context and any(
                        ext in full_context for ext in [".txt", ".log", ".csv"]
                    ):
                        self.record_event("output_redirected_to_file", score=1)

            except Exception as e:
                logging.error(f"Process monitor error: {e}")

    def monitor_temp_files(self):
        temp_dir = Path(os.environ.get("TEMP", "C:\\Windows\\Temp"))
        try:
            known_files = set(temp_dir.iterdir())
        except Exception:
            known_files = set()

        logging.info(f"Watching temp folder: {temp_dir}")

        while True:
            time.sleep(1)
            try:
                current_files = set(temp_dir.iterdir())
                new_files = current_files - known_files
                for f in new_files:
                    if f.suffix in (".txt", ".ps1", ".bat", ".vbs"):
                        logging.info(f"New file in temp: {f.name}")
                        self.record_event(f"temp_file_created({f.name})", score=1)
                known_files = current_files
            except Exception as e:
                logging.error(f"Temp monitor error: {e}")

    def alert(self, message: str):
        timestamp = datetime.now().isoformat()
        logging.warning(f"[ALERT {timestamp}] {message}")

    def run(self):
        logging.info("BadUSB Detector started.")
        self.start_keystroke_monitor()
        threads = [
            threading.Thread(target=self.monitor_processes, daemon=True),
            threading.Thread(target=self.monitor_temp_files, daemon=True),
        ]
        for t in threads:
            t.start()
        try:
            while True:
                time.sleep(1)
        except KeyboardInterrupt:
            logging.info("Detector stopped.")


if __name__ == "__main__":
    BadUSBDetector().run()
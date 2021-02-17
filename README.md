# Assembler Project
Implementing a Cm Assembler for Virtual Machines in Java

## Unit Testing Documentation

Running Tests:
1. Compile Test File: javac TestX.java
2. Run Test File and Redirect Output into .txt: java TestX > TestX.txt
3. Run Unit Test Using AUnit: java aunit TestX.txt

## Test Cases:
...

## Scrum Team:
Nicholas Kawwas [nickawwas](https://github.com/nickawwas) \
Nicholas Harris [NichHarris](https://github.com/NichHarris) \
Matthew Sklivas [mattsklivas](https://github.com/mattsklivas) \
Vincent Beaulieu [vincbeaulieu](https://github.com/vincbeaulieu) \
Philippe Lee [Lee-Phil](https://github.com/Lee-Phil) \
Karine Chatta [karinechatta](https://github.com/karinechatta) \
Georgia Bardaklis [gbardaklis](https://github.com/gbardaklis) \
Lina Tran [linatran1](https://github.com/linatran1) \
Malek Jerbi [oguzgezginci](https://github.com/oguzgezginci)

## Code Structure
Assembly Unit
-> LineStatement + EOF

LineStatement
-> Label + Instruction + Comment + EOL

Label
-> String

Instruction
-> Mnemonic + Operand

Operand
-> Label (String) or Offset (Binary)

Directive: .cstring (String)

Comment (;)
If ; detected, ignore the rest of the line until you reach EOL character  ("\n" | "\r" | "\r\n")
Remove useless whitespace

Assembler ends when EOF is detected (<control-Z>)

Generate pgm.exe for target and complete listing file pgm.lst with label table
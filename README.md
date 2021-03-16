# main.Assembler Project
Implementing a Cm main.Assembler for Virtual Machines in main

## Unit Testing Documentation

Running tests:
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
-> main.LineStatement + EOF

main.LineStatement
-> Label + main.Instruction + main.Comment + EOL

Label
-> String

main.Instruction
-> main.Mnemonic + main.Operand

main.Operand
-> Label (String) or Offset (Binary)

Directive: .cstring (String)

main.Comment (;)
If ; detected, ignore the rest of the line until you reach EOL character  ("\n" | "\r" | "\r\n")
Remove useless whitespace

main.Assembler ends when EOF is detected (<control-Z>)

Generate pgm.exe for target and complete listing file pgm.lst with label table
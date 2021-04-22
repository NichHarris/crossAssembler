ldc.i3       ; Instruction should have number as an operand field.
pop 2        ; Instruction should not have a number as an operand field.
enter.u5 32  ; Operand should range from 0 to 31.
ldc.i3   4  ; Operand should range from -4 to 3.
ldv.u3   8  ; Operand should range from 0 to 7.
top         ;Unknown mnemonic or directive

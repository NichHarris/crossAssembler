ldc.i3       ; Instruction should have number as an operand field.
pop 2        ; Instruction should not have a number as an operand field. (works)
enter.u5 32  ; Operand should range from 0 to 31. (works)
ldc.i3   4  ; Operand should range from -4 to 3. (works)
ldv.u3   8  ; Operand should range from 0 to 7. (works)
top         ;Unknown mnemonic or directive (not working due to getTokenType)

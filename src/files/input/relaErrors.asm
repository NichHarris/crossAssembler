; relaErrors.asm - Possible errors in relative instructions.
      lda.i16     Msg1     ; Error: Msg1 label not found (or defined). <-TODO: Need to change from hashmap to arraylist for this error to appear
      ldc.i8      128      ; Error: Operand number not in an i8 range [-128..+127]. <- working
      ldc.i8      ABC      ; Error: Label must refer to a operand. <- working
      lda.i16     0        ; Error: Operand must refer to a label. <- working
      br.i8       1        ; Error: Operand must refer to a label. <- working
      br.i8       Msg1     ; Error: Msg1 label not found (or defined). <- working
      lda.i16     Msg2     ; OK.
Msg2  .cstring    "A1"     ; OK.
Msg2  .cstring    "B23"    ; Error: Msg2 label already defined. <- working
Msg3  .cstring    "C45"    ; OK, but not used.

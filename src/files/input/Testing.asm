          enter.u5  0        ; OK, number <u5> [0..31].
          thisIsAnInvalidToken
          enter.u5  1        ; OK, number <u5> [0..31].
          enter.u5  2        ; OK, number <u5> [0..31].
          enter.u5  3        ; OK, number <u5> [0..31].
          enter.u5  4        ; OK, number <u5> [0..31].
          enter.u5  5        ; OK, number <u5> [0..31].
          enter.u5  6        ; OK, number <u5> [0..31].
          enter.u5  7        ; OK, number <u5> [0..31].
          enter.u5  8        ; OK, number <u5> [0..31].
          enter.u5  9        ; OK, number <u5> [0..31].
          enter.u5  10       ; OK, number <u5> [0..31].
          enter.u5  11       ; OK, number <u5> [0..31].
          enter.u5  12       ; OK, number <u5> [0..31].
          enter.u5  13       ; OK, number <u5> [0..31].
          enter.u5  14       ; OK, number <u5> [0..31].
          enter.u5  15       ; OK, number <u5> [0..31].
          enter.u5  16       ; OK, number <u5> [0..31].
          enter.u5  17       ; OK, number <u5> [0..31].
          enter.u5  18       ; OK, number <u5> [0..31].
          enter.u5  19       ; OK, number <u5> [0..31].
          enter.u5  20       ; OK, number <u5> [0..31].
          enter.u5  21       ; OK, number <u5> [0..31].
          enter.u5  22       ; OK, number <u5> [0..31].
          enter.u5  23       ; OK, number <u5> [0..31].
          enter.u5  24       ; OK, number <u5> [0..31].
          enter.u5  25       ; OK, number <u5> [0..31].
          enter.u5  26       ; OK, number <u5> [0..31].
          enter.u5  27       ; OK, number <u5> [0..31].
          enter.u5  28       ; OK, number <u5> [0..31].
          enter.u5  29       ; OK, number <u5> [0..31].
          enter.u5  30       ; OK, number <u5> [0..31].
          enter.u5  41       ; OK, number <u5> [0..31].

            ldc.i3  0        ; OK, number <i3> [-4..3].
            ldc.i3  1        ; OK, number <i3> [-4..3].
            ldc.i3  2        ; OK, number <i3> [-4..3].
            ldc.i3  3        ; OK, number <i3> [-4..3].
            ldc.i3  -4       ; OK, number <i3> [-4..3].
            ldc.i3  -3       ; OK, number <i3> [-4..3].
            ldc.i3  -2       ; OK, number <i3> [-4..3].
            ldc.i3  -1       ; OK, number <i3> [-4..3].
            ldc.i3           ; OK, number <i3> [-4..3].

           addv.u3  0        ; OK, number <u3> [0..7].
           addv.u3  1        ; OK, number <u3> [0..7].
           addv.u3  2        ; OK, number <u3> [0..7].
           addv.u3  3        ; OK, number <u3> [0..7].
           addv.u3  4        ; OK, number <u3> [0..7].
           addv.u3  5        ; OK, number <u3> [0..7].
           addv.u3  6        ; OK, number <u3> [0..7].
           addv.u3  7        ; OK, number <u3> [0..7].

            ldv.u3  0        ; OK, number <u3> [0..7].
            ldv.u3  1        ; OK, number <u3> [0..7].
            ldv.u3  2        ; OK, number <u3> [0..7].
            ldv.u3  3        ; OK, number <u3> [0..7].
            ldv.u3  4        ; OK, number <u3> [0..7].
            ldv.u3  5        ; OK, number <u3> [0..7].
            ldv.u3  6        ; OK, number <u3> [0..7].
            ldv.u3  7        ; OK, number <u3> [0..7].
            ldv.u3  8        ; OK, number lbv.u3 [0..7].

            stv.u3  0        ; OK, number <u3> [0..7].
            stv.u3  1        ; OK, number <u3> [0..7].
            stv.u3  2        ; OK, number <u3> [0..7].
            stv.u3  3        ; OK, number <u3> [0..7].
            stv.u3  4        ; OK, number <u3> [0..7].
            stv.u3  5        ; OK, number <u3> [0..7].
            stv.u3  6        ; OK, number <u3> [0..7].
            stv.u3  7        ; OK, number <u3> [0..7].

            ; rela01.asm
                  lda.i16     Msg1
                  ldc.i8      12
                  lda.i16     Msg2
                  lda.i16     Msg2

            Msg1  .cstring    "A1"     ; OK. Code generated -> 41 31 00
            Msg2  .cstring    "B23"    ; Only ASCII printable characters are allowed.

            halt 5

            MsgX enter.u5  5        ; OK, number <u5> [0..31].
            MsgX enter.u5  6        ; OK, number <u5> [0..31].
; TestImmediate.asm - Test immediate instructions.

          enter.u50        ; OK, number <u5> [0..31].
          enter.u51        ; OK, number <u5> [0..31].
          enter.u52        ; OK, number <u5> [0..31].
                                                    enter .u5  3        ; OK, number <u5> [0..31].
          enter.u54;OK,number<u5>[0..31].

          §§§§enter.u5  6        ; OK, number <u5> [0..31].
          enter.u5  7        ; OK, number <u5> [0..31].
          enter.u5  8        ; OK, number <u5> [0..31].
          enter.u5  9        ; OK, number <u5> [0..31].
          enter.u5  10       ; OK, number <u5> [0..31].

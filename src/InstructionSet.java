package src;

import java.util.HashMap;

public class InstructionSet {

    private HashMap<String, Integer> instructionSet;

    public InstructionSet() {
        //HashMap Containing Instruction Set
        instructionSet = new HashMap<String, Integer>();

        //Stack/Inherent Addressing - No Operands
        instructionSet.put("halt", 0x00);
        instructionSet.put("pop", 0x01);
        instructionSet.put("dup", 0x02);
        instructionSet.put("exit", 0x03);
        instructionSet.put("ret", 0x04);
        /* Reserved Instructions (05 - 0B) */
        instructionSet.put("not", 0x0C);
        instructionSet.put("and", 0x0D);
        instructionSet.put("or", 0x0E);
        instructionSet.put("xor", 0x0F);
        instructionSet.put("neg", 0x10);
        instructionSet.put("inc", 0x11);
        instructionSet.put("dec", 0x12);
        instructionSet.put("add", 0x13);
        instructionSet.put("sub", 0x14);
        instructionSet.put("mul", 0x15);
        instructionSet.put("div", 0x16);
        instructionSet.put("rem", 0x17);
        instructionSet.put("shl", 0x18);
        instructionSet.put("shr", 0x19);
        instructionSet.put("teq", 0x1A);
        instructionSet.put("tne", 0x1B);
        instructionSet.put("tlt", 0x1C);
        instructionSet.put("tgt", 0x1D);
        instructionSet.put("tle", 0x1E);
        instructionSet.put("tge", 0x1F);

        //Immediate Addressing - 3/5 Bit Operands
        instructionSet.put("br.i5", 0x30); //..4F"
        instructionSet.put("brf.i5", 0x50); //..6F"
        instructionSet.put("enter.u5", 0x70); //..8F"
        instructionSet.put("ldc.i3", 0x90); //..97"
        instructionSet.put("addv.u3", 0x98); //..9F"
        instructionSet.put("ldv.u3", 0xA0); //..A7"
        instructionSet.put("stv.u3", 0xA8); //..AF"

        //Relative Addressing - 8/16/32 Bit Operands
        instructionSet.put("addv.u8", 0xB0);
        instructionSet.put("ldv.u8", 0xB1);
        instructionSet.put("stv.u8", 0xB2);
        instructionSet.put("incv.u8", 0xB3);
        instructionSet.put("decv.u8", 0xB4);
        instructionSet.put("enter.u8", 0xBF);
        instructionSet.put("lda.i16", 0xD5);
        instructionSet.put("ldc.i8", 0xD9);
        instructionSet.put("ldc.i16", 0xDA);
        instructionSet.put("ldc.i32", 0xDB);
        instructionSet.put("br.i8", 0xE0);
        instructionSet.put("br.i16", 0xE1);
        instructionSet.put("brf.i8", 0xE3);
        instructionSet.put("call.i16", 0xE7);
        instructionSet.put("trap", 0xFF);
    }

//    public HashMap<String, String> getInstructionSet() {
//        return instructionSet;
//    }

    public Integer getCode(String token) {
        // add unit test here for invalid token recognition
        return instructionSet.get(token);
    }
}

package src;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, String> instructionSet;

    public SymbolTable() {
        //HashMap Containing Instruction Set
        instructionSet = new HashMap<String, String>();

        //Stack/Inherent Addressing - No Operands
        instructionSet.put("halt", "00");
        instructionSet.put("pop", "01");
        instructionSet.put("dup", "02");
        instructionSet.put("exit", "03");
        instructionSet.put("ret", "04");
        /* Reserved Instructions (05 - 0B) */
        instructionSet.put("not", "0C");
        instructionSet.put("and", "0D");
        instructionSet.put("or", "0E");
        instructionSet.put("xor", "0F");
        instructionSet.put("neg", "10");
        instructionSet.put("inc", "11");
        instructionSet.put("dec", "12");
        instructionSet.put("add", "13");
        instructionSet.put("sub", "14");
        instructionSet.put("mul", "15");
        instructionSet.put("div", "16");
        instructionSet.put("rem", "17");
        instructionSet.put("shl", "18");
        instructionSet.put("shr", "19");
        instructionSet.put("teq", "1A");
        instructionSet.put("tne", "1B");
        instructionSet.put("tlt", "1C");
        instructionSet.put("tgt", "1D");
        instructionSet.put("tle", "1E");
        instructionSet.put("tge", "1F");

        //Immediate Addressing - 3/5 Bit Operands
        instructionSet.put("br.i5", "30..4F");
        instructionSet.put("brf.i5", "50..6F");
        instructionSet.put("enter.u5", "70..8F");
        instructionSet.put("ldc.i3", "90..97");
        instructionSet.put("addv.u3", "98..9F");
        instructionSet.put("ldv.u3", "A0..A7");
        instructionSet.put("stv.u3", "A8..AF");

        //Relative Addressing - 8/16/32 Bit Operands
        instructionSet.put("addv.u8", "B0");
        instructionSet.put("ldv.u8", "B1");
        instructionSet.put("stv.u8", "B2");
        instructionSet.put("incv.u8", "B3");
        instructionSet.put("decv.u8", "B4");
        instructionSet.put("enter.u8", "BF");
        instructionSet.put("lda.i16", "D5");
        instructionSet.put("ldc.i8", "D9");
        instructionSet.put("ldc.i16", "DA");
        instructionSet.put("ldc.i32", "DB");
        instructionSet.put("br.i8", "E0");
        instructionSet.put("br.i16", "E1");
        instructionSet.put("brf.i8", "E3");
        instructionSet.put("call.i16", "E7");
        instructionSet.put("trap", "FF");
    }

    public HashMap<String, String> getInstructionSet() {
        return instructionSet;
    }

    public String getCode(String token) {
        // add unit test here for invalid token recognition
        return instructionSet.get(token);
    }
}

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Integer> symbolTable;

    public SymbolTable() {
        //HashMap Containing Instruction Set
        symbolTable = new HashMap<String, Integer>();

        //Stack/Inherent Addressing - No Operands
        symbolTable.put("halt", 0x00);
        symbolTable.put("pop", 0x01);
        symbolTable.put("dup", 0x02);
        symbolTable.put("exit", 0x03);
        symbolTable.put("ret", 0x04);
        /* Reserved Instructions (05 - 0B) */
        symbolTable.put("not", 0x0C);
        symbolTable.put("and", 0x0D);
        symbolTable.put("or", 0x0E);
        symbolTable.put("xor", 0x0F);
        symbolTable.put("neg", 0x10);
        symbolTable.put("inc", 0x11);
        symbolTable.put("dec", 0x12);
        symbolTable.put("add", 0x13);
        symbolTable.put("sub", 0x14);
        symbolTable.put("mul", 0x15);
        symbolTable.put("div", 0x16);
        symbolTable.put("rem", 0x17);
        symbolTable.put("shl", 0x18);
        symbolTable.put("shr", 0x19);
        symbolTable.put("teq", 0x1A);
        symbolTable.put("tne", 0x1B);
        symbolTable.put("tlt", 0x1C);
        symbolTable.put("tgt", 0x1D);
        symbolTable.put("tle", 0x1E);
        symbolTable.put("tge", 0x1F);

        //Immediate Addressing - 3/5 Bit Operands
        symbolTable.put("br.i5", 0x30); //..4F"
        symbolTable.put("brf.i5", 0x50); //..6F"
        symbolTable.put("enter.u5", 0x70); //..8F"
        symbolTable.put("ldc.i3", 0x90); //..97"
        symbolTable.put("addv.u3", 0x98); //..9F"
        symbolTable.put("ldv.u3", 0xA0); //..A7"
        symbolTable.put("stv.u3", 0xA8); //..AF"

        //Relative Addressing - 8/16/32 Bit Operands
        symbolTable.put("addv.u8", 0xB0);
        symbolTable.put("ldv.u8", 0xB1);
        symbolTable.put("stv.u8", 0xB2);
        symbolTable.put("incv.u8", 0xB3);
        symbolTable.put("decv.u8", 0xB4);
        symbolTable.put("enter.u8", 0xBF);
        symbolTable.put("lda.i16", 0xD5);
        symbolTable.put("ldc.i8", 0xD9);
        symbolTable.put("ldc.i16", 0xDA);
        symbolTable.put("ldc.i32", 0xDB);
        symbolTable.put("br.i8", 0xE0);
        symbolTable.put("br.i16", 0xE1);
        symbolTable.put("brf.i8", 0xE3);
        symbolTable.put("call.i16", 0xE7);
        symbolTable.put("trap", 0xFF);
    }

    // @TEAM -> Add unit test here for invalid token recognition

    //Get Instruction Code
    public Integer getCode(String key) {
        return symbolTable.get(key);
    }
}

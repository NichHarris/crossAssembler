package test.java;

import main.interfaces.*;
import main.java.*;

public class ParserTest {
    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("ParserTest.txt");

        IReader rdr = new Reader("TestImmediate.asm");
        rdr.readFile();
        ISymbolTable symT= new SymbolTable();
        IErrorReporter er = new ErrorReporter("TestImmediate.asm");
        IScanner scn= new Scanner(symT,er);
        IParser p = new Parser(rdr.getLineNum() + 1, symT, er, scn, rdr);
        p.parseToken();

        //Testing getInterRep()
        testParser("getInterRep() number of lines", "71", Integer.toString(p.getInterRep().getLength()));

        //should we test the parseOperandBound in its entirety, and how
        testParser("getInterRep() inner contents ", "' '\": -1\" ' '", p.getInterRep().getLine(1).toString());

    }
    public static void testParser(String caseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test -Parser Class- " + caseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}


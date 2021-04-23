package test.java;

import main.interfaces.*;
import main.java.*;

public class ParserTest {
    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("ParserTest.txt");


        //Creating a parser to test with
        IReader rdr = new Reader("Testing.asm");
        rdr.readFile();
        ISymbolTable symT= new SymbolTable();
        ErrorReporter er = new ErrorReporter("Testing.asm");
        IScanner scn= new Scanner(symT,er);
        IParser p = new Parser(rdr.getLineNum() + 1, symT, er, scn, rdr);
        p.parseToken();

        IInterRep rep  = p.getInterRep();

        //Testing getInterRep()
        testParser("getInterRep() number of lines", "86", Integer.toString(p.getInterRep().getLength()));

        //should we test the parseOperandBound in its entirety, and how
        testParser("getInterRep() inner contents ", "' '\"enter.u5: 132\" 4' ; OK, number <u5> [0..31].'", p.getInterRep().getLine(5).toString());

        // Error: Invalid Token
        testParser("getInterRep() Invalid Token ", "'thisIsAnInvalidToken '\": -1\" ' '", p.getInterRep().getLine(1).toString());

        // Error: Adding Operand with Stack
        testParser("getInterRep() Adding Operand with Stack","Error: Line 82, Column 0: Instructions with inherent addressing mode do not have an operand field. [halt]",er.getErrorMsg(3).getErrorMsg());

        // Error: Forgetting Operand with Immediate value
        testParser("getInterRep() Forgetting Operand with Immediate value","Error: Line 43, Column 2: Instructions with immediate mode addressing needs to have an operand field. [ldc.i3]" , er.getErrorMsg(1).getErrorMsg() );

        // Error: Operand Size Exceeds Bounds
        testParser("getInterRep() Operand Size Exceeds Bounds","Error: Line 32, Column 1: The immediate instruction 'enter.u5' must have a 5-bit unsigned operand number ranging from 0 to 31." , er.getErrorMsg(0).getErrorMsg() );

        //Creating an Interrep with just Immediate
        IReader rdr2 = new Reader("TestImmediate.asm");
        rdr2.readFile();
        ISymbolTable symT2= new SymbolTable();
        ErrorReporter er2 = new ErrorReporter("TestImmediate.asm");
        IScanner scn2= new Scanner(symT2,er2);
        IParser p2 = new Parser(rdr2.getLineNum() + 1, symT2, er2, scn2, rdr2);
        p2.parseToken();
        IInterRep rep2  = p2.getInterRep();
        // Successful Parsing of File - Immediate
        testParser("Successful Parsing of File - Immediate", "' '\": -1\" ' ; TestImmediate.asm - Test immediate instructions.'", rep2.getLine(0).toString());

        //Creating an Interrep with just Immediate
        IReader rdr3 = new Reader("Testing3.asm");
        rdr3.readFile();
        ISymbolTable symT3= new SymbolTable();
        ErrorReporter er3 = new ErrorReporter("Testing3.asm");
        IScanner scn3= new Scanner(symT3,er3);
        IParser p3 = new Parser(rdr3.getLineNum() + 1, symT3, er3, scn3, rdr3);
        p3.parseToken();
        IInterRep rep3  = p2.getInterRep();
        //Successful Parsing of File- Stack
        testParser("Successful Parsing of File - Immediate", "' '\"halt: 0\" ' '", rep3.getLine(0).toString());

        // Token uses keyword - Label cannot be a mnemonic for example
        testParser("getInterRep() Operand cannot be a mnemonic", "' '\": -1\" ' '", rep2.getLine(61).toString());
        // Label must not be created twice
        //testParser("getInterRep() Label must not be created twice ", "", p.getInterRep().getLine(84).toString());
    }

    public static void testParser(String caseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test -Parser Class- " + caseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

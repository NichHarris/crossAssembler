package test.java;

import main.interfaces.*;
import main.java.*;

public class ParserTest {
    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("ParserTest.txt");
        IReader rdr = new Reader("Testing.asm");
        rdr.readFile();
        ISymbolTable symT= new SymbolTable();
        ErrorReporter er = new ErrorReporter("Testing.asm");
        IScanner scn= new Scanner(symT,er);
        IParser p = new Parser(rdr.getLineNum() + 1, symT, er, scn, rdr);
        p.parseToken();


        IInterRep rep  = p.getInterRep();
        //Testing getInterRep()
        testParser("getInterRep() number of lines", "70", Integer.toString(p.getInterRep().getLength()));

        //should we test the parseOperandBound in its entirety, and how
        testParser("getInterRep() inner contents ", "' '\"enter.u5: 132\" 4' ; OK, number <u5> [0..31].'", p.getInterRep().getLine(5).toString());

        // Error: Invalid Token
        testParser("getInterRep() Invalid Token ", "'thisIsAnInvalidToken '\": -1\" ' '", p.getInterRep().getLine(1).toString());

        // Error: Adding Operand with Stack
        testParser("getInterRep()  Adding Operand with Stack","Error: Line 81, Column 0: Instructions with inherent addressing mode do not have an operand field. [halt]",er.getErrorMsg(2).getErrorMsg());
        // Error: Forgetting Operand with Immediate value
        testParser("getInterRep() Forgetting Operand with Immediate value","Error: Line 43, Column 2: Instructions with immediate mode addressing needs to have an operand field. [ldc.i3]" , er.getErrorMsg(1).getErrorMsg() );

        // Error: Operand Size Exceeds Bounds
        testParser("getInterRep() Operand Size Exceeds Bounds","Error: Line 32, Column 1: The immediate instruction 'enter.u5' must have a 5-bit unsigned operand number ranging from 0 to 31." , er.getErrorMsg(0).getErrorMsg() );

        // Successful Parsing of File - Stack and Immediate #confusionINTENSIFIED


        // Token uses keyword - Label cannot be a mnemonic for example
        testParser(" ", " ", p.getInterRep().getLine(77).toString());
        // Label must not be created twice-Malek


    }
    public static void testParser(String caseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test -Parser Class- " + caseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}


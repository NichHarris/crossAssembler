package test.java;

import main.interfaces.*;
import main.java.*;

public class InterRepTest {
    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("InterRepTest.txt");
        //Parametrized Constructor
        IInterRep IR = new InterRep(1);

        // Testing setter with a Line Statement
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");
        IInstruction instruction = new Instruction (m, op);
        ILineStatement ls = new LineStatement("Fct", instruction, "");

        // Testing addLine
        IR.addLine(0, ls);
        TestInterRep("Test -InterRep Class- addLine with just a line statement object", "'Fct '\"addv.u3: 152\" 3' '", IR.getLine(0).toString());

        // Testing Get LineStatement
        ILineStatement ls1 = IR.getLine(0);
        TestInterRep("Test -InterRep Class- getLine()", "'Fct '\"addv.u3: 152\" 3' '", ls1.toString());

        // Testing Get length of LineStatement
        int numberOfLines = IR.getLength();
        TestInterRep("Test -InterRep Class- getLength()", "1", Integer.toString(numberOfLines));

        // feeding an empty line statement()
        LineStatement ls2 = new LineStatement();
        IR.addLine(0, ls2);
        TestInterRep("Test -InterRep Class- empty LineStatement passed to InterRep object", "' '\": -1\" ' '", IR.getLine(0).toString());

        //Set LineStatement's instruction and hasInstruction method
        //IR2.setInstruction(0, instruction);
        TestInterRep("Test -InterRep- setInstruction() and hasInstruction()","false",Boolean.toString(IR.hasInstruction(0)));

        //Set Addr and get Addr
        IR.setAddr(0,100);
        TestInterRep("Test -InterRep- setAddr() and getAddr()","100",Integer.toString(IR.getAddr(0)));
    }

    public static void TestInterRep(String testCaseName, String expectedOutput, String methodOutput) throws Exception{
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

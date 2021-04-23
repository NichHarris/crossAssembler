package test.java;

import main.interfaces.IInstruction;
import main.interfaces.IMnemonic;
import main.interfaces.IOperand;
import main.interfaces.ISymbolTable;
import main.java.Instruction;
import main.java.Mnemonic;
import main.java.Operand;
import main.java.SymbolTable;

public class InstructionTest {
    public static void main(String[] args) throws Exception {
        TextFileGenerator.textFileGenerator("InstructionTest.txt");

        //Testing the default constructor
        IInstruction instruction1 = new Instruction();
        testInstruction("Test -Instruction Class- Default Constructor", " ", instruction1.getMnemonic().getMne() + " " + instruction1.getOperand().getOp());

        //Creating an instruction with both a Mnemonic and Operand
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");
        instruction1 = new Instruction (m, op);

        //Testing the Constructor of the class with getOperand() and getMnemonic()
        testInstruction("Test -Instruction Class- Mnemonic and Operand Constructor", "addv.u3 3", instruction1.getMnemonic().getMne()+" "+instruction1.getOperand().getOp());

        //Testing getMnemonic()
        code = symbolTable.getCode("not");
        m = new Mnemonic("not", code);
        testInstruction("Test -Instruction Class- setMnemonic", "addv.u3", instruction1.getMnemonic().getMne());

        //Testing getOperand()
        op = new Operand("4");
        testInstruction("Test -Instruction Class- getOperand", "3", instruction1.getOperand().getOp());

        //Testing the function toString() from Instruction Class
        testInstruction("Test -Instruction Class- toString", "'\"addv.u3: 152\" 3'", instruction1.toString());

        //Testing the function getSize() from Instruction Class
        testInstruction("Test -Instruction Class- getSize", "1", Integer.toString(instruction1.getSize()));
    }

    public static void testInstruction(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        //TestCase Name
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

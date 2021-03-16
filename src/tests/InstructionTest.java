package tests;

import Interfaces.IInstruction;
import Interfaces.IMnemonic;
import Interfaces.IOperand;
import Interfaces.ISymbolTable;
import main.Instruction;
import main.Mnemonic;
import main.Operand;
import main.SymbolTable;

public class InstructionTest {
    public static void main(String[] args) throws Exception {

        //Testing the default constructor
        IInstruction instruction1 = new Instruction();
        testInstruction("Test -Java.Instruction Class- Default Constructor", "null null", instruction1.getMnemonic().getMne() + " " + instruction1.getOperand().getOp());

        //Creating an instruction with both a Java.Mnemonic and Java.Operand
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");
        instruction1 = new Instruction (m, op);

        //Testing the Constructor of the class with getOperand() and getMnemonic()
        testInstruction("Test -Java.Instruction Class- Java.Mnemonic and Java.Operand Constructor", "addv.u3 3", instruction1.getMnemonic().getMne()+" "+instruction1.getOperand().getOp());

        //Testing setMnemonic(Interfaces.IMnemonic m), getMnemonic()
        code = symbolTable.getCode("not");
        m = new Mnemonic("not", code);
        instruction1.setMnemonic(m);
        testInstruction("Test -Java.Instruction Class- setMnemonic", "not", instruction1.getMnemonic().getMne());

        //Testing setOperand(Interfaces.IOperand o) and getOperand()
        op = new Operand("4");
        instruction1.setOperand(op);
        testInstruction("Test -Java.Instruction Class- setOperand", "4", instruction1.getOperand().getOp());

        //Testing the function toString() from Java.Instruction Class
        testInstruction("Test -Java.Instruction Class- toString", "'not 4'", instruction1.toString());
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
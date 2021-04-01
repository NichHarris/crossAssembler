package test.java;

import main.interfaces.IInstruction;
import main.interfaces.IMnemonic;
import main.interfaces.IOperand;
import main.interfaces.ISymbolTable;

import main.java.Instruction;
import main.java.InterRep;
import main.java.LineStatement;
import main.java.Listing;
import main.java.Mnemonic;
import main.java.Operand;
import main.java.SymbolTable;

public class ListingTest {
    public static void main(String[] args) throws Exception {
        //Creating an InterRepresentation
        InterRep IR = new InterRep(1);
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");

        //Creating mnemonic and operand objects for instruction object
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");

        //Creating an Instruction
        IInstruction instruction = new Instruction (m, op);

        //Creating a LineStatement with Instruction
        LineStatement ls = new LineStatement("Fct", instruction, "; this is a comment");

        //Adding Line Statement to IR
        IR.addLine(0, ls);

        String []mCode = new String[IR.getLength()];
        // initializing code generator object
        Listing list = new Listing(IR,mCode);

        TestListing("Test -Listing Class- getListing()","1    0000 null          Fct           addv.u3       3             ; this is a comment", list.getListing()[1]);
    }

    public static void TestListing(String testCaseName, String expectedOutput, String methodOutput) throws Exception{
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

package main.interfaces;

//Interface for Instruction class
public interface IInstruction {

    //Set Opcode of instruction
    void setOpcode(int code);

    //Get mnemonic
    IMnemonic getMnemonic();

    //Get operand
    IOperand getOperand();

    //Get the byte size of a LineStatement
    int getSize();

    //Returns a String representable of an Instruction object
    String toString();
}

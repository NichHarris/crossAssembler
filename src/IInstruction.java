//Interface for Instruction class
public interface IInstruction {

    //Set mnemonic
    void setMnemonic(IMnemonic m);

    //Set operand
    void setOperand(IOperand o);

    //Get mnemonic
    IMnemonic getMnemonic();

    //Get operand
    IOperand getOperand();

    //Returns a String representable of an Instruction object
    String toString();
}

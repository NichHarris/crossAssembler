//Interface for Instruction class
public interface IInstruction {

    //Set mnemonic
    void setMnemonic(String m);

    //Set operand
    void setOperand(String o);

    //Get mnemonic
    String getMnemonic();

    //Get operand
    String getOperand();

    //Check if operand is digit (true) or label (false)
    boolean isDigit();

    //Return the code of the Instruction object's mnemonic
    public Integer getCode();

    //Returns a String representable of an Instruction object
    String toString();
}

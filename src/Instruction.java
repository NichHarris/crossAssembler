//Instruction comprised of a mnemonic and (optional) operand
public class Instruction implements IInstruction {
    private String mnemonic;
    private String operand;

    //Default constructor
    public Instruction() {
        mnemonic = null;
        operand = null;
    }

    //Parameterized constructor with mnemonic and operand
    public Instruction(String m,String o) {
        mnemonic = m;
        operand = o;
    }

    //Set mnemonic
    public void setMnemonic(String m) {
        mnemonic = m;
    }

    //Set operand
    public void setOperand(String o) {
        operand = o;
    }

    //Get mnemonic
    public String getMnemonic() {
        return mnemonic;
    }

    //Get operand
    public String getOperand() {
        return operand;
    }

    //Check if operand is digit (true) or label (false)
    public boolean isDigit() {
        return operand.matches("[0-9]+");
    }

    //Returns a String representable of an Instruction object
    public String toString() {
        return String.format("'%s'", mnemonic + " " + operand);
    }
}
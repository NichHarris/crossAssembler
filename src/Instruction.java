//Instruction comprised of a mnemonic and (optional) operand
public class Instruction implements IInstruction {
    private IMnemonic mnemonic;
    private IOperand operand;

    //Default constructor
    public Instruction() {
        mnemonic = new Mnemonic();
        operand = new Operand();
    }

    //Parameterized constructor with mnemonic and operand
    public Instruction(IMnemonic m,IOperand o) {
        mnemonic = m;
        operand = o;
    }

    //Set mnemonic
    public void setMnemonic(IMnemonic m) {
        mnemonic = m;
    } //for testing

    //Set operand
    public void setOperand(IOperand o) {
        operand = o;
    } //for testing

    //Get mnemonic
    public IMnemonic getMnemonic(){ return mnemonic; }

    //Get operand
    public IOperand getOperand(){ return operand; }

    //Returns a String representable of an Instruction object
    public String toString() {
        return String.format("'%s'", mnemonic.toString() + " " + operand.toString());
    }
}
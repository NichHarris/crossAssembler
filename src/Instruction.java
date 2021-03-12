//Instruction comprised of a mnemonic and (optional) operand
public class Instruction implements IInstruction {
    private IMnemonic mnemonic;
    private IOperand operand;

    //Default constructor
    public Instruction() {
        mnemonic = new Mnemonic();
        operand = new Operand();
    }

    //Stack, Inherent Addressing Constructor
    public Instruction(IMnemonic m){
        mnemonic = m;
        operand = new Operand();
    }


    //Immediate or Relative Constructor
    public Instruction(IMnemonic m,IOperand o) {
        mnemonic = m;
        operand = o;
    }

    //Set mnemonic
    public void setMnemonic(IMnemonic m) {
        mnemonic = m;
    }

    //Set operand
    public void setOperand(IOperand o) {
        operand = o;
    }

    //Set Opcode of instruction
    public void setOpcode(int code) {mnemonic.setOpcode(code);}

    //Get mnemonic
    public IMnemonic getMnemonic(){ return mnemonic; }

    //Get operand
    public IOperand getOperand(){ return operand; }

    //Returns a String representable of an Instruction object
    public String toString() {
        if (operand == null)
            return String.format("'%s'", mnemonic.toString());

        return String.format("'%s'", mnemonic.toString() + " " + operand.toString());
    }
}
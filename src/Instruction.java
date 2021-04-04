//Instruction comprised of a mnemonic and (optional) operand
public class Instruction implements IInstruction {
    private IMnemonic mnemonic;
    private IOperand operand;

    //Default constructor
    public Instruction() {
        mnemonic = new Mnemonic();
        operand = new Operand();
    }

    //Stack, inherent addressing constructor
    public Instruction(IMnemonic m){
        mnemonic = m;
        operand = new Operand();
    }

    //Immediate or relative constructor
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

    //Checks if instruction contains mnemonic and operand
    private boolean isEmpty(){
        return this.getMnemonic().getMne().equals("") && this.getOperand().getOp().equals("");
    }

    //Get instruction size
    public int getSize() {
        if (!this.isEmpty()) {
            //Get the original opcode before making modifications
            int initOpcode = this.getMnemonic().getOpcode();

            //Stack/Inherent addressing
            if (initOpcode >= 0x00 && initOpcode <= 0x1F) {
                return 1;
            }
            //Immediate addressing
            else if (initOpcode >= 0x30 && initOpcode <= 0xAF) {
                String operand = this.getOperand().getOp();
                if (!this.getOperand().isNumeric() && !operand.equals("")) {
                    return 3;
                } else {
                    return 1;
                }
            }
            //Relative addressing
            else if (initOpcode >= 0xB0 && initOpcode <= 0xFF) {
                String operand = this.getOperand().getOp();
                if (!this.getOperand().isNumeric() && !operand.equals("")) {
                    return 3;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    //Returns a string representable of an instruction object
    public String toString() {
        if (operand == null)
            return String.format("'%s'", mnemonic.toString());

        return String.format("'%s'", mnemonic.toString() + " " + operand.toString());
    }
}
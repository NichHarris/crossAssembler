package main.java;

import main.interfaces.IInstruction;
import main.interfaces.IMnemonic;
import main.interfaces.IOperand;

//Instruction comprised of a mnemonic and (optional) operand
public class Instruction implements IInstruction {
    private final IMnemonic mnemonic;
    private final IOperand operand;

    //Default constructor
    public Instruction() {
        mnemonic = new Mnemonic();
        operand = new Operand();
    }

    //Immediate or relative constructor
    public Instruction(IMnemonic m,IOperand o) {
        mnemonic = m;
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
                //if (!this.getOperand().isNumeric() && !operand.equals("")) {
                //    return 3;
                //} else {
                return 1;
                //}
            }
            //Relative addressing
            else if (initOpcode >= 0xB0 && initOpcode <= 0xFF) {
                //Get substring of mnemonic
                String mne = this.getMnemonic().getMne();
                String subMne = mne.substring(mne.indexOf('.') + 1);

                //Get signed or unsigned and size
                boolean isSigned = subMne.contains("i");
                int size = Integer.parseInt(subMne.substring(subMne.indexOf(isSigned ? 'i' : 'u') + 1));
                return (1 + size/8);
                /*
                //String operand = this.getOperand().getOp();
                if (!this.getOperand().isNumeric() && !operand.equals("")) {
                    return 3;
                } else {
                    return 2;
                }
                 */
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
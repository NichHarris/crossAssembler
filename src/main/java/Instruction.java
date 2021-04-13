/*
    SOEN 341 - Cm Cross-Assembler Version 1.4 - Developed by Team 3.

    Nicholas Kawwas - 40124338
    Matthew Sklivas - 40095150
    Nicholas Harris - 40111093
    Georgia Bardaklis - 40096586
    Karine Chatta - 27894392
    Lina Tran - 40130446
    Vincent Beaulieu - 40062386
    Philippe Lee - 40131559
    Malek Jerbi - 40130983

 */


//Import necessary files and packages
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

            //Stack/Inherent || Immediate addressing
            if (initOpcode >= 0x00 && initOpcode <= 0xAF)
                return 1;
            //Relative addressing
            else if (initOpcode >= 0xB0 && initOpcode <= 0xFF) {
                //Get substring of mnemonic
                int size = this.getMnemonic().getMneSize();

                //Mnemonic + Opcode
                return 1 + size;
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
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
import main.interfaces.IMnemonic;

// Class which represents the Mnemonic
public class Mnemonic implements IMnemonic {
    public String mnemonic;
    public int opcode;

    //Default constructor
    public Mnemonic() {
        mnemonic = "";
        opcode = -1;
    }

    //Parametrized constructor
    public Mnemonic(String m, int o) {
        mnemonic = m;
        opcode = o;
    }

    //Get the opcode of a mnemonic
    public int getOpcode() { return opcode; }

    //Get the mnemonic name as a string
    public String getMne(){ return mnemonic; }

    //Set the opcode of a mnemonic
    public void setOpcode(int opc) { opcode = opc; }

    //Set the mnemonic name as a string
    public void setMnemonic(String mne) { mnemonic = mne; }

    //Get the mnemonic size in bytes
    public int getMneSize() {
        String subMne = mnemonic.substring(mnemonic.indexOf('.') + 1);

        //Get signed or unsigned size
        boolean isSigned = subMne.contains("i");

        return Integer.parseInt(subMne.substring(subMne.indexOf(isSigned ? 'i' : 'u') + 1))/8;
    }

    //toString method
    public String toString() {
        return "\"" + mnemonic + ": " + Integer.toString(opcode) + "\"";
    }
}

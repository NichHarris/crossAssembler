package main;

import Interfaces.IMnemonic;

// Class which represents the Java.Mnemonic
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

    //toString method
    public String toString() {
        return "\"" + mnemonic + ": " + Integer.toString(opcode) + "\"";
    }
}

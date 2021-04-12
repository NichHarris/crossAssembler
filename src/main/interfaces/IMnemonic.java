package main.interfaces;

//Interface for Mnemonic class
public interface IMnemonic {

    //Get the opcode of a mnemonic
    int getOpcode();

    //Get the mnemonic name as a string
    String getMne();

    //Set the opcode of a mnemonic
    void setOpcode(int opc);

    //Set the mnemonic name as a string
    void setMnemonic(String mne);

    //Get the mnemonic size
    int getMneSize();

    //toString method
    String toString();
}

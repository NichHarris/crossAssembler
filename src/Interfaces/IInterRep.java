package Interfaces;

import Interfaces.IInstruction;

//Interface for Java.InterRep class
public interface IInterRep {

    //Add Java.LineStatement with a Java.LineStatement object
    void addLine(int i, ILineStatement l);

    //Add Java.LineStatement with a label, Java.Instruction object and comment
    void addLine(int i, String l, IInstruction in, String c);

    //Get Java.LineStatement
    ILineStatement getLine(int i);

    //Get length of LineStatements array
    int getLength();

    //Set a Java.LineStatement's label
    void setLabel(int i, String label);

    //Set a Java.LineStatement's comments
    void setComment(int i, String comment);

    //Set a Java.LineStatement's instruction
    void setInstruction(int i, IInstruction instruction);

    //Set code of mnemonic
    void setMachineCode(int i);

    //Check if instruction is null
    boolean hasInstruction(int i);

    //Returns a String representable of an Java.InterRep object
    String toString();
}

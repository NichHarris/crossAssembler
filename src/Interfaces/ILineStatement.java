package Interfaces;

import Interfaces.IInstruction;

//Interface for Java.LineStatement class
public interface ILineStatement {

    //Setter for label
    void setLabel(String l);

    //Set instruction
    void setInstruction(IInstruction in);

    //Set comments
    void setComment(String c);

    //Get label
    String getLabel();

    //Get instruction
    IInstruction getInstruction();

    //Get comments
    String getComment();

    //Returns a String representable of a Java.LineStatement object
    String toString();
}

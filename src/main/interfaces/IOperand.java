package main.interfaces;

//Interface for Operand class
public interface IOperand {

    //Set operand
    void setOperand(String op);

    //Get operand as a string
    String getOp();

    //toString method
    String toString();

    boolean isNumeric();
}

//Interface for Operand class
public interface IOperand {

    //Set operand
    void setOperand(String op);

    //Get operand as a string
    String toString();

    //Check if operand is a number (true), otherwise return false
    boolean isDigit();
}

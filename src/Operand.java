//Class which represents the operand of an instruction
public class Operand implements IOperand {
    public String operand;

    //Default constructor
    public Operand() {
        operand = "";
    }

    //Parametrized constructor
    public Operand(String o) {
        operand = o;
    }

    //Set operand
    public void setOperand(String op) {
        operand = op;
    }

    //Get operand as a string
    public String getOp() {
        return operand;
    }

    //toString method
    public String toString() {
        return operand;
    }
}

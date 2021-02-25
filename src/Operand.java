//Class which represents the operand of an instruction
public class Operand implements IOperand {
    public String operand;

    //Default constructor
    public Operand() {
        operand = null;
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
    public String toString() {
        return operand;
    }

    //Check if operand is a number (true), otherwise return false (label)
    public boolean isDigit() {
        boolean bool = false;

        //Checks if Alphabetical Character Exists
        for(char c:operand.toCharArray())
            bool = c > 'A' ? false : true;

        return bool;
        //return operand.matches("[0-9]+");

        /*
        ASCII TABLE:
        48 to 57 -> integer char 0 to 9
        65 to 90 -> caps alphabetic
        97 to 122 -> small alphabetic
         */
    }

}

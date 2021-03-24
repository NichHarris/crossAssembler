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

    //Check if token is numeric
    public boolean isNumeric() {
        String str = this.getOp();
        if (str.length() == 0)
            return false;

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            //Check if character is outside number range
            if(c < 48 || c > 57)
                //Check if negative number (-)
                if (i == 0 && c == 45)
                    continue;
                else
                    return false;
        }
        return true;
    }

}

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorReporter implements IErrorReporter{

    //String comprised of recorded errors
    ArrayList<String> recordedErrors;
    //Dictionary of error messages
    HashMap<Integer,String> errorList;

    HashMap<Integer, Integer> invalidChars;

    //Default constructor
    public ErrorReporter(){
        //Initialize reportedErrors and errorList
        recordedErrors = new ArrayList<>();
        errorList = new HashMap<>();

        //Scanner errors
        errorList.put(0,"Error: invalid character\n");
        errorList.put(1,"Error: eof in string\n");
        errorList.put(2,"Error: eol in string\n");

        //Parser errors
        errorList.put(3,"Error: Instructions with inherent mode addressing do not have an operand field.\n"); //DONE
        //TODO: Done but may need to traverse IR to check for missing operands (what if you dont add a label or comment
        // after the immediate addressing mnemonic and start adding to the next line?)
        errorList.put(4,"Error: This immediate instruction must have a number as an operand field.\n");
        errorList.put(5,"Error: The immediate instruction 'enter.u5' must have a 5-bit unsigned operand number ranging from 0 to 31.\n"); //DONE
        errorList.put(6,"Error: The immediate instruction 'ldc.i3' must have a 3-bit signed operand number ranging from -4 to 3.\n"); //DONE
        errorList.put(7,"Error: The immediate instruction 'ldv.u3' must have a 3-bit unsigned operand number ranging from 0 to 7.\n"); //DONE

        invalidChars = new HashMap<>();
        fillInvalidChars();
    }

    //Adds an error from Scanner to reportedErrors
    public void addError(int errNum, int line, int col) {
        recordedErrors.add(errorList.get(errNum));
        recordedErrors.add(String.format("Line Number: %s1  Column Number: %s2\n\n", line, col));
    }

    //Adds an error from Scanner to reportedErrors for invalid characters
    public void addError(int errNum, int line, int col, char c) {
        recordedErrors.add(errorList.get(errNum));
        recordedErrors.add(String.format("Line Number: %s1  Column Number: %s2  Character: ", line, col));
        recordedErrors.add(Character.toString(c));
        recordedErrors.add("\n\n");
    }

    //Adds an error from Parser to reportedErrors
    public void addError(int errNum, IToken token) {
        recordedErrors.add(errorList.get(errNum));
        recordedErrors.add(String.format("Line Number: %s1  Column Number: %s2\n\n", token.getPosition().getLineNumber(), token.getPosition().getColumnNumber()));
    }

    //Print the errors to console
    public void reportErrors() {
        if (recordedErrors.size() != 0) {
            for (String error: recordedErrors)
                System.out.print(error);
        }
    }

    //Fill hashmap of invalid characters
    public void fillInvalidChars(){
        for(int i =0; i < 32; i++){
            if (i == 10 || i == 13){
                continue;
            }
            else{
                invalidChars.put(i, null);
            }
        }
        invalidChars.put(127, null);
    }

    //Check if character is valid
    public void isValid(char c, int lineNum, int colNum) {
        //Report error when ever non-valid character is detected
        if(invalidChars.containsKey((int) c)){
            addError(0, lineNum, colNum, c);
        }
    }

}

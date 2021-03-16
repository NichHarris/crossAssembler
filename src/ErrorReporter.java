import java.util.ArrayList;
import java.util.HashMap;

public class ErrorReporter implements IErrorReporter{

    //String comprised of recorded errors
    ArrayList<String> recordedErrors;
    //Dictionary of error messages
    HashMap<Integer,String> errorList;

    //Default constructor
    public ErrorReporter(){
        //Initialize reportedErrors and errorList
        recordedErrors = new ArrayList<>();
        errorList = new HashMap<>();

        //Scanner errors
        errorList.put(0,"Error: invalid character");
        errorList.put(1,"Error: eof in string");
        errorList.put(2,"Error: eol in string\n\twhere eof is an end-of-file.\n\t\teol is an end-of-line: a carriage return ('\\r') or a line feed ('\\n').");

        //Parser errors
        errorList.put(3,"Error: Instructions with inherent mode addressing do not have an operand field."); //DONE
        //TODO: Done but may need to traverse IR to check for missing operands (what if you dont add a label or comment
        // after the immediate addressing mnemonic and start adding to the next line?)
        errorList.put(4,"Error: This immediate instruction must have a number as an operand field.");
        errorList.put(5,"Error: The immediate instruction 'enter.u5' must have a 5-bit unsigned operand number ranging from 0 to 31."); //DONE
        errorList.put(6,"Error: The immediate instruction 'ldc.i3' must have a 3-bit signed operand number ranging from -4 to 3."); //DONE
        errorList.put(7,"Error: The immediate instruction 'ldv.u3' must have a 3-bit unsigned operand number ranging from 0 to 7."); //DONE
    }

    //Adds an error from Scanner to reportedErrors
    public void addError(int errNum, int line, int col) {
        recordedErrors.add(errorList.get(errNum));
        recordedErrors.add(String.format("Line Number: %s1  Column Number: %s2", line, col));
        recordedErrors.add("\n");
    }

    //Adds an error from Parser to reportedErrors
    public void addError(int errNum, IToken token) {
        recordedErrors.add(errorList.get(errNum));
        recordedErrors.add(String.format("Line Number: %s1  Column Number: %s2", token.getPosition().getLineNumber(), token.getPosition().getColumnNumber()));
        recordedErrors.add("\n");
    }

    //Print the errors to console
    public void reportErrors() {
        if (recordedErrors.size() != 0) {
            for (String error: recordedErrors)
                System.out.print(error);
        }
    }
}

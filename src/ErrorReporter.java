import java.util.ArrayList;
import java.util.HashSet;

//Records and reports errors caught by the cross assembler
public class ErrorReporter implements IErrorReporter {
    //String comprised of recorded errors
    ArrayList<ErrorMsg> recordedErrors;

    //Dictionary of invalid characters
    HashSet<Integer> invalidChars;

    //Name of input file
    String fileName;

    //Default constructor
    public ErrorReporter(String fileName){
        //Initialize reportedErrors and errorList
        recordedErrors = new ArrayList<>();
        this.fileName = fileName;

        //Create a dictionary of invalid characters to be detected by the Scanner
        invalidChars = new HashSet<>();
        fillInvalidChars();
    }

    //Adds an error to the list of recorded errors
    public void record(ErrorMsg error) {
        recordedErrors.add(error);
    }

    //Print the errors to console
    public void report() {
        int numErrors = recordedErrors.size();
        if (numErrors != 0) {
            for (ErrorMsg error: recordedErrors)
                System.out.printf("%s: %s%n", fileName, error.getErrorMsg());
            System.out.printf("\n\n\n\n%s Errors Found.%n",numErrors);
        }
    }

    //Fill hashmap of invalid characters
    public void fillInvalidChars(){
        for(int i =0; i < 32; i++){
            if (i == 10 || i == 13){
                continue;
            }
            else{
                invalidChars.add(i);
            }
        }
        invalidChars.add(127);
    }

    //Check if a character is valid
    public boolean isValid(char c) {
        //Return false if non-valid character is detected
        if(invalidChars.contains((int) c)){
            return false;
        }
        return true;
    }

}

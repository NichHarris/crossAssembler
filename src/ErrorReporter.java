import java.util.ArrayList;
import java.util.HashMap;
//Records and reports errors caught by the cross assembler
public class ErrorReporter implements IErrorReporter {
    //String comprised of recorded errors
    ArrayList<ErrorMsg> recordedErrors;

    //Name of input file
    String fileName;

    //Default constructor
    public ErrorReporter(String fileName){
        //Initialize reportedErrors and errorList
        recordedErrors = new ArrayList<>();
        this.fileName = fileName;
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
                System.out.println(String.format("%s1: %s2", fileName, error.getErrorMsg()));
            System.out.println(String.format("\n\n\n\n%s Errors Found.",numErrors));
        }
    }
}

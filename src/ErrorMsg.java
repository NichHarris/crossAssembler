//Represents an error message recorded by the Error Reporter
public class ErrorMsg implements IErrorMsg {

    //Error message to be displayed
    String errorMsg;

    //Components of the error msg
    int line;
    int column;
    String error;

    //Parametrized constructor
    public ErrorMsg(String msg, Position position) {
        //Get the line number, column number and error
        line = position.getLineNumber();
        column = position.getColumnNumber();
        error = msg;

        //Set the error message
        errorMsg = String.format("Error: Line %s, Column %s: %s\n", line, column, error);
    }

    //Parametrized constructor
    public ErrorMsg(String msg, int line, int col) {
        //Get the line number, column number and error
        this.line = line;
        column = col;
        error = msg;

        //Set the error message
        errorMsg = String.format("Error: Line %s1, Column %s2: %s3\n", line, column, error);
    }

    //Returns the error message
    public String getErrorMsg() {
        return errorMsg;
    }
}

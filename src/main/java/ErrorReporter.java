package main.java;

import main.interfaces.IErrorMsg;
import main.interfaces.IErrorReporter;

import java.util.ArrayList;
import java.util.HashSet;

//Records and reports errors caught by the cross assembler
public class ErrorReporter implements IErrorReporter {
    //String comprised of recorded errors
    ArrayList<IErrorMsg> recordedErrors;

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
    public void record(IErrorMsg error) {
        recordedErrors.add(error);
    }

    //Print the errors to console
    public void report() {
        int numErrors = recordedErrors.size();
        if (numErrors != 0) {
            for (IErrorMsg error: recordedErrors)
                System.out.printf("%s: %s%n", fileName, error.getErrorMsg());
            if (numErrors == 1) {
                System.out.printf("\n%s Error Found.%n",numErrors);
            } else {
                System.out.printf("\n%s Errors Found.%n",numErrors);
            }
        }
    }

    //Fill hashmap of invalid ASCII characters in decimal format
    private void fillInvalidChars(){
        //https://www.ascii-code.com/
        for(int i =0; i < 32; i++){
            switch (i) {
                case 10:    //Line feed
                case 13:    //Carriage return
                    break;
                default:
                    invalidChars.add(i);
                    break;
            }
        }
        for(int i=127;i<256;i++){
            invalidChars.add(i); //Del + all extended ASCII character
        }
    }

    //Check if a character is valid
    public boolean isValid(char c) {
        //Return false if non-valid character is detected
        return !invalidChars.contains((int) c);
    }

    public ErrorMsg getErrorMsg(int i){
        return (ErrorMsg)recordedErrors.get(i);
    }

}

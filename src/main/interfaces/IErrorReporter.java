package main.interfaces;

import main.java.ErrorMsg;

//Interface for ErrorReporter
public interface IErrorReporter {

    //Adds an error to the list of recorded errors
    void record(IErrorMsg error);

    //Print the errors to console
    void report();

    boolean isValid(char c);
}

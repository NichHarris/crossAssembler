/*
    SOEN 341 - Cm Cross-Assembler Version 1.4 - Developed by Team 3.

    Nicholas Kawwas - 40124338
    Matthew Sklivas - 40095150
    Nicholas Harris - 40111093
    Georgia Bardaklis - 40096586
    Karine Chatta - 27894392
    Lina Tran - 40130446
    Vincent Beaulieu - 40062386
    Philippe Lee - 40131559
    Malek Jerbi - 40130983

 */


//Import necessary files and packages
package main.java;
import main.interfaces.IErrorMsg;
import main.interfaces.IPosition;

//Represents an error message recorded by the Error Reporter
public class ErrorMsg implements IErrorMsg {

    //Error message to be displayed
    String errorMsg;

    //Components of the error msg
    private final int line;
    private final int column;
    private final String error;

    //Parametrized constructor
    public ErrorMsg(String msg, IPosition position) {
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
        errorMsg = String.format("Error: Line %s, Column %s: %s\n", line, column, error);
    }

    //Returns the error message
    public String getErrorMsg() {
        return errorMsg;
    }
}

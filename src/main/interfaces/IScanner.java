package main.interfaces;

import main.java.TokenType;

//Interface for Scanner class
public interface IScanner {
    //Scans file character by character given Reader
    IToken scanFile(IReader reader);

    //Get the token type of a token
    TokenType getTokenType(String name, int colNum);

    //Check if token is numeric
    boolean isNumeric(String str);

    //Get the current position in Scanner
    int getCurrPos();

    //Set the current position in Scanner
    void setCurrPos(int pos);

    //Get the Error from ErrorReporter
    IErrorReporter getErrors();
}

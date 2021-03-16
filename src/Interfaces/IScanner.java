package Interfaces;

import main.TokenType;

//Interface for Java.Scanner class
public interface IScanner {
    //Scans file character by character given Java.Reader
    void scanFile(IReader reader);

    //Get the token type of a token
    TokenType getTokenType(String name, int colNum);

    //Check if token is numeric
    boolean isNumeric(String str);

    //Send token to Java.Parser
    void sendToParser();

    //Get the parser
    IParser getParser();
}

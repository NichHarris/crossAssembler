//Interface for Scanner class
public interface IScanner {
    //Scans file character by character given Reader
    void scanFile(IReader reader);

    //Get the token type of a token
    TokenType getTokenType(String name, int colNum);

    //Check if token is numeric
    boolean isNumeric(String str);

    //Send token to Parser
    void sendToParser();

    //Get the parser
    IParser getParser();

    //Print error recorded by ErrorReporter (if there are any)
    void reportErrors();

    //Check if character is valid
    void isValid(char c);
}

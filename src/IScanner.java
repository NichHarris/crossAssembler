//Interface for Scanner class
public interface IScanner {
    //Scans file character by character given Reader
    IToken scanFile(IReader reader);

    //Get the token type of a token
    TokenType getTokenType(String name, int colNum);

    //Check if token is numeric
    boolean isNumeric(String str);

    //Gets the current position in Scanner
    int getCurrPos();

    //Sets the current position in Scanner
    void setCurrPos(int pos);

    //Print error recorded by ErrorReporter (if there are any)
    void reportErrors();
}

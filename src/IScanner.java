//Interface for Scanner class
public interface IScanner {
    //Scans file character by character given Reader
    IToken scanFile(IReader reader);

    //Get the token type of a token
    TokenType getTokenType(String name, int colNum);

    //Check if token is numeric
    boolean isNumeric(String str);

    //Fill hashmap of invalid characters
    void fillInvalidChars();

    //Check if character is valid
    boolean isValid(char c);

    //Get the current position in Scanner
    int getCurrPos();

    //Set the current position in Scanner
    void setCurrPos(int pos);
}

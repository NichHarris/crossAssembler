import java.util.ArrayList;

//Interface for Scanner class
public interface IScanner {

    //Get the token type of a token
    TokenType getTokenType(String name, int colNum);

    //Check if token is numeric
    boolean isNumeric(String str);

    //Send token to Parser
    void sendToParser();

    //Get the parser
    IParser getParser();
}

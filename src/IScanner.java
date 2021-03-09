import java.util.ArrayList;

//Interface for Scanner class
public interface IScanner {

    //Returns list of tokens
    ArrayList<ArrayList<String>> getTokens();

    //Returns list of comments
    String[] getComments();

    void sendToParser();

    //Print method for checking tokens
    void printTokens();
}

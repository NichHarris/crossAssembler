import java.util.ArrayList;

//Interface for Scanner class
public interface IScanner {

    //Returns list of tokens
    ArrayList<String[]> getTokens();

    //Returns list of comments
    String[] getComments();

    //Print method for checking tokens
    void printTokens();
}

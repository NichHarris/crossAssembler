import java.util.ArrayList;
import java.util.Arrays;

//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    private final ArrayList<String[]> tokens;
    private final String[] comments;

    void toChar(){
        String temp =
    }

    //Parametrized constructor
    public Scanner(String[] assemblyUnit) {
        //Initialize ArrayList and String array for the list of tokens and list of comments, respectively
        tokens = new ArrayList<String[]>(assemblyUnit.length);
        comments = new String[assemblyUnit.length];


        //Traverse the assembly unit and scan for tokens/comments
        for(int i = 0; i < assemblyUnit.length; i++) {
            //Ignore Comments, Remove Extra WhiteSpace Then Split into SubComponents
            tokens.add(i,assemblyUnit[i].split(";")[0].trim().split("\\s+"));
            comments[i] = assemblyUnit[i].contains(";") ? assemblyUnit[i].split(";")[1].trim() : "";
        }

        /*
        TODO: Treat character per character

        ASCII TABLE:
            char      dec
            SPACE   : 32
              ;     : 59
              .     : 46
            A..Z    : 65..90
            a..z    : 97..122
            1..9    : 48..9

         */
    }

    //Returns list of tokens
    public ArrayList<String[]> getTokens() {
        return tokens;
    }

    //Returns list of comments
    public String[] getComments() {
        return comments;
    }

    //Print method for checking tokens
    public void printTokens() {
        for (String[] line: tokens){
            System.out.print(Arrays.toString(line));
        }
    }
}

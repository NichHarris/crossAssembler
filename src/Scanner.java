import java.util.ArrayList;
import java.util.Arrays;

//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    private final int index;
    private final ArrayList<ArrayList<String>> tokens;
    private String token="";
    private final String[] comments;

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
    //scan from lhs of the line and divide until ";" or "EOL"
    //


    //Parametrized constructor
    public Scanner(ArrayList<String> assemblyUnit) {
        //Initialize the index to 0
        index = 0;

        //Initialize ArrayList and String array for the list of tokens and list of comments, respectively
        tokens = new ArrayList<ArrayList<String>>(assemblyUnit.size());
        comments = new String[assemblyUnit.size()];

        boolean isSpc = false;
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < assemblyUnit.size(); i++) {
            String token = assemblyUnit.get(i);
            int length = token.length();
            int count = 0;
            for(int j = 0; j < length; j++)
                if (token.charAt(j) == ';')
                    comments[i] = token.substring(j, length - 1);
                else if(token.charAt(j) == ' ' && !status) {
                    status = true;
                    tokens.add(count, temp);
                    count++;
                } else {
                    status = false;
                    temp.add(count, temp.get(count) + token.charAt(j));
                }
            temp.clear();
        }

/*
        //Traverse the assembly unit and scan for tokens/comments
        for(int i = 0; i < assemblyUnit.size(); i++) {
            //Ignore Comments, Remove Extra WhiteSpace Then Split into SubComponents
            //tokens.add(i,assemblyUnit[i].split(";")[0].trim().split("\\s+"));
            //comments[i] = assemblyUnit[i].contains(";") ? assemblyUnit[i].split(";")[1].trim() : "";
        }
*/
    }



    //Returns list of tokens
    public ArrayList<ArrayList<String>> getTokens() {
        return tokens;
    }

    //Returns list of comments
    public String[] getComments() {
        return comments;
    }

    //Print method for checking tokens
    public void printTokens() {
        for (ArrayList<String> line: tokens){
            System.out.print(Arrays.toString(line));
        }
    }
}

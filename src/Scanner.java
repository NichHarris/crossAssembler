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

        boolean isSpace = false;
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < assemblyUnit.size(); i++) {
            String str = "";
            String token = assemblyUnit.get(i);
            int length = token.length();

            for(int j = 0; j < length; j++)
                if (token.charAt(j) == ';') {
                    comments[i] = token.substring(j, length);
                    break;
                } else if(token.charAt(j) == ' ' && !isSpace || j == length - 1) {
                    isSpace = true;
                    temp.add(str);
                    str = "";
                } else {
                    isSpace = false;
                    str += token.charAt(j);
                }

            tokens.add(temp);
            temp.clear();
        }
        for(ArrayList<String> a: tokens)
            for(String s: a)
                System.out.println(s);

        //Ignore Comments, Remove Extra WhiteSp
/*
        //Traverse the assembly unit and scan for tokens/comments
        for(int i = 0; i < assemblyUnit.size(); i++) {
          ace Then Split into SubComponents
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
            for(String s: line){
                System.out.print(s);
            }
        }
    }
}

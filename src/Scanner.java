import java.util.ArrayList;
import java.util.Arrays;

//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    private final ArrayList<ArrayList<String>> tokens;
    private final String[] comments;

    //Parametrized constructor
    public Scanner(ArrayList<String> assemblyUnit) {
        //Initialize ArrayList and String array for the list of tokens and list of comments, respectively
        tokens = new ArrayList<ArrayList<String>>(assemblyUnit.size());
        comments = new String[assemblyUnit.size()];

        boolean isSpace = false;
        ArrayList<String> temp;

        //Traverse the assembly unit and scan for tokens/comments
        for(int i = 0; i < assemblyUnit.size(); i++) {
            String str = "";
            temp = new ArrayList<String>();

            String token = assemblyUnit.get(i);
            int length = token.length();

            for(int j = 0; j < length; j++)
                //Ignore Comments
                if (token.charAt(j) == ';') {
                    comments[i] = token.substring(j, length);
                    break;
                //Remove White Space and Split Into Tokens
                } else if(token.charAt(j) == ' ' || token.charAt(j) == '\t') {
                    if(!isSpace) {
                        isSpace = true;
                        temp.add(str);
                        str = "";
                    }
                //Check for EOL
                } else if(j == length - 1) {
                    temp.add(str + token.charAt(j));
                } else {
                    //Append token
                    isSpace = false;
                    str += token.charAt(j);
                }

            //Add
            tokens.add(temp);
        }

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
        System.out.println(tokens);
    }
}

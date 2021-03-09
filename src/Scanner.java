import java.util.ArrayList;
import java.util.Arrays;

//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    //private final ArrayList<ArrayList<String>> tokens;
    //private final String[] comments;
    private String tkn;
/*
    //Parametrized constructor
    public Scanner(ArrayList<String> assemblyUnit) {
        //Initialize ArrayList and String array for the list of tokens and list of comments, respectively
        tokens = new ArrayList<ArrayList<String>>(assemblyUnit.size());
        comments = new String[assemblyUnit.size()];

        ArrayList<String> temp;
        boolean isSpace;

        //Traverse the assembly unit and scan for tokens/comments
        for(int i = 0; i < assemblyUnit.size(); i++) {
            String str = "";
            temp = new ArrayList<String>();

            String token = assemblyUnit.get(i);
            int length = token.length();
            isSpace = true;

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
*/
    public Scanner(String token) {
        tkn = token;
    }

    public void scanning(String line, int lineNum) {
        String tkn = "";
        int colNum = 0;

        // Scanner Flags
        boolean isSpace, isEOL;
        boolean isComment = false;

        //Seperates Lines into Tokens
        for(char c : line.toCharArray) {
            isEOL = c == '\r' || c == '\n';
            isSpace = c == ' ' || c == '\t';

            //Create tokens when space or new line is detect
            if (isSpace && !isComment || isEOL)  {
                sendToParser(tkn, lineNum, colNum);
                //Comment Detected
            } else if(c == ';') {
                isComment = true;

                //Adds Character By Character to Token
                tkn += c;
            }
    }

    public void sendToParser(String tkn, int lineNum, int colNum) {
            //IToken token = new Token(new Position(lineNum, colNum), tkn, "" );
        }
        if (tkn != "") Parser(tkn, lineNum, colNum++);

        tkn = "";
    }
}

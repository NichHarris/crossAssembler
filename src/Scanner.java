//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    //private final ArrayList<ArrayList<String>> tokens;
    //private final String[] comments;
    private IReader file;
    private IToken token;
    private String buffer;
    private String content;
    private String tokenStr;
    private SymbolTable symbolTable;
    private int numLines;
    private int lineNum;
    private int colNum;
    private IParser parser;
    private TokenType tokenType;
    private boolean isSpace = false;
    private boolean isEOL = false;
    private boolean isComment = false;
    private boolean spaceBeforeEOL = false;

    public Scanner(Reader file) {
        //Reader file
        this.file = file;
        //Symbol table
        this.symbolTable = new SymbolTable();
        //Buffer for obtaining tokens
        this.buffer = "";
        //Number of lines in file
        this.numLines = file.getLineNum();
        //Input file content as a String
        String fileContent = file.getFileContent();
        //Line number
        this.lineNum = 0;
        //Column number
        this.colNum = 0;
        //Instance of Parser
        this.parser = new Parser(numLines);

//        //Scanner Flags
//        boolean isSpace, isEOL;
//        boolean isComment = false;
//        boolean spaceBeforeEOL = false;

        // halt \r\n
        //
        //Traverse the file content character per character and scan for tokens
        for (int i = 0; i < fileContent.length(); i++) {
            //Adds Character By Character to Token
            char c = file.getChar(i);

            //Character type flags
            isEOL = c == '\r' || c == '\n';
            isSpace = c == ' ' || c == '\t';

            //Case where EOF is reached
            if (spaceBeforeEOL && isEOL) {
                newLine();
            }
            else if (isEOL && buffer != "") {
                //Get token and send to parser
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                sendToParser();

                newLine();
            }
            //Failsafe for second EOL character
            else if (isEOL && buffer == "") {
                isEOL = false;
            }
            //Failsafe for multiple space characters
            else if (isSpace && buffer == ""){
                isSpace = false;
            }
            //If space detected (and not a comment), create a token, increment column number and clear buffer
            else if (isSpace && !isComment) {
                if (buffer != "") {
                    tokenType = this.getTokenType(buffer, colNum);
                    token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                    sendToParser();
                    colNum += 1;
                    isSpace = false;
                    spaceBeforeEOL = true;
                    buffer = "";
                }
            }
            //Keep adding to buffer if comment detected
            else if (isComment) {
                buffer = buffer + (c);
            }
            //Comment detected
            else if (c == ';') {
                isComment = true;
                buffer = buffer + (c);
            }
            // Keep adding to buffer
            else {
                buffer = buffer + (c);
            }
        }
    }

    public void newLine(){
        //Increment line number and reset column number
        lineNum++;
        colNum = 0;

        //Reset flags
        isEOL = false;
        isSpace = false;
        isComment = false;
        spaceBeforeEOL = false;
        buffer = "";
    }

    //Get the token type of a token
    //TODO: to be improved for edge cases + error reporter
    public TokenType getTokenType(String name, int colNum) {

        //Get the opcode of the token
        int code = symbolTable.getCode(name);

        //Check if mnemonic
        if(code != -1){
            return TokenType.Mnemonic;
        }
        //Check if operand
        else if (isNumeric(name)) {
            return TokenType.LabelOperand;
        }
        //Check if label
        else if (colNum == 0 && !isNumeric(name)) {
            return TokenType.Label;
        }

        return null;
    }

    //Check if token is numeric
    public boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    //Send token to Parser
    public void sendToParser() {
        parser.parseToken(token);
        //if (buffer != "")
        //buffer = "";
    }

    public IParser getParser() {
        return parser;
    }
}

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
    /*
    public void scan(String line, int lineNum) {
        tokenStr = "";
        int colNum = 0;

        // Scanner Flags
        boolean isSpace, isEOL;
        boolean isComment = false;

        //Seperates Lines into Tokens
        for(char c : line.toCharArray()) {
            isEOL = c == '\r' || c == '\n';
            isSpace = c == ' ' || c == '\t';

            //Create tokens when space or new line is detected
            if (isSpace && !isComment || isEOL) {
                token = new Token(new Position(lineNum, colNum), tokenStr, null);
                sendToParser();
                colNum += 1; //TODO: need to handle reset of colNum
            //Comment Detected
            } else if (c == ';')
                isComment = true;

            //Adds Character By Character to Token
            tokenStr += c;
        }
    }
    */

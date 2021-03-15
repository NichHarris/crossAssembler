//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    private IToken token;
    private TokenType tokenType;

    private IParser parser;
    private SymbolTable symbolTable;

    private String buffer;
    private int numLines;
    private int lineNum, colNum;

    private boolean isSpace = false, isEOL = false;
    private boolean isComment = false, spaceBeforeEOL = false;

    public Scanner() {
        //Buffer for obtaining tokens
        buffer = "";

        //Initialize Line & Col number
        lineNum = 0; colNum = 0;

        //Create instance of Symbol table
        symbolTable = new SymbolTable();
    }

    //Scans file character by character given Reader object
    public void scanFile(IReader file) {
        //Input file content as a String
        String fileContent = file.getFileContent();

        //Number of lines in file
        numLines = file.getLineNum();

        //Instance of Parser
        parser = new Parser(numLines + 1);

        //Traverse the file content character per character and scan for tokens
        for (int i = 0; i < fileContent.length(); i++) {
            //Adds Character By Character to Token
            char c = file.getChar(i);

            //Character type flags
            //wasEOL = isEOL;
            isEOL = c == '\r' || c == '\n';
            isSpace = c == ' ' || c == '\t';

            if (i == fileContent.length() - 1){
                if (c != '\r' && c != '\n'){
                    //Get token and send to parser
                    buffer = buffer + (c);
                    tokenType = this.getTokenType(buffer, colNum);
                    token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                    sendToParser();

                    newLine();
                }
            }

            if (spaceBeforeEOL) {
                if (isEOL)
                    continue;
                else
                    spaceBeforeEOL = false;
            }

            //Case where EOF is reached
            if (spaceBeforeEOL && isEOL) {
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                sendToParser();
                newLine();
            } else if (isEOL && buffer != "") {
                //Get token and send to parser
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                sendToParser();

                newLine();
            } else if (isEOL && colNum == 0){
                newLine();
            }
            //Failsafe for second EOL character
            else if (isEOL && buffer == "") {
                isEOL = false;
            }
            //Failsafe for multiple space characters
            else if (isSpace && buffer == "") {
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
                spaceBeforeEOL = false;
                buffer = buffer + (c);
            }
            // Keep adding to buffer
            else {
                buffer = buffer + (c);
            }
        }
    }

    public void newLine() {
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
        if(code != -1)
            return TokenType.Mnemonic;
        //Check if operand
        else if (isNumeric(name))
            return TokenType.LabelOperand;
        //Check if comment
        else if (isComment)
            return TokenType.Comment;
        //Check if label ?? Does col need to be 0?
        else if (colNum == 0 && !isNumeric(name))
            return TokenType.Label;

        return TokenType.None;
    }

    //Check if token is numeric
    public boolean isNumeric(String str) {
        if (str.length() == 0)
            return false;

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            //Check if character is outside number range
            if(c < 48 || c > 57)
                //Check if negative number (-)
                if (i == 0 && c == 45)
                    continue;
                else
                    return false;
        }

        return true;
    }

    //Send token to Parser
    public void sendToParser() {
        parser.parseToken(token);
    }

    public IParser getParser() {
        return parser;
    }
}

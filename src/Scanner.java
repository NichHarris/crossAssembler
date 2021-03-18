import java.util.HashMap;

//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    private IToken token;
    private TokenType tokenType;

    private IParser parser;
    private SymbolTable symbolTable;

    private String buffer;
    private int numLines;
    private int lineNum, colNum;

    private boolean isSpace, isEOL, isComment, checkNext, checkPrev = false;

    private IErrorReporter errorReporter;
    private final HashMap<Integer, Integer> invalidChars;

    public Scanner() {
        //Buffer for obtaining tokens
        buffer = "";

        //Initialize Line & Col number
        lineNum = 0;
        colNum = 0;
        //Create instance of Symbol table
        symbolTable = new SymbolTable();

        //Create an ErrorReporter
        errorReporter = new ErrorReporter();

        invalidChars = new HashMap<Integer, Integer>();
        fillInvalidChars();
    }

    //Scans file character by character given Reader object
    public void scanFile(IReader file) {
        //Input file content as a String
        String fileContent = file.getFileContent();
        //Number of lines in file
        numLines = file.getLineNum();

        //Instance of Parser
        parser = new Parser(numLines + 1);

        int eolCounter = 0;

        //Traverse the file content character per character and scan for tokens
        for (int i = 0; i < fileContent.length(); i++) {
            //Adds Character By Character to Token
            char c = file.getChar(i);

            /*
            //TODO: Needs fixing
            //Case where a \r appeared and the following char isn't \n
            if(checkNext && c != '\n'){
                errorReporter.addError(2, lineNum, colNum);
                //Reset Flags
                checkNext = false;
                checkPrev = false;
            }
            //Case where a \n appears but \r was not the previous char
            if(checkPrev && !checkNext){
                errorReporter.addError(2, lineNum, colNum);
                //Reset Flags
                checkNext = false;
                checkPrev = false;
            }
            */

            //Character type flags
            //EOL format is \r\n
            isEOL = c == '\r' || c == '\n';
            isSpace = c == ' ' || c == '\t';

            //Check if character is valid or not, and report error if not
            isValid(c);

            //Check if an EOF character is found anywhere other than the end of file
            if (i != fileContent.length() - 1 && (int) c == 26){
                System.out.println(i);
                errorReporter.addError(1, lineNum, colNum);
            }

            //Counts number of EOL characters in a row
            eolCounter = isEOL ? eolCounter + 1 : 0;

            //If space and buffer is not empty and not a comment - send to parser
            if (isSpace && buffer != "" && !isComment) {
                //Send to Parser
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                sendToParser();

                colNum += 1;
                buffer = "";
                //If EOL and buffer is not empty - send to parser + new line
            } else if (isEOL && buffer != "") {
                //Send to Parser
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                sendToParser();
                newLine();
                //If more than 2 EOL characters in a row
                //TODO: Fix this sketchiness
            } else if (eolCounter % 2 != 0) {
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                sendToParser();
                newLine();
                //If at last line of file
            } else if (i == fileContent.length() - 1 && !isEOL) {
                //Send toParser
                buffer = buffer + (c);
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                sendToParser();
                newLine();
                //Ignore spaces and extra EOL
            } else if ((isSpace || isEOL) && buffer == "") {
                continue;
                //Add to buffer
            } else {
                buffer += c;

                //Comment detected
                if (c == ';')
                    isComment = true;
            }
        }
    }

    public void newLine() {
        //Increment line number and reset column number
        lineNum++;
        colNum = 0;
        //Reset flag and buffer
        isComment = false;
        buffer = "";
    }

    //Get the token type of a token
    //TODO: to be improved for edge cases + error reporter
    public TokenType getTokenType(String name, int colNum) {
        //Get the opcode of the token
        int code = symbolTable.getCode(name);

        //Check if mnemonic
        if (code != -1)
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
        else if (colNum == 1 || colNum == 2) {
            if (!isNumeric(name))
                return TokenType.LabelOperand;
        }

        return TokenType.None;
    }

    //Check if token is numeric
    public boolean isNumeric(String str) {
        //TODO: Return error for not following grammar
        if (str.length() == 0)
            return false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            //Check if character is outside number range
            if (c < 48 || c > 57)
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

    //Get the instance of Parser
    public IParser getParser() {
        return parser;
    }

    //Print error recorded by ErrorReporter (if there are any)
    public void reportErrors() {
        errorReporter.reportErrors();
    }

    //Fill hashmap of invalid characters
    public void fillInvalidChars(){
        for(int i =0; i < 32; i++){
            if (i == 10 || i == 13){
                continue;
            }
            else{
                invalidChars.put(i, null);
            }
        }
        invalidChars.put(127, null);
    }

    //Check if character is valid
    public void isValid(char c) {
        //Report error when ever non-valid character is detected
        if(invalidChars.containsKey((int) c)){
            errorReporter.addError(0, lineNum, colNum, c);
        }
        switch((int) c){
            case(13):
                checkNext = true;
                break;
            case(10):
                checkPrev = true;
                break;
        }
    }
}

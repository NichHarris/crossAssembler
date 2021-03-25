import java.util.HashMap;
/*
Commented out certain system.out for testing purposes;
would like know if they are required or simply done
for debugging purposes?
 */

//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    private IToken token;
    private TokenType tokenType;

    private ISymbolTable symbolTable;

    private String buffer;
    private int lineNum, colNum;
    private int currPos, eolCounter;
    private boolean isSpace, isEOL, isComment = false;

    private IErrorReporter errorReporter;

    public Scanner(ISymbolTable symTable, IErrorReporter errRep) {
        //Buffer for obtaining tokens
        buffer = "";

        //Initialize Line & Col number
        lineNum = 0;
        colNum = 0;
        currPos = 0;
        eolCounter = 0;

        //Create instance of Symbol table
        symbolTable = symTable;

        //Create an ErrorReporter
        errorReporter = errRep;
    }

    //Scans file character by character given Reader object
    public IToken scanFile(IReader file) {
        //Input file content as a String
        String fileContent = file.getFileContent();
        //Number of lines in file
        //numLines = file.getLineNum();

        buffer = "";
        //Traverse the file content character per character and scan for tokens
        for (int i = currPos; i < fileContent.length(); i++) {
            //Adds Character By Character to Token
            char c = file.getChar(i);

            isEOL = c == '\r' || c == '\n';
            isSpace = c == ' ' || c == '\t';

            //Check if character is valid or not, and report error if not
            if (!errorReporter.isValid(c))
                errorReporter.record(new ErrorMsg("Invalid character\n", lineNum, colNum));

            //Check if eol in string
            if(!buffer.equals("") && c == '\n')
                errorReporter.record(new ErrorMsg("EOL in String\n", lineNum, colNum));

            //Check if an EOF character is found anywhere other than the end of file
            if (i != fileContent.length() - 1 && (int) c == 26)
                errorReporter.record(new ErrorMsg("EOF in String\n", lineNum, colNum));

            //Counts number of EOL characters in a row
            eolCounter = isEOL ? eolCounter + 1: 0;

            //If space and buffer is not empty and not a comment - send to parser
            if (i == fileContent.length() - 1) {
                if (!buffer.equals("")) {
                    buffer = buffer + (c);
                    tokenType = this.getTokenType(buffer, colNum);
                    token = new Token(new Position(lineNum, colNum), buffer, tokenType);

                    currPos = ++i;
                    return token;
                }
            }
            else if(isSpace && !buffer.equals("") && !isComment) {
                //Send to Parser
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                colNum += 1;

                currPos = i;
                return token;
            //If EOL and buffer is not empty - send to parser + new line
            } else if (isEOL && !buffer.equals("")) {
                //Send to Parser
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                colNum += 1;
                currPos = i;
                isComment = false;
                return token;
            //Add to buffer
            //If more than 2 EOL characters in a row
            } else if (eolCounter >= 2) {
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                //System.out.println("HERE EOL: " + eolCounter + " Line Num:" + lineNum);
                currPos = ++i;
                newLine();
                return token;
            //If at last line of file
            } else if((isSpace || isEOL) && buffer.equals("")) {
                    continue;
            } else {
                buffer += c;

                //Comment detected
                if (c == ';')
                    isComment = true;
            }
        }

        System.out.println("END OF FILE");
        return null;
    }

    public void newLine() {
        //Increment line number and reset column number
        lineNum++;
        colNum = 0;
        //Reset flag and buffer
        isComment = false;
        eolCounter = 0;
        buffer = "";
    }

    //Get the token type of a token
    //TODO: to be improved for edge cases + error reporter
    public TokenType getTokenType(String name, int colNum) {
        //Get the opcode of the token
        int code = symbolTable.getCode(name);

        //Check if directive
        boolean isDirective = name.equals(".cstring");

        //Check if mnemonic
        if (code != -1 || isDirective)
            return TokenType.Mnemonic;
            //Check if operand
        else if (isNumeric(name))
            return TokenType.Operand;
            //Check if comment
        else if (isComment)
            return TokenType.Comment;
            //Check if label
        else if (colNum == 0 && !isNumeric(name) && !name.equals(""))
            return TokenType.Label;
            //Check if label in operand position
        else if (colNum == 1 || colNum == 2)
            if (!isNumeric(name))
                return TokenType.LabelOperand;
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

    //Get the current position in Scanner
    public int getCurrPos(){
        return currPos;
    }

    //Set the current position in Scanner
    public void setCurrPos(int pos){
        currPos = pos;
    }
}

import java.util.HashMap;

//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {
    private IToken token;
    private TokenType tokenType;

    private ISymbolTable symbolTable;

    private String buffer;
    private int numLines;
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
        numLines = file.getLineNum();

        buffer = "";

        //Traverse the file content character per character and scan for tokens
        for (int i = currPos; i < fileContent.length(); i++) {
            //Adds Character By Character to Token
            char c = file.getChar(i);

            //Character type flags
            //EOL format is \r\n
            isEOL = c == '\r' || c == '\n';
            isSpace = c == ' ' || c == '\t';

            //Check if character is valid or not, and report error if not
//            errorReporter.isValid(c, lineNum, colNum);

//            //Check if eol in string
//            if(buffer != "" && c == '\n'){
//                errorReporter.addError(2,lineNum, colNum);
//            }
//
//            //Check if an EOF character is found anywhere other than the end of file
//            //TODO: Not sure if this works
//            if (i != fileContent.length() - 1 && (int) c == 26) {
//                errorReporter.addError(1, lineNum, colNum);
//            }

            //Counts number of EOL characters in a row
            eolCounter = isEOL ? eolCounter + 1 : 0;
            //System.out.println(eolCounter);

            //If space and buffer is not empty and not a comment - send to parser
            if (isSpace && buffer != "" && !isComment) {
                //Send to Parser
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);

                colNum += 1;
                currPos = i++;

                System.out.println("Current Position: " + currPos + ", Value: " + buffer);

                return token;
            //If EOL and buffer is not empty - send to parser + new line
            } else if (isEOL && buffer != "") {
                //Send to Parser
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);

                currPos = i++;
                System.out.println("Current Position: " + currPos + ", Value: " + buffer);

                newLine();

                return token;
            //If more than 2 EOL characters in a row
            //TODO: Fix this sketchiness
            } else if (eolCounter % 2 != 0) {
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);

                currPos = i++;
                System.out.println("Current Position: " + currPos + ", Value: " + buffer);

                newLine();

                return token;
            //If at last line of file
            } else if (i == fileContent.length() - 1 && !isEOL) {
                //Send toParser
                buffer = buffer + (c);
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);

                currPos = i++;
                System.out.println("Current Position: " + currPos + ", Value: " + buffer);

                newLine();

                return token;
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


        System.out.println("END OF FILE");

        //System.out.println("Current Position: " + currPos + ", Value: " + buffer);
//        tokenType = this.getTokenType(buffer, colNum);
//        token = new Token(new Position(lineNum, colNum), buffer, tokenType);
        return null;
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
        else if (colNum == 0 && !isNumeric(name) && name != "")
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
//    public void sendToParser() {
//        parser.parseToken(token);
//    }
//
//    //Get the instance of Parser
//    public IParser getParser() {
//        return parser;
//    }

    //Print error recorded by ErrorReporter (if there are any)
    public void reportErrors() {
        errorReporter.reportErrors();
    }

    public int getCurrPos(){
        return currPos;
    }

    public void setCurrPos(int pos){
        currPos = pos;
    }

}



/*
    SOEN 341 - Cm Cross-Assembler Version 1.4 - Developed by Team 3.

    Nicholas Kawwas - 40124338
    Matthew Sklivas - 40095150
    Nicholas Harris - 40111093
    Georgia Bardaklis - 40096586
    Karine Chatta - 27894392
    Lina Tran - 40130446
    Vincent Beaulieu - 40062386
    Philippe Lee - 40131559
    Malek Jerbi - 40130983

 */


//Import necessary files and packages
package main.java;
import main.interfaces.*;

//Scanner - Performs Lexical Analysis on the assembly unit
public class Scanner implements IScanner {

    private final ISymbolTable symbolTable;

    private String buffer;
    private int lineNum, colNum;
    private int currPos, eolCounter;
    private boolean isComment = false;

    private final IErrorReporter errorReporter;

    public IErrorReporter getErrors(){
        return errorReporter;
    }

    public Scanner(ISymbolTable symTable, IErrorReporter errRep) {
        //Buffer for obtaining tokens
        buffer = "";

        //Initialize line & col number
        lineNum = 0;
        colNum = 0;
        currPos = 0;
        eolCounter = 0;

        //Create instance of symbolTable
        symbolTable = symTable;

        //Create an ErrorReporter
        errorReporter = errRep;
    }

    //Scans file character by character given Reader object
    public IToken scanFile(IReader file) {
        //Input file content as a String
        String fileContent = file.getFileContent();

        buffer = "";
        //Traverse the file content character per character and scan for tokens
        for (int i = currPos; i < fileContent.length(); i++) {
            //Adds character by character to token
            char c = file.getChar(i);

            boolean isEOL = c == '\r' || c == '\n';
            boolean isSpace = c == ' ' || c == '\t';

            //Check if character is valid or not, and report error if not
            if (!errorReporter.isValid(c)) {
                errorReporter.record(new ErrorMsg("Invalid character\n", lineNum, colNum));
            }

            //Check if EOL in string
            if (buffer.contains("\n")) {
                errorReporter.record(new ErrorMsg("EOL in String\n", lineNum, colNum));
            }

            //Check if an EOF character is found anywhere other than the end of file
            if (i != fileContent.length() - 1 && (int) c == 26) {
                errorReporter.record(new ErrorMsg("EOF in String\n", lineNum, colNum));
            }

            //Counts number of EOL characters in a row
            eolCounter = c == '\n' ? eolCounter + 1 : 0;

            //If at last line of file
            IToken token;
            TokenType tokenType;
            if (i == fileContent.length() - 1) {
                if ((!isSpace || isComment) && !isEOL)
                    buffer += c;
                tokenType = this.getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);

                currPos = ++i;
                return token;
            //If space and buffer is not empty and not a comment - send to parser
            } else if (isSpace && !buffer.equals("") && !isComment) {
                tokenType = getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                colNum += 1;
                currPos = i;
                return token;
                //If EOL and buffer is not empty - send to parser + new line
            } else if (isEOL && !buffer.equals("")) {
                tokenType = getTokenType(buffer, colNum);
                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                colNum += 1;
                currPos = i;
                isComment = false;
                return token;
            //If 2 or more EOL characters in a row
            } else if (eolCounter >= 1) {
                tokenType = getTokenType(buffer, colNum);

                token = new Token(new Position(lineNum, colNum), buffer, tokenType);
                currPos = ++i;
                newLine();
                return token;
            //Ignore spaces and extra EOL chars
            } else if ((isSpace || isEOL) && buffer.equals("")) {
                continue;
            //Add char to buffer
            } else {
                buffer += c;

                //Comment detected
                if (c == ';')
                    isComment = true;
            }
        }

        return null;
    }

    //Called when a newline is detected
    public void newLine(){
        //Increment line number and reset column number
        lineNum++;
        colNum = 0;
        //Reset flag and buffer
        isComment = false;
        eolCounter = 0;
        buffer = "";
    }

    //Get the token type of a token
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
            if (!isNumeric(name) && !name.equals(""))
                return TokenType.LabelOperand;
        return TokenType.None;
    }

    //Check if token is numeric
    public boolean isNumeric(String str) {
        if (str.length() == 0)
            return false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            //Check if character is outside number range
            if (c < 48 || c > 57)
                //Check if negative number (-)
                if(i == 0 && c == 45)
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
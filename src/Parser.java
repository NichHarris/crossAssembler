import java.util.Locale;

//Parser - Performs analysis the syntax of tokens and generates the correct IR
public class Parser implements IParser {
    private IInterRep interRep;
    private ILineStatement line;

    private IInstruction instr;

    private ISymbolTable symbolTable;
    private IErrorReporter errorReporter;
    private IScanner scanner;
    private IReader reader;

    private int currLine;

    //Parser constructor initializes IR using number of lines from Reader
    public Parser(int len, ISymbolTable symTable, IErrorReporter errRep, IScanner scnr, IReader rdr) {
        interRep = new InterRep(len);

        //Create an instance of Symbol table
        symbolTable = symTable;

        //Create an instance of ErrorReporter
        errorReporter = errRep;

        //Create an instance of Scanner
        scanner = scnr;

        //Create an instance of Reader
        reader = rdr;

        //Create an instance of LineStatement
        line = new LineStatement();
        currLine = 0;
    }

    //Parse a token received from Scanner
    public void parseToken(){
        IToken tk;

        do {
            tk = scanner.scanFile(reader);
            parseToIR(tk);

            //addToIR(tk);
        } while(scanner.getCurrPos() != reader.getFileContent().length() - 1);

        interRep.addLine(currLine, line);
    }


    //Parses to IR
    public void parseToIR(IToken token) {
        //Get column and line number from token
        int lineN = token.getPosition().getLineNumber();
        int colN = token.getPosition().getColumnNumber();

        //Add to InterRep completed line and create empty line
        if(currLine < lineN) {
            interRep.addLine(currLine++, line);
            //System.out.println("Line: " + (lineN - 1) + ", " + interRep.getLine(lineN - 1).toString());
            line = new LineStatement();
        }

        //Get opcode from Symbol Table
        int code = symbolTable.getCode(token.getName());

        //Adds to LineStatement
        switch(token.getCode()) {
            //Add Label to Line
            case Label:
                line.setLabel(token.getName());
                break;
            //Add Mnemonic to Line
            case Mnemonic:
                if (token.getName().equals(".cstring"))
                    line.setDirective(new Directive(token.getName(), ""));
                else
                    line.setInstruction(new Instruction(new Mnemonic(token.getName(), code), null));
                break;
            //Add Operand/Label to Line
            case Operand:
            case LabelOperand:
                if (line.getDirective().getDir().equals(".cstring")) {
                    line.getDirective().setCString(token.getName());
                } else {
                    //Check mnemonic can have operand
                    //if(line.getInstruction().getMnemonic().getOpcode())
                    // Else Error
                    line.setInstruction(new Instruction(line.getInstruction().getMnemonic(), new Operand(token.getName())));

                    //Update opcode
                    if (token.getCode() == TokenType.Operand) {
                        line.getInstruction().setOpcode(line.getInstruction().getMnemonic().getOpcode() + Integer.parseInt(token.getName()));
                    }
                }
                break;
            //Add comment
            case Comment:
                line.setComment(new Comment(token.getName()));
                break;
            //None - Empty Line or Error
            default:
                if(token.getName() != "")
                    System.out.print("Invalid token: " + token.getName());
        }

/*        //Inherent mode addressing check
        if (opcode >= 0x00 && opcode <= 0x1F) {
            errorReporter.addError(3, token);
        }
        //enter.u5 operand check
        else if (opcode == 0x70) {
            int operand = Integer.parseInt(token.getName());
            if (operand < 0 || operand > 31) {
                errorReporter.addError(5,token);
            }
        }
        //ldc.i3 operand check
        else if (opcode == 0x90) {
            int operand = Integer.parseInt(token.getName());
            if (operand < -4 || operand > 3) {
                errorReporter.addError(6,token);
            }
        }
        //ldv.u3 operand check
        else if (opcode == 0xA0) {
            int operand = Integer.parseInt(token.getName());
            if (operand < 0 || operand > 7) {
                errorReporter.addError(7,token);
            }
        }
*/
    }

    //Adds to ir
    public void addToIR(IToken token){
        //Get token type
        TokenType tokenType = token.getCode();

        //Get column and line number from token
        int lineNum = token.getPosition().getLineNumber();
        int colNum = token.getPosition().getColumnNumber();

        //Get opcode from Symbol Table
        int code = symbolTable.getCode(token.getName());

        //System.out.println("Line: " + lineNum + ", Token: " + token.getName());

        //Check if LineStatement already exists
        //If it doesn't exist, add a new LineStatement to the IR with the given token
        if (interRep.getLine(lineNum) == null) {
            switch(tokenType) {
                case Label:
                    //Add LineStatement to IR with label
                    interRep.addLine(lineNum, new LineStatement(token.getName(), null, null));
                    break;
                case Mnemonic:
                    //Add LineStatement to IR with mnemonic and its opcode
                    if(token.getName() == ".cstring")
                        interRep.addLine(lineNum, new LineStatement(null, new Directive(token.getName(), ""), null, null));
                    else
                        interRep.addLine(lineNum, new LineStatement(null, new Instruction(new Mnemonic(token.getName(), code)), null));
                    break;
                case Operand:
                case LabelOperand:
                    //Add LineStatement to IR with operand
                    interRep.addLine(lineNum, new LineStatement(null, new Instruction(null, new Operand(token.getName())), null));
                    break;
                case Comment:
                    //Add comment
                    interRep.addLine(lineNum, new LineStatement(null, null, token.getName()));
                    break;
                default:
                    //System.out.println("HERE" + token.getName());
                    //Add empty LineStatement
                    if (token.getName() == "")
                        interRep.addLine(lineNum, new LineStatement(null, null, null));
                    else
                        //TODO: add error reporting if none of these work
                        System.err.print("Invalid token. Need to check why");
            }
        }
        //If a LineStatement already exists, add token to said LineStatement
        else {
            //Get the LineStatement
            line = interRep.getLine(lineNum);

            //Check the token type
//            if (tokenType == null) {
//                tokenType = TokenType.Comment;
//            }

            switch(tokenType) {
                //TODO: Label case probably not needed
                case Label:
                    //Add label to LineStatement
                    interRep.setLabel(lineNum, token.getName());
                    break;
                //Add mnemonic to LineStatement
                case Mnemonic:
                    if(token.getName() == ".cstring") {
                        interRep.setDirective(lineNum, new Directive(token.getName(), ""));
                    }
                    else if (line.getInstruction() == null) {
                        //Set the updated instruction in the LineStatement
                        interRep.setInstruction(lineNum, new Instruction(new Mnemonic(token.getName(), code)));
                    }
                    else {
                        //Get Instruction
                        instr = line.getInstruction();

                        //Set new mnemonic to the instruction
                        instr.setMnemonic(new Mnemonic(token.getName(), code));

                        //Set the updated instruction in the LineStatement
                        interRep.setInstruction(lineNum, instr);
                    }
                    break;
                //Add label or operand to LineStatement
                case Operand:
                    //Add operand to LineStatement
                    //Verify if operand can be added to IR
                    int opcode = line.getInstruction().getMnemonic().getOpcode();

                    //Inherent mode addressing check
                    if (opcode >= 0x00 && opcode <= 0x1F) {
                        errorReporter.addError(3, token);
                    }
                    //enter.u5 operand check
                    else if (opcode == 0x70) {
                        int operand = Integer.parseInt(token.getName());
                        if (operand < 0 || operand > 31) {
                            errorReporter.addError(5,token);
                        }
                    }
                    //ldc.i3 operand check
                    else if (opcode == 0x90) {
                        int operand = Integer.parseInt(token.getName());
                        if (operand < -4 || operand > 3) {
                            errorReporter.addError(6,token);
                        }
                    }
                    //ldv.u3 operand check
                    else if (opcode == 0xA0) {
                        int operand = Integer.parseInt(token.getName());
                        if (operand < 0 || operand > 7) {
                            errorReporter.addError(7,token);
                        }
                    }

                    //Get LineStatement
                    instr = line.getInstruction();

                    //Set the new operand
                    instr.setOperand(new Operand(token.getName()));

                    //Set the updated instruction in the LineStatement
                    interRep.setInstruction(lineNum, instr);

                    //Update mnemonic's code based on added operand
                    if (interRep.hasInstruction(lineNum) && interRep.getLine(lineNum).getInstruction().getOperand().isNumeric()) {
                        interRep.updateCode(lineNum);
                    }
                    break;
                //Add label to LineStatement
                case LabelOperand:
                    //Verify if missing operand for immediate instruction

                    opcode = line.getInstruction().getMnemonic().getOpcode();
                    if (opcode >= 0x30 && opcode <= 0xA8) {
                        if (line.getLabel() == "") {
                            if (colNum == 1) {
                                errorReporter.addError(2, token);
                            }
                        } else {
                            if (colNum == 2) {
                                errorReporter.addError(2, token);
                            }
                        }
                    }

                    //Get LineStatement
                    instr = line.getInstruction();

                    //Set the new operand
                    instr.setOperand(new Operand(token.getName()));

                    //Set the updated instruction in the LineStatement
                    interRep.setInstruction(lineNum, instr);

                    //interRep.setLabel(lineNum, token.getName()
                    break;
                case Comment:
                    //Verify if missing operand for immediate instruction
                    opcode = line.getInstruction().getMnemonic().getOpcode();
                    if (opcode >= 0x30 && opcode <= 0xA8) {
                        if (line.getLabel() == "") {
                            if (colNum == 1) {
                                errorReporter.addError(2, token);
                            }
                        } else {
                            if (colNum == 2) {
                                errorReporter.addError(2, token);
                            }
                        }
                    }

                    //Check if missing operand for immediate instruction
                    if (opcode >= 0x00 && opcode <= 0x1F) {
                        errorReporter.addError(3,token);
                    }

                    //Add comment
                    if (token.getName().startsWith(";")) {
                        interRep.setComment(lineNum, new Comment(token.getName()));
                    }
                    //Throw error
                    else {
                        //TODO: add error reporting if none of these work
                        System.err.print("Invalid token. Need to check why2");
                    }

            }
        }
    }

    //Get the intermediate representation
    public IInterRep getInterRep() {
        return interRep;
    }

    //Print error recorded by ErrorReporter (if there are any)
    public void reportErrors() {
        errorReporter.reportErrors();
    }
}

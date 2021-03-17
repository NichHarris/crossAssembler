//Parser - Performs analysis the syntax of tokens and generates the correct IR
public class Parser implements IParser {
    private IInterRep interRep;
    private IInstruction instr;
    private ILineStatement line;

    private ISymbolTable symbolTable;
    private IErrorReporter errorReporter;

    //Parser constructor initializes IR using number of lines from Reader
    public Parser(int len) {
        interRep = new InterRep(len);
        symbolTable = new SymbolTable();
        //Create an ErrorReporter
        errorReporter = new ErrorReporter();
    }

    //Parse a token received from Scanner
    public void parseToken(IToken token){
        //Get column and line number from token
        int lineNum = token.getPosition().getLineNumber();
        int colNum = token.getPosition().getColumnNumber();

        //Get token type
        TokenType tokenType = token.getCode();

        //Get opcode from Symbol Table
        int code = symbolTable.getCode(token.getName());

//        System.out.println("Line: " + lineNum + ", Token: " + token.getName());

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
                    interRep.addLine(lineNum, new LineStatement(null, new Instruction(new Mnemonic(token.getName(), code)), null));
                    break;
                case LabelOperand:
                    //Add LineStatement to IR with operand
                    /*
                    if (isNumeric(token.getName()))
                        interRep.addLine(lineNum, new LineStatement(null, new Instruction(null, new Operand(token.getName())), null));
                    //Add LineStatement to IR with label
                    else
                        interRep.addLine(lineNum, new LineStatement(token.getName(), null, null));
                     */
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
            if (tokenType == null) {
                tokenType = TokenType.Comment;
            }
            switch(tokenType) {
                //TODO: Label case probably not needed
                case Label:
                    //Add label to LineStatement
                    interRep.setLabel(lineNum, token.getName());
                    break;
                //Add mnemonic to LineStatement
                case Mnemonic:
                    if (line.getInstruction() == null) {
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
                case LabelOperand:
                    //Add operand to LineStatement
                    if (isNumeric(token.getName())) {
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
                    }
                    //Add label to LineStatement
                    else {
                        //Verify if missing operand for immediate instruction
                        int opcode = line.getInstruction().getMnemonic().getOpcode();
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

                        //interRep.setLabel(lineNum, token.getName());
                    }
                    break;
                case Comment:
                    //Verify if missing operand for immediate instruction
                    int opcode = line.getInstruction().getMnemonic().getOpcode();
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
                        interRep.setComment(lineNum, token.getName());
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

    //Check if token is numeric
    public boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

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

    //Second pass through Parser to update machine code
    public void secondPass() {
        //Traverse LineStatements in IR and update codes + LineStatement byte sizes
        for (int i = 0; i < interRep.getLength(); i++){
            //Get the original opcode before making modifications
            int initOpcode = interRep.getLine(i).getInstruction().getMnemonic().getOpcode();

            //Stack/inherent addressing
            if (initOpcode >= 0x00 && initOpcode <= 0x1F){
                interRep.setSize(i, 1);
            }
            //.cstring directives
            else if (initOpcode == 0x41) {
                String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
                int len = operand.length();
                interRep.setSize(i, 1 + len);
            }
            //Immediate Addressing
            else if (initOpcode >= 0x30 && initOpcode <= 0xA8) {
                String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
                if (!isNumeric(operand) && operand != ""){
                    interRep.setSize(i, 3);
                }
                else {
                    interRep.setSize(i, 1);
                }
            }
            //Relative Addressing
            else if (initOpcode >= 0xB0 && initOpcode <= 0xFF) {
                String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
                if (!isNumeric(operand) && operand != ""){
                    interRep.setSize(i, 3);
                }
                else {
                    interRep.setSize(i, 1);
                }
            }

            //System.out.println(interRep.hasInstruction(i));
            if (interRep.hasInstruction(i) && isNumeric(interRep.getLine(i).getInstruction().getOperand().getOp())) {
                interRep.updateCode(i);
            }

            System.out.println("Size: " + interRep.getSize(i));
        }

        //Set the address of each line
        interRep.setAddr(0, 0);
        for (int i = 1; i < interRep.getLength(); i++) {
            interRep.setAddr(i,interRep.getAddr(i-1) + interRep.getSize(i-1));
            System.out.println("Address : " + interRep.getAddr(i));
        }

        //Set the machine code of each line statement
        for (int i = 0; i < interRep.getLength(); i++) {
            //Get the opcode and operand of the line statement
            int opcode = interRep.getLine(i).getInstruction().getMnemonic().getOpcode();
            String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
            //If operand is valid
            if (!isNumeric(operand) && operand != ""){
                //If it's a directive
                if (opcode == 0x41) {
                    String op = operand.substring(1, operand.length() - 1);
                    char[] arr = op.toCharArray();
                    String mCode = Integer.toHexString(Integer.parseInt(interRep.getLine(i).getInstruction().getMnemonic().get()));
                    for (char c: arr){
                        mCode = mCode + " " + Integer.toHexString(c);
                    }
                    interRep.setMachineCode(i, mCode);
                }
                else {
                    String label = interRep.getLine(i).getLabel();
                    String code = interRep.getLine(i).getInstruction().getMnemonic().getMne();
                    for (int j = i; j < interRep.getLength(); j++) {
                        if (interRep.getLine(i).getLabel() == label) {
                            int address = interRep.getAddr(i);
                            interRep.setMachineCode(i, String.format("%s1 %s2", Integer.toHexString(Integer.parseInt(code)), String.format("%1$04X",address)));
                        } else {
                            //throw error here
                        }
                    }
                }
            }
            else {
                interRep.setMachineCode(i, interRep.getLine(i).getInstruction().getMnemonic().getMne());
            }

            System.out.println("Machine Code: " + interRep.getMachineCode(i));
        }
    }

    //Print error recorded by ErrorReporter (if there are any)
    public void reportErrors() {
        errorReporter.reportErrors();
    }
}

//Parser - Performs analysis the syntax of tokens and generates the correct IR
public class Parser implements IParser {
    private IInterRep interRep;
    private ISymbolTable symbolTable;
    private ILineStatement line;
    private IInstruction instr;

    //Parser constructor initializes IR using number of lines from Reader
    public Parser(int len) {
        interRep = new InterRep(len);
        symbolTable = new SymbolTable();
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
                    interRep.addLine(lineNum, new LineStatement(null, new Instruction(new Mnemonic(token.getName(), code)), null));
                    break;
                case LabelOperand:
                    //Add LineStatement to IR with operand
                    if (isNumeric(token.getName()))
                        interRep.addLine(lineNum, new LineStatement(null, new Instruction(null, new Operand(token.getName())), null));
                    //Add LineStatement to IR with label
                    else
                        interRep.addLine(lineNum, new LineStatement(token.getName(), null, null));
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

                        //Get LineStatement
                        instr = line.getInstruction();

                        //Set the new operand
                        instr.setOperand(new Operand(token.getName()));

                        //Set the updated instruction in the LineStatement
                        interRep.setInstruction(lineNum, instr);
                    }
                    //Add label to LineStatement
                    else {
                        interRep.setLabel(lineNum, token.getName());
                    }
                    break;
                case Comment:
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

        if (str.startsWith("-")) {
            return true;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)){
                return false;
            }
        }

        return true;
    }

    //Second pass through Parser to update machine code
    public void secondPass() {
        //Traverse LineStatements in IR and update codes
        for (int i = 0; i < interRep.getLength(); i++){
            //System.out.println(interRep.hasInstruction(i));
            if (interRep.hasInstruction(i)) {
                interRep.setMachineCode(i);
            }
        }
    }
}

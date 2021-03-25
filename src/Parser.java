//Parser - Performs analysis the syntax of tokens and generates the correct IR
public class Parser implements IParser {
    private IInterRep interRep;
    private ILineStatement line;

    private BinaryConverter bnConv;
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
        bnConv = new BinaryConverter();
        //Create an instance of Reader
        reader = rdr;

        //Create an instance of LineStatement
        line = new LineStatement();
        currLine = 0;
    }

    //Parse a token received from Scanner
    public void parseToken(){
        IToken tk;

        while(scanner.getCurrPos() != reader.getFileContent().length()) {
            tk = scanner.scanFile(reader);
            parseToIR(tk);
        }
        interRep.addLine(currLine, line);
    }


    //Parses to IR
    public void parseToIR(IToken token) {
        //Get column and line number from token
        int lineN = token.getPosition().getLineNumber();
        int colN = token.getPosition().getColumnNumber();

        //Add to InterRep completed line and create empty line
        if(currLine < lineN) {
            //System.out.println(line.toString());
            interRep.addLine(currLine++, line);
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
                else if (symbolTable.getCode(token.getName()) != -1)
                    line.setInstruction(new Instruction(new Mnemonic(token.getName(), code), new Operand()));
                else
                    errorReporter.record(new ErrorMsg("Not a valid mnemonic or directive. [" + token.getName() + "]", token.getPosition()));
                break;
            //Add Operand/Label to Line
            case Operand:
            case LabelOperand:
                if (line.getDirective().getDir().equals(".cstring")) {
                    line.getDirective().setCString(token.getName());
                } else {
                    int opCode = line.getInstruction().getMnemonic().getOpcode();
                    //Check mnemonic is immediate or relative
                    if (opCode > 0x1F) {
                        line.setInstruction(new Instruction(line.getInstruction().getMnemonic(), new Operand(token.getName())));
                        System.out.println("Here: " + line.getInstruction().getOperand().getOp());
                        //Update opcode - Parse operand size and state
                        if (token.getCode() == TokenType.Operand) {
                            //System.out.println("[" + token.getName() + "], Token Type: " + token.getCode());
                            parseOperandBound(token, opCode);
                        }
                    //Inherent Mode Addressing Error
                    } else
                        if (!token.getName().equals("")) {
                            errorReporter.record(new ErrorMsg("Instructions with inherent mode addressing do not have an operand field. [" + token.getName() + "]", token.getPosition()));
                        }
                }
                break;
            //Add comment
            case Comment:
                line.setComment(new Comment(token.getName()));
                break;
            //None - Empty Line or Error
            default:
                if(!token.getName().equals(""))
                    errorReporter.record(new ErrorMsg("Invalid Token.", token.getPosition()));
        }
    }

    //Parses Operand Bounds
    public void parseOperandBound(IToken token, int opCode) {
        //Get substring of mnemonic
        String mne = line.getInstruction().getMnemonic().getMne();
        String subMne = mne.substring(mne.indexOf('.') + 1);

        //Get signed or unsigned and size
        boolean isSigned = subMne.contains("i");
        int size = Integer.parseInt(subMne.substring(subMne.indexOf(isSigned ? 'i' : 'u') + 1));
        int shift = Integer.parseInt(token.getName());
        //Converts signed values for shift
        if (isSigned) {
            //Handle ldc.i3 instruction case
            if (mne.equals("enter.u5")) {
                if (Integer.parseInt(line.getInstruction().getOperand().getOp()) < -4 || Integer.parseInt(line.getInstruction().getOperand().getOp()) > 3) {
                    errorReporter.record(new ErrorMsg("The immediate instruction 'ldc.i3' must have a 3-bit signed operand number ranging from -4 to 3.", token.getPosition()));
                } else {
                    String binStr = bnConv.toBinary(shift, size);
                    shift = bnConv.getBinaryValue(binStr);
                    line.getInstruction().setOpcode(opCode + shift);
                }
            } else {
                String binStr = bnConv.toBinary(shift, size);
                shift = bnConv.getBinaryValue(binStr);
                line.getInstruction().setOpcode(opCode + shift);
            }
        }
        //Get Overflow Method in Binary Converter
        else if (!bnConv.isOverflow(shift, size, isSigned)) {
            //Handle enter.u5 instruction case
            if(mne.equals("enter.u5")){
                opCode = (Integer.parseInt(line.getInstruction().getOperand().getOp()) > 15) ? 0x70 : 0x80;
                opCode = opCode | (Integer.parseInt(line.getInstruction().getOperand().getOp()) & 0x1F);
                line.getInstruction().setOpcode(opCode);
            } else {
                line.getInstruction().setOpcode(opCode + shift);
            }
        }
        //Operand Exceed Limit: Errors 5-7
        else {
            //enter.u5 operand check
            if (opCode == 0x70)
                errorReporter.record(new ErrorMsg("The immediate instruction 'enter.u5' must have a 5-bit unsigned operand number ranging from 0 to 31.", token.getPosition()));
            //ldc.i3 operand check
            else if (opCode == 0x90)
                    errorReporter.record(new ErrorMsg("The immediate instruction 'ldc.i3' must have a 3-bit signed operand number ranging from -4 to 3.", token.getPosition()));
            //ldv.u3 operand check
            else if (opCode == 0xA0)
                    errorReporter.record(new ErrorMsg("The immediate instruction 'ldv.u3' must have a 3-bit unsigned operand number ranging from 0 to 7.", token.getPosition()));
            else
                System.out.println("Future Sprint Case");
        }
    }

    //Get the intermediate representation
    public IInterRep getInterRep() {
        return interRep;
    }
}

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

//Parser - Performs analysis the syntax of tokens and generates the correct IR
public class Parser implements IParser {
    private static IInterRep interRep;
    private ILineStatement line;
//temp
    private final IBinaryConverter bnConv;
    private final ISymbolTable symbolTable;
    private final IErrorReporter errorReporter;
    private final IScanner scanner;
    private final IReader reader;

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
        //Add final LineStatement to IR
        interRep.addLine(currLine, line);
    }


    //Parses to IR
    private void parseToIR(IToken token) {
        //Get line number from token
        int lineN = token.getPosition().getLineNumber();
        int colN = token.getPosition().getColumnNumber();
        //Add to InterRep completed line and create empty line
        if(currLine < lineN) {
            //Missing operand
            if(line.getInstruction().getMnemonic().getOpcode() > 0x1F && line.getInstruction().getOperand().getOp().equals(""))
                errorReporter.record(new ErrorMsg("Instructions with immediate mode addressing needs to have an operand field. [" + line.getInstruction().getMnemonic().getMne() + "]", new Position(currLine, colN)));

            //Add current LineStatement to IR
            interRep.addLine(currLine++, line);
            //Create new LineStatement
            line = new LineStatement();
        }

        //Get opcode from Symbol Table
        int code = symbolTable.getCode(token.getName());

        //Adds to LineStatement
        switch(token.getTokenType()) {
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
                    errorReporter.record(new ErrorMsg("Not a valid mnemonic or directive. [" + token.getName() + "]", new Position(currLine, colN)));
                break;
            //Add Operand/Label to Line
            case Operand:
            case LabelOperand:
                if (line.getDirective().getDir().equals(".cstring")) {
                    line.getDirective().setCString(token.getName());
                    //Check quotation marks
                    if(!line.getDirective().getCString().startsWith("\"") || !line.getDirective().getCString().endsWith("\""))
                        errorReporter.record(new ErrorMsg("Directives need to be declared with opening and closing quotation marks. [" + line.getDirective().getCString() + "]", new Position(currLine, colN)));
                } else {
                    int opCode = line.getInstruction().getMnemonic().getOpcode();
                    //Check mnemonic is immediate or relative
                    if (opCode > 0x1F && opCode < 0xB0) {
                        line.setInstruction(new Instruction(line.getInstruction().getMnemonic(), new Operand(token.getName())));
                        //Update opcode - Parse operand size and state
                        if (token.getTokenType() == TokenType.Operand) {
                            parseOperandBound(token, opCode);
                        }
                    //Relative mode addressing
                    } else if (opCode >= 0xB0) {
                        line.setInstruction(new Instruction(line.getInstruction().getMnemonic(), new Operand(token.getName())));

                        //Check operand size
                        if (token.getTokenType() == TokenType.Operand && bnConv.isOverflow(Integer.parseInt(token.getName()), 8*line.getInstruction().getMnemonic().getMneSize(),line.getInstruction().getMnemonic().getMne().contains(".i"))) {
                            errorReporter.record(new ErrorMsg("Instruction in relative addressing mode exceeds its range. [" + line.getInstruction().getMnemonic().getMne() + "]", new Position(currLine, colN)));
                        }
                        //Check if operand is a label not a number
                        switch (opCode) {
                            //Expecting label - Check if operand
                            case (0xD5):
                            case (0xE0):
                            case (0xE1):
                            case (0xE3):
                                if(token.getTokenType() == TokenType.Operand)
                                    errorReporter.record(new ErrorMsg("Operand must refer to a Label.", new Position(currLine, colN)));
                                break;
                            //Expecting operand - Check if label
                            default:
                                if(token.getTokenType() == TokenType.LabelOperand && !line.getInstruction().getOperand().isNumeric())
                                    errorReporter.record(new ErrorMsg("Label must refer to a Operand.", new Position(currLine, colN)));
                        }
                    //Inherent mode addressing error
                    } else {
                        errorReporter.record(new ErrorMsg("Instructions with inherent addressing mode do not have an operand field. [" + line.getInstruction().getMnemonic().getMne() + "]", new Position(currLine, colN)));
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
                    errorReporter.record(new ErrorMsg("Invalid Token.", new Position(currLine, colN)));
        }
    }

    //Parses Operand Bounds
    private void parseOperandBound(IToken token, int opCode) {
        //Get substring of mnemonic
        String mne = line.getInstruction().getMnemonic().getMne();
        String subMne = mne.substring(mne.indexOf('.') + 1);

        //Get signed or unsigned and size
        boolean isSigned = subMne.contains("i");
        int size = Integer.parseInt(subMne.substring(subMne.indexOf(isSigned ? 'i' : 'u') + 1));
        int shift = Integer.parseInt(token.getName());

        //Get Overflow Method in Binary Converter
        if (!bnConv.isOverflow(shift, size, isSigned)) {
            //Converts signed values for shift
            if(isSigned) {
                String binStr = bnConv.toBinary(shift, size);
                shift = bnConv.getBinaryValue(binStr);
                line.getInstruction().setOpcode(opCode + shift);
            //Handle enter.u5 instruction case
            } else if(mne.equals("enter.u5")) {
                opCode = (Integer.parseInt(line.getInstruction().getOperand().getOp()) > 15) ? 0x70 : 0x80;
                opCode = opCode | (Integer.parseInt(line.getInstruction().getOperand().getOp()) & 0x1F);
                line.getInstruction().setOpcode(opCode);
            } else {
                line.getInstruction().setOpcode(opCode + shift);
            }
        }
        //Operand Exceeds Limit
        else {
            //enter.u5 operand check
            if (opCode == 0x70)
                errorReporter.record(new ErrorMsg("The immediate instruction 'enter.u5' must have a 5-bit unsigned operand number ranging from 0 to 31.", token.getPosition()));
            //ldv.u3 operand check
            else if (opCode == 0xA0)
                errorReporter.record(new ErrorMsg("The immediate instruction 'ldv.u3' must have a 3-bit unsigned operand number ranging from 0 to 7.", token.getPosition()));
            //ldc.i3 operand check
            else if(opCode == 0x90)
                errorReporter.record(new ErrorMsg("The immediate instruction 'ldc.i3' must have a 3-bit signed operand number ranging from -4 to 3.", token.getPosition()));
        }
    }

    //Get the intermediate representation
    public IInterRep getInterRep() {
        return interRep;
    }
}

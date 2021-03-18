//Main class for the VM Cross Assembler
public class Assembler {
    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {
        //Create SymbolTable
        ISymbolTable symbolTable = new SymbolTable();

        //Create error reporter
        IErrorReporter errorReporter = new ErrorReporter();

        //Set options from CL
        IOptions options = new Options();
        options.setOptions(args);

        //Get file name
        String fileName = options.getFile();

        //Create Reader object
        IReader reader = new Reader(fileName);
        reader.readFile();

        //Create scanner object, this is to be passed to Parse
        IScanner scanner = new Scanner(symbolTable, errorReporter);

        //Instantiate the Parser
        IParser parser = new Parser(reader.getLineNum() + 1, symbolTable, errorReporter, scanner, reader);
        //We then want Parser to request a token from scanner, from there, scanner will produce a token for parser
        //and then do its thing on that token, adding it to the interRep
        parser.parseToken();

        //Report any errors found in Scanner
        scanner.reportErrors();

        //Run a second pass through the IR to update the machine code
        IInterRep interRep = parser.getInterRep();
        secondPass(interRep);

        //Report any errors found in Parser
        parser.reportErrors();

        //Get the intermediate representation

        //Generate listing file
        ICodeGenerator generator = new CodeGenerator(interRep, options);
    }

    static void secondPass(IInterRep interRep) {
        //Traverse LineStatements in IR and update codes + LineStatement byte sizes
        for (int i = 0; i < interRep.getLength(); i++) {
            if (interRep.hasInstruction(i)) {
                //Get the original opcode before making modifications
                int initOpcode = interRep.getLine(i).getInstruction().getMnemonic().getOpcode();

                //Stack/inherent addressing
                if (initOpcode >= 0x00 && initOpcode <= 0x1F) {
                    interRep.setSize(i, 1);
                }
                //.cstring directives
                else if (initOpcode == 0x41) {
                    String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
                    int len = operand.substring(1, operand.length() - 1).length();
                    interRep.setSize(i, 1 + len);
                }
                //Immediate Addressing
                else if (initOpcode >= 0x30 && initOpcode <= 0xA8) {
                    String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
                    if (!isNumeric(operand) && operand != "") {
                        interRep.setSize(i, 3);
                    } else {
                        interRep.setSize(i, 1);
                    }
                }
                //Relative Addressing
                else if (initOpcode >= 0xB0 && initOpcode <= 0xFF) {
                    String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
                    if (!isNumeric(operand) && operand != "") {
                        interRep.setSize(i, 3);
                    } else {
                        interRep.setSize(i, 1);
                    }
                }

                //System.out.println(interRep.hasInstruction(i));
                if (interRep.hasInstruction(i) && isNumeric(interRep.getLine(i).getInstruction().getOperand().getOp())) {
                    //System.out.println(String.format("Opcode before: %s", Integer.toHexString(interRep.getLine(i).getInstruction().getMnemonic().getOpcode())));
                    //System.out.println(String.format("Operand before: %s", interRep.getLine(i).getInstruction().getOperand()));
                    interRep.updateCode(i);
                    // System.out.println(String.format("Opcode After: %s", Integer.toHexString(interRep.getLine(i).getInstruction().getMnemonic().getOpcode())));
                }

            }

            //System.out.println("Size: " + interRep.getSize(i));
        }

        //Set the address of each line, starting at 0000 for the first line
        interRep.setAddr(0, 0);
        for (int j = 1; j < interRep.getLength(); j++) {
            //Get previous and current LineStatement
            ILineStatement prevLine = interRep.getLine(j - 1);
            ILineStatement currLine = interRep.getLine(j);
            System.out.println("Prev: " + prevLine.toString());
            System.out.println("Curr: " + currLine.toString());

            //Check if previous line is empty and current line is not
            //If so, increment the current line's address by one
            //Otherwise set current line's address to the addition of the previous line's address and its size
            if (prevLine.isEmpty() && !currLine.isEmpty()) {
                interRep.setAddr(j, interRep.getAddr(j - 1) + 1);
            } else {
                interRep.setAddr(j, interRep.getAddr(j - 1) + interRep.getSize(j - 1));
                System.out.println(interRep.getAddr(j));
            }
        }

        //Set the machine code of each line statement
        for (int i = 0; i < interRep.getLength(); i++) {
            //Get the opcode and operand of the line statement
            int opcode = interRep.getLine(i).getInstruction().getMnemonic().getOpcode();
            String operand = interRep.getLine(i).getInstruction().getOperand().getOp();
            //If operand is a label or string
            if (!isNumeric(operand) && operand != "") {
                //If a line's mnemonic is a .cstring, set its machine code to its opcode + the byte size of each character in the string operand
                if (opcode == 0x41) {
                    String op = operand.substring(1, operand.length() - 1);
                    char[] arr = op.toCharArray();
                    String mCode = "";
                    //Append hex bytes to machine code
                    for (char c : arr) {
                        mCode = mCode + " " + Integer.toHexString(c).toUpperCase();
                    }
                    //Append '00' as remaining bytes to machine code
                    for (int j = interRep.getSize(i) - arr.length; j > 0; j--) {
                        mCode = mCode + " 00";
                    }

                    mCode = mCode.substring(1);
                    interRep.setMachineCode(i, mCode);
                }
                //If operand is a label, set the machine code to the instruction's opcode + the
                else {
                    String label = interRep.getLine(i).getInstruction().getOperand().getOp();
                    int code = interRep.getLine(i).getInstruction().getMnemonic().getOpcode();
                    //Fine the address where the label is declared
                    for (int j = i + 1; j < interRep.getLength(); j++) {
                        String currLabel = interRep.getLine(j).getLabel();
                        if (currLabel.equals(label)) {
                            int address = interRep.getAddr(j);
                            interRep.setMachineCode(i, String.format("%s %s", Integer.toHexString(code).toUpperCase(), String.format("%1$04X", address)));
                        } else {
                            //TODO: Throw error here
                        }
                    }
                }
            } else if (isNumeric(operand)) {
                interRep.setMachineCode(i, Integer.toHexString(interRep.getLine(i).getInstruction().getMnemonic().getOpcode()).toUpperCase());
            }

//            System.out.println("Machine Code: " + interRep.getMachineCode(i));
        }
    }

    //Check if token is numeric
    static boolean isNumeric(String str) {
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

}


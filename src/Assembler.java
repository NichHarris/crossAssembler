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

        //TODO? Get the intermediate representation

        //Generate listing file
        ICodeGenerator generator = new CodeGenerator(interRep, options);
    }

    static void secondPass(IInterRep interRep) {

        //Traverse LineStatements in IR and update codes
        for (int i = 0; i < interRep.getLength(); i++) {
            //System.out.println(interRep.hasInstruction(i));
            if (interRep.hasInstruction(i) && interRep.getLine(i).getInstruction().getOperand().isNumeric()) {
                //System.out.println(String.format("Opcode before: %s", Integer.toHexString(interRep.getLine(i).getInstruction().getMnemonic().getOpcode())));
                //System.out.println(String.format("Operand before: %s", interRep.getLine(i).getInstruction().getOperand()));
                interRep.updateCode(i);
                // System.out.println(String.format("Opcode After: %s", Integer.toHexString(interRep.getLine(i).getInstruction().getMnemonic().getOpcode())));
            }
        }

        //Set the address of each line, starting at 0000 for the first line
        interRep.setAddr(0, 0);
        for (int j = 1; j < interRep.getLength(); j++) {
            //Get previous and current LineStatement
            ILineStatement prevLine = interRep.getLine(j - 1);
            ILineStatement currLine = interRep.getLine(j);

            //Check if previous line is empty and current line is not
            //If so, increment the current line's address by one
            //Otherwise set current line's address to the addition of the previous line's address and its size
            if (prevLine.isEmpty() && !currLine.isEmpty()) {
                interRep.setAddr(j, interRep.getAddr(j - 1) + 1);
            } else {
                interRep.setAddr(j, interRep.getAddr(j - 1) + interRep.getLine(j - 1).getInstruction().getSize());
                System.out.println(interRep.getAddr(j));
            }
        }
    }
}

package main.java;

import main.interfaces.*;

//Main class for the VM Cross Assembler
public class Assembler {
    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {

        try {
            //Set options from CL
            IOptions options = new Options(args);

            //Get file name
            String fileName = options.getFile(); //throw exception if file not found

            //Create Reader object
            IReader reader = new Reader(fileName);
            reader.readFile();

            //Create SymbolTable
            ISymbolTable symbolTable = new SymbolTable();

            //Create error reporter
            IErrorReporter errorReporter = new ErrorReporter(fileName);

            //Create scanner object, this is to be passed to Parse
            IScanner scanner = new Scanner(symbolTable, errorReporter);

            //Instantiate the Parser
            IParser parser = new Parser(reader.getLineNum() + 1, symbolTable, errorReporter, scanner, reader);
            //We then want Parser to request a token from scanner, from there, scanner will produce a token for parser
            //and then do its thing on that token, adding it to the interRep
            parser.parseToken();

            //Run a second pass through the IR to update the machine code
            IInterRep interRep = parser.getInterRep();
            secondPass(interRep);

            //Report any errors found by the cross assembler
            errorReporter.report();

            //Generate listing file
            ICodeGenerator generator = new CodeGenerator(interRep, options);

        }catch(Exception e){

            System.out.println(e.getMessage());
        }
    }

    static void secondPass(IInterRep interRep) {
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
                //If its a directive
                if (interRep.hasDirective(j-1)){
                    int dirSize = interRep.getDirective(j-1).getCString().substring(1, interRep.getDirective(j-1).getCString().length() - 1).length() + 1;
                    interRep.setAddr(j, interRep.getAddr(j - 1) + dirSize);
                }
                //If its an instruction
                else {
                    int instrSize = interRep.getLine(j - 1).getInstruction().getSize();
                    interRep.setAddr(j, interRep.getAddr(j - 1) + instrSize);
                }
            }
        }
    }
}

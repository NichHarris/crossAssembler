//Main class for the VM Cross Assembler
public class Assembler {
    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {
        //Set options from CL
        IOptions options = new Options();
        options.setOptions(args);

        //Get file name
        String fileName = options.getFile();

        //Create Reader object
        IReader reader = new Reader(fileName);
        reader.readFile();

        //Scan for tokens using the read file content from Reader
        IScanner scanner = new Scanner(reader);
        scanner.scanFile();

        //Get the parser from scanner
        IParser parser = scanner.getParser();

        //Run a second pass through the IR to update the machine code
        parser.secondPass();

        //Get the intermediate representation
        IInterRep interRep = parser.getInterRep();

        //Generate listing file
        ICodeGenerator generator = new CodeGenerator(interRep, options);
    }
}
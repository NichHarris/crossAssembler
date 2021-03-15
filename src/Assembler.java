import java.util.ArrayList;

//Main class for the VM Cross Assembler
public class Assembler {
    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {
        //Set the options from the CL
        IOptions options = new Options();
        options.setOptions(args);

        //Get File Name
        String fileName = options.getFile();

        //Scan for tokens using the read file content from Reader
        IScanner scanner = new Scanner(new Reader(fileName));

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
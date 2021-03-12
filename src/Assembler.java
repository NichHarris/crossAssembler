import java.util.ArrayList;

//Main class for the VM Cross Assembler
public class Assembler {
    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {

        //Check if .asm file not included in CLI arguments
        int fileIndex = args.length - 1;
        if (args.length < 1 || !args[fileIndex].endsWith(".asm")) {
            System.out.println("Error: Missing Assembly file");
            return;
        }

        //Check if there's too many CLI arguments
        if (args.length > 3) {
            System.out.println("Error: Too Many Arguments in CL");
            return;
        }

        //Set the options from the
        IOptions options = new Options(args);

        //Scan for tokens using the read file content from Reader
        IScanner scanner = new Scanner(new Reader(args[fileIndex]));

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
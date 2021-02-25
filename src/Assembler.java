import java.util.ArrayList;

//Main class for the VM Cross Assembler
public class Assembler {
    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {
        int fileIndex = args.length - 1;

        //Check if .asm file not included in CLI arguments
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

        //Parse the .asm file and get an array of unparsed line statements
        IReader fileContent = new Reader(args[fileIndex]);
        ArrayList<String> assemblyUnit = fileContent.getAssemblyUnit();

        //Initialize the IR
        IInterRep IR = new InterRep(assemblyUnit.size());

        //Parse line statements into tokens and comments
        IScanner scanner = new Scanner(assemblyUnit);

        //Perform syntax analysis on tokens
        IParser parser = new Parser(scanner, IR);

        //Generate listing file
        ICodeGenerator generator = new CodeGenerator(IR, options);
    }
}
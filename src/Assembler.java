//Main class for the VM Cross Assembler
public class Assembler {

    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {

        //Check if .asm file not included in CLI arguments
        if (args.length < 1) {
            System.out.println("Error: Missing .asm file");
            return;
        }

        //Check if first argument is a .asm file
        if (!args[0].split("\\.")[1].equals("asm")) {
            System.out.println("Error: Missing .asm file");
            return;
        }

        //Check if there's too many CLI arguments
        if (args.length > 3) {
            System.out.println("Error: Too Many Arguments in CL");
            return;
        }

        //Set the options from the
        Options options = new Options(args);

        //Parse the .asm file and get an array of unparsed line statements
        Reader fileContent = new Reader(args[0]);
        String[] assemblyUnit = fileContent.getAssemblyUnit();

        //Initialize the IR
        InterRep IR = new InterRep(assemblyUnit.length);

        //Parse line statements into tokens and comments
        Scanner scanner = new Scanner(assemblyUnit);

        //Perform syntax analysis on tokens
        Parser parser = new Parser(scanner, IR);

        //Generate listing file
        CodeGenerator generator = new CodeGenerator(IR, options);
    }
}
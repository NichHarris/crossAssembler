public class Assembler {

    // Entrypoint of program execution
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

        //Parse the .asm file and get an array of unparsed line statements
        Reader fileContent = new Reader(args[0]);
        String[] assemblyUnit = fileContent.getAssemblyUnit();

        //Initialize the AssemblyUnit
        InterRep IR = new InterRep(assemblyUnit.length);

        //Splits LineStatements to Perform Lexical Analysis
        Scanner(assemblyUnit, IR);

        //Get listing file contents
        Listing listing = new Listing(IR);
        String[] listingContent = listing.getListing();

        //Generate listing file
        FileGenerator generator = new FileGenerator(listingContent);
        generator.generateListing();
    }

    //Scanner - Performs Lexical Analysis
    public static void Scanner(String[] lines, InterRep assemblyUnit) {
        //Split LineStatements into Sub Components Using Whitespace
        String[] subComponents;
        String assComments;
        for(int i = 0; i < lines.length; i++) {
            //Ignore Comments, Remove Extra WhiteSpace Then Split into SubComponents
            subComponents = lines[i].split(";")[0].trim().split("\\s+");
            assComments = lines[i].contains(";") ? lines[i].split(";")[1].trim() : "";
            LexicalAnalyzer(assemblyUnit, i, subComponents, assComments);
        }
    }

    //Syntax Analyzer
    public static void LexicalAnalyzer(InterRep assemblyUnit, int i, String[] subComponents, String comments) {
        //Perform Syntax Analysis & Detect Errors
        int len = subComponents.length;
        switch(len) {
            //Stack + Inherent Addressing Mode
            case(1):
                //System.out.println("Mnemonic || Label");
                //Check in HashSet for Mnemonic
                //If not, Add Element to Label Table
                SymbolTable symbolTable = new SymbolTable();

                int code = symbolTable.getCode(subComponents[0]);

                if(code == -1)
                    System.out.println("Error: Mnemonic Not Found");
                else if(code > 0x1F)
                    System.out.println("Error: Missing an Operand");
                else if(code >= 0x00 && code <= 0x1F ) {
                    //Add LineStatement to AssemblyUnit
                    assemblyUnit.setLine(i, new Instruction(subComponents[0], ""), comments);
                    assemblyUnit.setCode(i, code);
                }
                //Check if label exists in label table Else Add to Table

                //toHexString
                //System.out.print(Integer.toBinaryString(code) + " ");
                break;
            //Immediate Addressing Mode
            case(2):
                //System.out.println("Mnemonic + Operand || Label + Mnemonic");
                //Check First Element in HashSet for Mnemonic
                //If not, Add first Element to Label Table & Check Second Element


                //System.out.println("Error: Mnemonic Missing/Not Found");
                //System.out.println("Error: Missing an Operand");
                //System.out.println("Error: Operand is Too Large");
                //System.out.println("Error: Operand Not Allowed");
                break;
            //Relative Addressing Mode
            case(3):
                //System.out.println("Label + Mnemonic + Operand");
                //Add First Element to Label List
                //Check Second Element in HashSet for Mnemonic
                //Check Third Element

                //System.out.println("Error: Mnemonic Not Found");
                //System.out.println("Error: Operand is Too Large");
                //System.out.println("Error: Operand Not Allowed");
                break;
            default:
                System.out.println("Error: Exceeds Possible Number of Elements Per line");
        }
    }

}
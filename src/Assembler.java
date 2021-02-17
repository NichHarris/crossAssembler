package src;

import java.io.File;
import java.io.FileInputStream;

public class Assembler {

    private static String srcName = null;
    private static File srcFile = null;

    public static void main(String args[]) throws Exception {

        //Parse the .asm file
        if (args.length != 1) {
            System.out.println("Missing .asm file");
            return;
        }

        if (args[0] != null) { // Check <src>
            srcName = args[0];
            srcFile = new File(srcName);
            if (!srcFile.canRead()) {
                System.out.println("Cannot open source file '" + srcName + "'");
                return;
            }
        }

        //Parse the Assembly Code
        String[] ls = Parser(srcFile);
        //LineStatement[] ls = new LineStatement[numLines];

        //Splits LineStatements to Perform Lexical Analysis
        Tokenizer(ls);
    }

    public static int Scanner(String[] options) throws Exception {
        //Return Type
        int status = 0;

        //No File Provided Error
        if(options.length < 1) return status = -3;

        //Too Many Options Enabled Error
        if(options.length > 3) return status = -2;

        //Iterate Through Options
        for(String o:options)
            if(o.equals("-h") || o.equals("--help"))
                status = (status <= 0) ? 1 : -1;
            else if(o.equals("-l") || o.equals("--listing"))
                status = (status <= 0) ? 2 : (status == 3) ? 4 : -1;
            else if(o.equals("-v") || o.equals("--verbose"))
                status = (status <= 0) ? 3 : (status == 2) ? 4 : -1;
            else if(o.endsWith(".asm"))
                continue;
            else if(!o.endsWith(".asm"))
                return status = -3;
            else
                return status = -1;

        return status;

        // Return Values (Neg = Bad Req, Pos = Good Req)
        // No File Provided: -3
        // Too Many Options: -2
        // Invalid Options: -1
        // No Options: 0
        // -h Only: 1
        // -l Only: 2
        // -v Only: 3
        // -l -v: 4
    }

    //Parses - Reads File
    public static String[] Parser(File f) throws Exception {
        //Read File Using File Input Stream
        FileInputStream file = new FileInputStream(f);

        //Generate an Assembly Unit
        String assemblyUnit = "";
        //Assembly assemblyUnit = new AssemblyUnit("");

        int currentChar = file.read();
        while(currentChar > 0) {
            assemblyUnit += (char)currentChar;
            currentChar = file.read();
        }

        file.close();

        //Create an Array of LineStatements Using EOL
        return assemblyUnit.split("[\r\n]+");
    }
 
    //Tokenizer - Combines Tokens
    public static void Tokenizer(String[] lines) {
        //Split LineStatements into Sub Components Using Whitespace
        String[] subComponents;
        for(String l: lines) {
            //Ignore Comments, Remove Extra WhiteSpace Then Split into SubComponents
            subComponents = l.split(";")[0].trim().split("\\s+");
            
            LexicalAnalyzer(subComponents);
        }
    }
     
    //Lexical Analyzer
    public static void LexicalAnalyzer(String[] subComponents) {
    		//Perform Lexical Analysis & Detect Errors 
        int len = subComponents.length;
        switch(len) {
            //Stack + Inherent Addressing Mode
            case(1):
                System.out.println("Mnemonic || Label");
                //Check in HashSet for Mnemonic
                //If not, Add Element to Label Table

                //System.out.println("Error: Mnemonic Not Found");
                //System.out.println("Error: Missing an Operand");
                break;
            //Immediate Addressing Mode
            case(2):
                System.out.println("Mnemonic + Operand || Label + Mnemonic");
                //Check First Element in HashSet for Mnemonic
                //If not, Add first Element to Label Table & Check Second Element

                //System.out.println("Error: Mnemonic Missing/Not Found");
                //System.out.println("Error: Missing an Operand");
                //System.out.println("Error: Operand is Too Large");
                //System.out.println("Error: Operand Not Allowed");
                break;
            //Relative Addressing Mode
            case(3):
                System.out.println("Label + Mnemonic + Operand");
                //Add First Element to Label List
                //Check Second Element in HashSet for Mnemonic
                //Check Third Element 

                //System.out.println("Error: Mnemonic Not Found");
                //System.out.println("Error: Operand is Too Large");
                //System.out.println("Error: Operand Not Allowed");
                break;
            default:
                System.out.println("Error: Exceeds Possible Number of Elements Per line - " + len);
        }
        
        //Prints Out Sub Components
        System.out.print("[ ");
        for(String s: subComponents)
            System.out.print("[" + s + "] ");
        System.out.println("]");
    }
        
}
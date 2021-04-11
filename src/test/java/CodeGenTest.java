package test.java;
import main.interfaces.*;
import main.java.*;
import java.io.File;
import java.security.*;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays.*;

public class CodeGenTest {


    public static void main(String[] args) throws Exception {
        //Making the Text file in the proper location
        TextFileGenerator.textFileGenerator("main.java.CodeGenTest.txt");

        //Creating Options
        String[] options = {"-v", "-l", "TestImmediate.asm"};

        Main.main(options);
        /*
        //Creating an InterRepresentation
        InterRep IR = new InterRep(1);
//        ISymbolTable symbolTable = new SymbolTable();
//        int code = symbolTable.getCode("addv.u3");

        //Creating mnemonic and operand objects for instruction object
        IMnemonic m = new Mnemonic("addv.u3", null);
        IOperand op = new Operand("3");

        //Creating an Instruction
        IInstruction instruction = new Instruction (m, op);

        //Creating a LineStatement with Instruction
        LineStatement ls = new LineStatement("Fct", instruction, "");

        //Adding Line Statement to IR
        IR.addLine(0, ls);

        // initializing code generator object
        CodeGenerator codeGen = new CodeGenerator(IR,op2);

        // Reading content of listing.ls
        /*
        FileInputStream file = new FileInputStream("listing.lst");

        ArrayList listingContent = new ArrayList<>();
        String lineContent = "";
        int currentChar = file.read();

        //Traverse the contents of the source file and save them to fileContent
        while(currentChar != -1) {
            char c = (char)currentChar;
            //Create an assembly unit comprised of unparsed line statements using EOL
            if(c == '\r' || c == '\n') {
                if(lineContent != "")
                    listingContent.add(lineContent);
                lineContent = "";
            } else
                lineContent += c;
            currentChar = file.read();
        }
        //close source file
        file.close();
        */

        String pathname = "src/io/out/";
        String path1 = new File(pathname + "listing.lst").getAbsolutePath();
        File file = new File(path1);

        String pathname2 = "src/test/ref/";
        String path2 = new File(pathname2 + "listing.lst").getAbsolutePath();
        File file2 = new File(path2);

        //compare(path1,path2);
        System.out.println(Paths.get(path1));
        //long a = Files.mismatch(Paths.get(path1), Paths.get(path2));

        System.out.println(Paths.get(path2));
        // testing code generator file creation
         //TestCodeGenerator("Test -CodeGenerator Class-", "true",Boolean.toString(java.util.Arrays.equals(f1, f2)));


    }

    public static void TestCodeGenerator(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

    /*
    private static void compare(String file1, String file2)throws Exception{
        File
        BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(file2));
        int b1 = 0, b2 = 0, pos = 1;
        while (b1 != -1 && b2 != -1) {
            if (b1 != b2) {
                System.out.println("Files differ at position " + pos);
            }
            pos++;
            b1 = fis1.read();
            b2 = fis2.read();
        }
        if (b1 != b2) {
            System.out.println("Files have different length");
        } else {
            System.out.println("Files are identical");
        }
        fis1.close();
        fis2.close();
    }

     */

}



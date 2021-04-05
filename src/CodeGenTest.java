import java.io.FileInputStream;
import java.util.ArrayList;

public class CodeGenTest {
    public static void main(String[] args) throws Exception{

        //Creating Options
        String[] options = {"-v", "-l","testfile.asm"};
        Options op2 = new Options();
        op2.setOptions(options);

        //Creating an InterRepresentation
        InterRep IR = new InterRep(1);
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");

        //Creating mnemonic and operand objects for instruction object
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");

        //Creating an Instruction
        IInstruction instruction = new Instruction (m, op);

        //Creating a LineStatement with Instruction
        LineStatement ls = new LineStatement("Fct", instruction, "");

        //Adding Line Statement to IR
        IR.addLine(0, ls);

        // initializing code generator object
        CodeGenerator codeGen = new CodeGenerator(IR,op2, "testfile.asm");

        // Reading content of listing.lst
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

        // testing code generator file creation
        TestCodeGenerator("Test -CodeGenerator Class- File creation",
                "1    0000 98            Fct           addv.u3       3                                 ", listingContent.get(1).toString());
    }
    public static void TestCodeGenerator(String testCaseName, String expectedOutput, String methodOutput) throws Exception{
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

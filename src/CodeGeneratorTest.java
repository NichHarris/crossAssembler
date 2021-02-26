import java.io.FileInputStream;
import java.util.ArrayList;
public class CodeGeneratorTest {

    public static void main(String[] args) throws Exception{
        /*
        String[] options = {"-v", "-l"};
        Options op2 = new Options(options);

        Reader fileContent = null;
        try {
            fileContent = new Reader("testfile.asm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> assemblyUnit = fileContent.getAssemblyUnit();
        InterRep IR = new InterRep(assemblyUnit.size());

        Scanner scanner = new Scanner(assemblyUnit);
        Parser parser = new Parser(scanner, IR);
        CodeGenerator codeGen = new CodeGenerator(IR,op2);


         */

        String[] options = {"-v", "-l"};
        Options op2 = new Options(options);
        InterRep IR = new InterRep(1);
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");
        IInstruction instruction = new Instruction (m, op);
        LineStatement ls = new LineStatement("Fct", instruction, "");
        IR.addLine(0, ls);

        CodeGenerator codeGen = new CodeGenerator(IR,op2);



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

        TestCodeGenerator("Test -CodeGenerator Class- File creation",
                "0    0000 98            Fct           addv.u33                                 ", listingContent.get(1).toString());


    }
    public static void TestCodeGenerator(String testCaseName, String expectedOutput, String methodOutput) throws Exception{
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

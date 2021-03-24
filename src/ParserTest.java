public class ParserTest {
    public static void main(String[] args) throws Exception {

        Reader rdr = new Reader("TestImmediate_copy.asm");
        rdr.readFile();
        SymbolTable symT= new SymbolTable();
        ErrorReporter er = new ErrorReporter("TestImmediate_copy.asm");
        Scanner scn= new Scanner(symT,er);
        Parser p = new Parser(rdr.getLineNum() + 1, symT, er, scn, rdr);
        p.parseToken();

        //Testing getInterRep()
        testParser("getInterRep()", "76", Integer.toString(p.getInterRep().getLength()));

        //Testing isNumeric()
        //testParser("isNumeric()", "true", Boolean.toString(p.isNumeric("50")));

    }
    public static void testParser(String caseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test -Parser Class- " + caseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}


/*
import java.util.ArrayList;
public class ParserTest {
    public static void main(String[] args) throws Exception {

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

        for(int i=0;i<assemblyUnit.size();i++){
            testParser("Test -Parser Class- getLine #"+i,"' '"+scanner.getTokens().get(i).get(0)+" null' '",IR.getLine(i).toString());
        }
    }

    public static void testParser(String caseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(caseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}
*/

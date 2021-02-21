import java.util.ArrayList;
public class ParserTest {
    public static void main(String[] args) throws Exception {

        Reader fileContent = null;
        try {
            fileContent = new Reader("testfile.asm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] assemblyUnit = fileContent.getAssemblyUnit();
        InterRep IR = new InterRep(assemblyUnit.length);

        Scanner scanner = new Scanner(assemblyUnit);
        Parser parser = new Parser(scanner, IR);

        for(int i=0;i<assemblyUnit.length;i++){
            testParser("Test #"+i,"' '"+scanner.getTokens().get(i)[0]+" ' '",IR.getLine(i).toString());
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

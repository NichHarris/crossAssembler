/*
import java.util.ArrayList;
public class ParserTest {
    public static void main(String[] args) throws Exception {

        Java.Reader fileContent = null;
        try {
            fileContent = new Java.Reader("testfile.asm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> assemblyUnit = fileContent.getAssemblyUnit();
        Java.InterRep IR = new Java.InterRep(assemblyUnit.size());

        Java.Scanner scanner = new Java.Scanner(assemblyUnit);
        Java.Parser parser = new Java.Parser(scanner, IR);

        for(int i=0;i<assemblyUnit.size();i++){
            testParser("Test -Java.Parser Class- getLine #"+i,"' '"+scanner.getTokens().get(i).get(0)+" null' '",IR.getLine(i).toString());
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

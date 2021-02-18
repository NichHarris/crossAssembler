package testing.Parser;

// TODO: sort out package dependency issues
/*
import java.io.File;
import java.util.Arrays;

//Ensure Functionality of Parser
public class ParserTest {
    public static void main(String[] args) throws Exception {
        //Empty File
        String f1 = "ParseEmptyTest.txt";
        String[] ac1 = {};
        testParse(f1, ac1, "Empty File");

        //Multiline File + All Addressing Modes
        String f2 = "sample.asm";
        String[] ac2 = {"Fct ldc.i3 3","    ldc.i3 1","    add     ; This is a comment","    ret"};
        testParse(f2, ac2, "Multiline File + All Addressing Modes");
    }

    public static void testParse(String fileName, String[] expected, String caseTitle) throws Exception {
        System.out.println("Test Parser - " + caseTitle);

        //Scan Then Parse Content of File
        File srcFile = new File(fileName);
        String[] parsedContent = Assembler.Parser(srcFile);

        //Actual Contents of File
        String[] actualContent = expected;

        //Compare toPrint with
        System.out.println(Arrays.toString(parsedContent));
        System.out.println(Arrays.toString(actualContent));

    }
}
 */
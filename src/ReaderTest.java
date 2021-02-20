import java.util.Arrays;

//Ensure Functionality of Reader
public class ReaderTest {
    public static void main(String[] args) throws Exception {
        //Empty File
        String f1 = "ReaderEmptyTest.txt";
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
        Reader reader = new Reader(fileName);
        String[] parsedContent = reader.getAssemblyUnit();

        //Actual Contents of File
        String[] actualContent = expected;

        //Compare toPrint with
        System.out.println(Arrays.toString(parsedContent));
        System.out.println(Arrays.toString(actualContent));

    }
}
import java.util.Arrays;

//Ensure Functionality of Reader
public class ReaderTest {
    public static void main(String[] args) throws Exception {
        //No file input
        String noFile = "";
        String[] ac = {};
        testFalseReader(noFile, "No File Given");

        //Non-existing file
        String nonExisting = "NoTextFile.txt";
        String[] ac3 = {};
        testFalseReader(noFile, "File does not exist");

        //Empty File
        String f1 = "ReaderEmptyTest.txt";
        String[] ac1 = {};
        testReader(f1, ac1, "Empty File");

        //Multiline File + All Addressing Modes
        String f2 = "sample.asm";
        String[] ac2 = {"Fct ldc.i3 3","    ldc.i3 1","    add     ; This is a comment","    ret"};
        testReader(f2, ac2, "Multiline File + All Addressing Modes");
    }

    public static void testReader(String fileName, String[] expected, String caseTitle) throws Exception {
        System.out.println("Test Reader - " + caseTitle);

        //Scan Then Parse Content of File
        Reader reader = new Reader(fileName);
        String[] readContent = reader.getAssemblyUnit();

        //Actual Contents of File
        String[] actualContent = expected;

        //Compare toPrint with
        System.out.println(Arrays.toString(readContent));
        System.out.println(Arrays.toString(actualContent));

    }
    public static void testFalseReader(String fileName, String caseTitle) throws Exception {
        System.out.println("Test Reader - " + caseTitle);
        //Scan Then Parse Content of File
        Reader reader = new Reader(fileName);
        String[] readContent = reader.getAssemblyUnit();
        System.out.println("java.lang.Exception: Error: Unable to open source file ''");
    }

}
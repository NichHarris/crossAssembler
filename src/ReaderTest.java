//Ensure Functionality of Reader
public class ReaderTest {
    public static void main(String[] args) throws Exception {
        Reader test = new Reader("testfile.asm");
        test.readFile();

        //testing character
        testReader("getChar()", "h", test.getChar(1).toString());

        //testing getLineNum
        testReader("getLineNum()", "106", Integer.toString(test.getLineNum()));

        //testing file content
        //aunit will not allow us to test the entire file content, only 2 lines will work
        //testReader("getFileContent()","",test.getFileContent());
    }

    public static void testReader(String caseTitle, String expectedOutput, String methodOutput) {
        System.out.println("Test " + caseTitle);
        System.out.println(expectedOutput);
        System.out.println(methodOutput);

    }
}
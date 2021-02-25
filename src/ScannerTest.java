import java.util.ArrayList;

//Testing File Scanner Functionality
public class ScannerTest {
    public static void main(String[] args) throws Exception {
        //Multiline File + All Addressing Modes
        ArrayList<String> ac2 = new ArrayList<>();
        ac2.add("Fct ldc.i3 3");
        ac2.add("    ldc.i3 1");
        ac2.add("    add     ; This is a comment");
        ac2.add("    ret");

        Scanner scan = new Scanner (ac2);

        System.out.println("done");

        // Test getTokens
        testScanner("Test -Scanner Class- getTokens", "Fct", scan.getTokens().get(0).get(0));

        // Test getComments
        testScanner("Test -Scanner Class- getComments", "This is a comment", scan.getComments()[2]);

        // Test printTokens()
        System.out.println("Test -Scanner Class- printTokens\n" + "[Fct, ldc.i3, 3][ldc.i3, 1][add][ret]");
        scan.printTokens();
    }

    public static void testScanner(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}
//Testing File Scanner Functionality
public class ScannerTest {
    public static void main(String[] args) throws Exception {
        //Multiline File + All Addressing Modes
        String[] ac2 = {"Fct ldc.i3 3","    ldc.i3 1","    add     ; This is a comment","    ret"};

        Scanner scan = new Scanner (ac2);

        // Test getTokens
        testScanner("Test -Scanner Class- getTokens", "Fct", scan.getTokens().get(0)[0]);

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
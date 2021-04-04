public class ErrorReporterTest {

    public static void main(String[] args) {
        ErrorReporter errorReport =  new ErrorReporter("testfile.asm");

        ErrorMsg message = new ErrorMsg("Error 1 ", 2 , 3);
        errorReport.record(message);
        System.out.println("Testing report Error");
        System.out.println("testfile.asm: Error: Line 2, Column 3: Error 1 ");
        errorReport.report();
    }

    public static void TestErrorReporter(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.print(methodOutput);
    }
}

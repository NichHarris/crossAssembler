public class ErrorMsgTest {
    public static void main(String[] args) throws Exception {
        Position position = new Position(1,2);
        ErrorMsg message = new ErrorMsg("Error 1", position);
        ErrorMsg message2 = new ErrorMsg("Error 2",3,4);


        errorMsgTester("Parameterized Constructor with position object","Error: Line 1, Column 2: Error 1",message.getErrorMsg());

        errorMsgTester("Parameterized Constructor without position object","Error: Line 3, Column 4: Error 2",message2.getErrorMsg());
    }

    public static void errorMsgTester(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test " + testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

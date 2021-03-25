public class ErrorMsgTest {
    public static void main(String[] args) throws Exception {
        Position position = new Position(1,2);
        ErrorMsg message = new ErrorMsg("Error1", position);
        ErrorMsg message2 = new ErrorMsg("Error2",3,4);


        errorMsgTester("Paramerterized Constructor with position object","Error: Line 1, Column 2: Error1",message.getErrorMsg());

        errorMsgTester("Paramerterized Constructor without position object","Error: Line 31, Column 42: Error23",message2.getErrorMsg());
    }

    public static void errorMsgTester(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test " + testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

package test.java;

import main.interfaces.IErrorMsg;
import main.java.ErrorMsg;
import main.java.Position;

public class ErrorMsgTest {
    public static void main(String[] args) throws Exception {
        TextFileGenerator.textFileGenerator("ErrorMsgTest.txt");
        IErrorMsg message = new ErrorMsg("Error 1", new Position(1,2));
        IErrorMsg message2 = new ErrorMsg("Error 2",3,4);


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

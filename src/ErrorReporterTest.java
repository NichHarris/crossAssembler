public class ErrorReporterTest {
    public static void main(String[] args) throws Exception {
        ErrorReporter er = new ErrorReporter();

        //Testing constructor, input at index 0
        TestErrorReporter("Test -ErrorReporter Class- ErrorReporter()", "Error: invalid character", er.errorList.get(0));

        //Testing an error from Scanner to reportedErrors
        er.addError(1,2,3);
        TestErrorReporter("Test -ErrorReporter Class- addError() from Scanner", "Error: eof in string", er.recordedErrors.get(0));

        //Testing an error from Scanner to reportedErrors for invalid characters
        er.addError(0, 3, 4, '%');
        TestErrorReporter("Test -ErrorReporter Class- addError() from Scanner (invalid character)", "Error: invalid character", er.recordedErrors.get(2));

        //Testing an error from Parser to reportedErrors
        Position p1 = new Position(1, 2);
        Token t1 = new Token(p1, "halt", TokenType.Mnemonic);
        er.addError(3, t1);
        TestErrorReporter("Test -ErrorReporter Class- addError() from Parser", "Error: Instructions with inherent mode addressing do not have an operand field.", er.recordedErrors.get(6));

        //Testing recordedErrors array list at index 4
        TestErrorReporter("Test -ErrorReporter Class- reportErrors()", "%", er.recordedErrors.get(4));
        System.out.println();

//        //Testing invalidChars array list => null pointer exception because we are trying to print the value of null
//        er.fillInvalidChars();
//        TestErrorReporter("Test -ErrorReporter Class- fillInvalidChars()", "null", Integer.toString(er.invalidChars.get(0)));

        //Testing isValid()
        er.isValid('2',9,8);
        TestErrorReporter("Test -ErrorReporter Class- isValid()", "Error: Instructions with inherent mode addressing do not have an operand field.", er.recordedErrors.get(6));
    }

    public static void TestErrorReporter(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.print(methodOutput);
    }
}

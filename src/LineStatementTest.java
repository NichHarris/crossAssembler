public class LineStatementTest {
    public static void main(String[] args) throws Exception {
        LineStatement ls = new LineStatement("Fct", new Instruction("ldc.i3","3"), "");

        TestLineStatement("Test getLabel()","Fct", ls.getLabel());
        TestLineStatement("Test getInstruction()", "'ldc.i3 3'", ls.getInstruction().toString());
        TestLineStatement("Test getComment()", "", ls.getComment());
        TestLineStatement("Test getCode()","144", ls.getCode().toString());
        TestLineStatement("Test toString()", "'Fct 'ldc.i3 3' '", ls.toString());
    }

    public static void TestLineStatement(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}


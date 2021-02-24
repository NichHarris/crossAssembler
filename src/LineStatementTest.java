public class LineStatementTest {
    public static void main(String[] args) throws Exception {
        //1 Testing Default constructor
        LineStatement ls = new LineStatement();
        TestLineStatement("Test Default Constructor", " 'null null' ", ls.getLabel() + " " + ls.getInstruction() + " " + ls.getComment());

        //2 Testing Parametrized constructor for object initialization with label, instruction and comment
        LineStatement ls1 = new LineStatement("Fct", new Instruction("ldc.i3","3"), "");
        TestLineStatement("Test Parameterized Constructor with label, instruction and comment", "Fct 'ldc.i3 3' ", ls1.getLabel() + " " + ls1.getInstruction().toString() + " " + ls1.getComment());

        //Testing Setter for label
        ls1.setLabel("RA");
        TestLineStatement("Test setLabel()", "RA", ls1.getLabel());

        //Testing Setter for instruction
        ls1.setInstruction(new Instruction("pop", ""));
        TestLineStatement("Test setInstruction()", "'pop '", ls1.getInstruction().toString());

        //Testing Setter for comments
        ls1.setComment("; new comment");
        TestLineStatement("Test setComment()", "; new comment", ls1.getComment());

        //Testing the function getLabel() from LineStatement Class
        TestLineStatement("Test getLabel()","RA", ls1.getLabel());

        //Testing the function getInstruction() from LineStatement Class
        TestLineStatement("Test getInstruction()", "'pop '", ls1.getInstruction().toString());

        //Testing the function getComment() from LineStatement Class
        TestLineStatement("Test getComment()", "; new comment", ls1.getComment());

        //Testing the function getCode() from LineStatement Class
        //TestLineStatement("Test getCode()","144", ls1.getCode().toString());

        //Testing the function toString() from LineStatement Class
        TestLineStatement("Test toString()", "'RA 'pop ' ; new comment'", ls1.toString());
    }

    public static void TestLineStatement(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}
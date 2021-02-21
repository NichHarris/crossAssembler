public class LineStatementTest {
    public static void main(String[] args) throws Exception {
        //Testing Default constructor
        LineStatement ls = new LineStatement();
        TestLineStatement("Test Default Constructor", " null ", ls.getLabel() + " " + ls.getInstruction() + " " + ls.getComment());

        //Testing Parametrized constructor for object initialization with label, instruction and comment
        LineStatement ls1 = new LineStatement("Fct", new Instruction("ldc.i3","3"), "");
        TestLineStatement("Test Parameterized Constructor with label, instruction and comment", "Fct 'ldc.i3 3' ", ls1.getLabel() + " " + ls1.getInstruction().toString() + " " + ls1.getComment());

        //Testing Parametrized constructor for object initialization with instruction and comment
        LineStatement ls2 = new LineStatement(new Instruction("add", ""), "; This is a comment");
        TestLineStatement("Test Parameterized Constructor with instruction and comment", "'add ' ; This is a comment", ls2.getInstruction().toString() + " " + ls2.getComment());

        //Testing Parametrized constructor for object initialization with label and instruction
        LineStatement ls3 = new LineStatement("Fct", new Instruction("ldc.i3","3"));
        TestLineStatement("Test Parameterized Constructor with label and instruction", "Fct 'ldc.i3 3'", ls3.getLabel() + " " + ls3.getInstruction().toString());

        //Testing Parametrized constructor for object initialization with label
        LineStatement ls4 = new LineStatement("Fct");
        TestLineStatement("Test Parameterized Constructor with a label", "Fct", ls4.getLabel());

        //Testing Parametrized constructor for object initialization with label and comments
        LineStatement ls5 = new LineStatement("Fct", "; this is another comment");
        TestLineStatement("Test Parameterized Constructor with label and comments", "Fct ; this is another comment", ls5.getLabel() + " " + ls5.getComment());

        //Testing Setter for label
        ls2.setLabel("RA");
        TestLineStatement("Test setLabel()", "RA", ls2.getLabel());

        //Testing Setter for instruction
        ls2.setInstruction(new Instruction("pop", ""));
        TestLineStatement("Test setInstruction()", "'pop '", ls2.getInstruction().toString());

        //Testing Setter for comments
        ls2.setComment("; new comment");
        TestLineStatement("Test setComment()", "; new comment", ls2.getComment());

        //Testing the function getLabel() from LineStatement Class
        TestLineStatement("Test getLabel()","Fct", ls1.getLabel());

        //Testing the function getInstruction() from LineStatement Class
        TestLineStatement("Test getInstruction()", "'ldc.i3 3'", ls1.getInstruction().toString());

        //Testing the function getComment() from LineStatement Class
        TestLineStatement("Test getComment()", "", ls1.getComment());

        //Testing the function getCode() from LineStatement Class
        TestLineStatement("Test getCode()","144", ls1.getCode().toString());

        //Testing the function toString() from LineStatement Class
        TestLineStatement("Test toString()", "'Fct 'ldc.i3 3' '", ls1.toString());
    }

    public static void TestLineStatement(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}


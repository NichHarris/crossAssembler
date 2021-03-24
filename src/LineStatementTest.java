public class LineStatementTest {
    public static void main(String[] args) throws Exception {
        //Testing Default constructor
        ILineStatement ls = new LineStatement();
        TestLineStatement("Test -LineStatement Class- Default Constructor", " '\": -1\" ' ", ls.getLabel() + " " + ls.getInstruction() + " " + ls.getComment());

        //Testing Parametrized constructor for object initialization with label, instruction and comment
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");
        IInstruction instruction = new Instruction (m, op);
        LineStatement ls1 = new LineStatement("Fct", instruction, "");
        TestLineStatement("Test -LineStatement Class- Parameterized Constructor with label, instruction and comment", "Fct '\"addv.u3: 152\" 3' ", ls1.getLabel() + " " + ls1.getInstruction().toString() + " " + ls1.getComment());

        //Testing setLabel() and getLabel()
        ls1.setLabel("RA");
        TestLineStatement("Test -LineStatement Class- setLabel()", "RA", ls1.getLabel());

        //Testing setInstruction() and getInstruction()
        m = new Mnemonic("mul", code);
        op = new Operand("4");
        instruction = new Instruction(m, op);
        ls1.setInstruction(instruction);
        TestLineStatement("Test -LineStatement Class- setInstruction() and getInstruction", "'\"mul: 152\" 4'", ls1.getInstruction().toString());

        //Testing setComment() and getComment()
        ls1.setComment("; new comment");
        TestLineStatement("Test -LineStatement Class- setComment()", "; new comment", ls1.getComment());

        //Testing the function getLabel()
        TestLineStatement("Test -LineStatement Class- getLabel()","RA", ls1.getLabel());
    }

    public static void TestLineStatement(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}
public class OperandTest {
    public static void main(String[] args) throws Exception {
        //Testing Default constructor and toString()
        IOperand test1 = new Operand();
        TestMnemonic("Test -Operand Class- Default Constructor and toString()", "null",test1.toString());

        //Testing parametrized constructor
        test1 = new Operand("45");
        TestMnemonic("Test -Operand Class- Parametrized Constructor", "45",test1.toString());

        //Testing setOperand()
        test1.setOperand("12");
        TestMnemonic("Test -Operand Class- setOperand", "12",test1.toString());

        //Testing isDigit()
        boolean flag = false;
        flag = test1.isDigit();
        TestMnemonic("Test -Operand Class- isDigit", "true",Boolean.toString(flag));

        test1 = new Operand("abc");
        flag = test1.isDigit();
        TestMnemonic("Test -Operand Class- isDigit", "false",Boolean.toString(flag));
    }

    public static void TestMnemonic(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

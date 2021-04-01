package test.java;

import main.interfaces.IOperand;
import main.java.Operand;

public class OperandTest {
    public static void main(String[] args) throws Exception {
        //Testing Default constructor and toString()
        IOperand test1 = new Operand();
        TestMnemonic("Test -Operand Class- Default Constructor and toString()", "",test1.getOp());

        //Testing parametrized constructor
        test1 = new Operand("45");
        TestMnemonic("Test -Operand Class- Parametrized Constructor", "45",test1.getOp());

        //Testing setOperand()
        test1.setOperand("12");
        TestMnemonic("Test -Operand Class- setOperand", "12",test1.getOp());

        //Testing isNumeric()
        boolean flag = false;
        flag = test1.isNumeric();
        TestMnemonic("Test -Operand Class- isNumeric", "true",Boolean.toString(flag));

        test1 = new Operand("abc");
        flag = test1.isNumeric();
        TestMnemonic("Test -Operand Class- isNumeric", "false",Boolean.toString(flag));
    }

    public static void TestMnemonic(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

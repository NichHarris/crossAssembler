public class InstructionTest {
    public static void main(String[] args) throws Exception {

        //Testing the default constructor
        Instruction instruction1 = new Instruction();
        testInstruction("Test Default Constructor", "null null", instruction1.getMnemonic()+" "+instruction1.getOperand());

        //testing the Mnemonic only Constructor constructor
        instruction1=new Instruction("pop");
        testInstruction("Test Mnemonic only Constructor", "pop null", instruction1.getMnemonic()+" "+instruction1.getOperand());

        //Creating an instruction with both a Mnemonic and Operand
        instruction1 = new Instruction ("halt", "i3");

        //Testing the Constructor of the class with getOperand() and getMnemonic()
        testInstruction("Test Mneomic and Operand Constructor", "halt i3", instruction1.getMnemonic()+" "+instruction1.getOperand());

        //Testing setMnemonic(String m) and getMnemonic()
        instruction1.setMnemonic("not");
        testInstruction("Test setMnemonic", "not", instruction1.getMnemonic());

        //Testing setOperand(String o) and getOperand()
        instruction1.setOperand("12");
        testInstruction("Test setOperand", "12", instruction1.getOperand());

        //Testing the function getCode() from Instruction Class
        testInstruction("Test getCode", "12", Integer.toString(instruction1.getCode()));

        //Testing the function getDigit() Instruction Class
        testInstruction("Test isDigit", "true", Boolean.toString(instruction1.isDigit()));

        //Testing the function toString() from Instruction Class
        testInstruction("Test toString", "'not 12'", instruction1.toString());
    }

    public static void testInstruction(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        //TestCase Name
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

}
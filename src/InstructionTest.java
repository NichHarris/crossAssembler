public class InstructionTest {
    public static void main(String[] args) throws Exception {

        //Testing the default constructor
        Instruction instruction1 = new Instruction();
        testInstruction("Test -Instruction Class- Default Constructor", "null null", instruction1.getMnemonic()+" "+instruction1.getOperand());

        //Creating an instruction with both a Mnemonic and Operand
        instruction1 = new Instruction ("halt", "i3");

        //Testing the Constructor of the class with getOperand() and getMnemonic()
        testInstruction("Test -Instruction Class- Mnemonic and Operand Constructor", "halt i3", instruction1.getMnemonic()+" "+instruction1.getOperand());

        //Testing setMnemonic(String m) and getMnemonic()
        instruction1.setMnemonic("not");
        testInstruction("Test -Instruction Class- setMnemonic", "not", instruction1.getMnemonic());

        //Testing setOperand(String o) and getOperand()
        instruction1.setOperand("12");
        testInstruction("Test -Instruction Class- setOperand", "12", instruction1.getOperand());

        //Testing the function getDigit() Instruction Class
        testInstruction("Test -Instruction Class- isDigit", "true", Boolean.toString(instruction1.isDigit()));

        //Testing the function toString() from Instruction Class
        testInstruction("Test -Instruction Class- toString", "'not 12'", instruction1.toString());

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
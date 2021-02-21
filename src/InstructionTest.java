public class InstructionTest {
    public static void main(String[] args) throws Exception {
        //Creating two instructions to test with
        Instruction instruction1 = new Instruction ("not", "12");

        //Testing the function getCode() from Instruction Class
        testInstruction("Test Operand", "12", Integer.toString(instruction1.getCode()));
        //Testing the function getDigit() Instruction Class
        testInstruction("Test isDigit", "true", Boolean.toString(instruction1.isDigit()));
        //Testing the function toString() from Instruction Class
        testInstruction("Test toString", "'not 12'", instruction1.toString());
    }

    public static void testInstruction(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}
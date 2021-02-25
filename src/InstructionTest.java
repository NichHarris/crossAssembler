public class InstructionTest {
    public static void main(String[] args) throws Exception {

        //Testing the default constructor
        IInstruction instruction1 = new Instruction();
        testInstruction("Test -Instruction Class- Default Constructor", "null null", instruction1.getMnemonic().getMne() + " " + instruction1.getOperand().toString());

        //Creating an instruction with both a Mnemonic and Operand
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("halt");
        instruction1 = new Instruction ("halt", "i3");

        //Testing the Constructor of the class with getOperand() and getMnemonic()
        testInstruction("Test -Instruction Class- Mnemonic and Operand Constructor", "halt i3", instruction1.getMnemonic()+" "+instruction1.getOperand());

        //Testing setMnemonic(IMnemonic m) and getMnemonic()
        int code = symbolTable.getCode("not");
        IMnemonic m = new Mnemonic("not", code);
        instruction1.setMnemonic();
        testInstruction("Test -Instruction Class- setMnemonic", "not", instruction1.getMnemonic().getMne());

        //Testing setOperand(String o) and getOperand()
        instruction1.setOperand("12");
        testInstruction("Test -Instruction Class- setOperand", "12", instruction1.getOperand().toString());

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
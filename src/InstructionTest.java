public class InstructionTest {
    public static void main(String[] args) throws Exception {

        //Testing the default constructor
        IInstruction instruction1 = new Instruction();
        testInstruction("Test -Instruction Class- Default Constructor", "null null", instruction1.getMnemonic().getMne() + " " + instruction1.getOperand().toString());

        //Creating an instruction with both a Mnemonic and Operand
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");
        instruction1 = new Instruction (m, op);

        //Testing the Constructor of the class with getOperand() and getMnemonic()
        testInstruction("Test -Instruction Class- Mnemonic and Operand Constructor", "addv.u3 3", instruction1.getMnemonic().getMne()+" "+instruction1.getOperand().toString());

        //Testing setMnemonic(IMnemonic m), getMnemonic()
        code = symbolTable.getCode("not");
        m = new Mnemonic("not", code);
        instruction1.setMnemonic(m);
        testInstruction("Test -Instruction Class- setMnemonic", "not ", instruction1.getMnemonic().getMne());

        //Testing setOperand(String o) and getOperand()
        op = new Operand("4");
        instruction1.setOperand(op);
        testInstruction("Test -Instruction Class- setOperand", "4", instruction1.getOperand().toString());

        //Testing the function toString() from Instruction Class
        testInstruction("Test -Instruction Class- toString", "'not 4'", instruction1.toString());

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
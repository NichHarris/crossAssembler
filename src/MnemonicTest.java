public class MnemonicTest {
    public static void main(String[] args) throws Exception {

        Mnemonic test1;

        //Testing Default constructor
        test1 = new Mnemonic();
        TestMnemonic("Test -Mnemonic Class- Default Constructor", "null -1",test1.getMnemonic()+" "+Integer.toString(test1.getOpcode()));

        //Testing paramerterized constructor/Integration test?
        //test1 = new Mnemonic("ADD", 45,new Operand("i5"));
        TestMnemonic("Test -Mnemonic Class- Default Constructor", "ADD 45",test1.getMnemonic()+" "+Integer.toString(test1.getOpcode()));

        //Testing setMnemonic and getMnemonic
        test1.setMnemonic("JMP");
        TestMnemonic("Test -Mnemonic Class- setMnemonic and getMnemonic", "JMP", test1.getMnemonic());

        //Testing setOpCode and getOpCode()
        test1.setOpcode(12);
        TestMnemonic("Test -Mnemonic Class- set Opcode and getOpCode", "12", Integer.toString(test1.getOpcode()));

        //Testing setOperand and getOperand
        //test1.setOperand(new Operand("c4"));
        TestMnemonic("Test -Mnemonic Class- setOperand and getOperand", "c4", Integer.toString(test1.getOpcode()));

        //Integration Test
        Mnemonic test2 = new Mnemonic();
        test2.setMnemonic("Halt");
        test2.setOpcode(52);
        //test2.setOperand(new Operand("g2"));
        TestMnemonic("Test -Mnemonic Class- Integration Test", "Halt 52",test1.getMnemonic()+" "+Integer.toString(test1.getOpcode()));
    }

    public static void TestMnemonic(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

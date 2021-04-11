package test.java;

import main.interfaces.IMnemonic;
import main.java.Mnemonic;

public class MnemonicTest {
    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("MnemonicTest.txt");
        //Testing Default constructor
        IMnemonic test1 = new Mnemonic();
        TestMnemonic("Test -Mnemonic Class- Default Constructor", " -1",test1.getMne()+" "+Integer.toString(test1.getOpcode()));

        //Testing parametrized constructor and getMnemonic
        test1 = new Mnemonic("ADD", 45);
        TestMnemonic("Test -Mnemonic Class- Parametrized Constructor and getMnemonic", "ADD 45",test1.getMne()+" "+Integer.toString(test1.getOpcode()));

        //Testing setOpCode and getOpCode()
        test1.setOpcode(12);
        TestMnemonic("Test -Mnemonic Class- set Opcode and getOpCode", "12", Integer.toString(test1.getOpcode()));
    }

    public static void TestMnemonic(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

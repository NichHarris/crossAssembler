package test.java;

import main.interfaces.ISymbolTable;
import main.java.SymbolTable;

public class SymbolTableTest {
    public static void main(String[] args) throws Exception {
        //Testing invalid key
        ISymbolTable st = new SymbolTable();
        testKey("Test -SymbolTable Class- Invalid Key", "-1", Integer.toString(st.getCode("apple")));

        //Testing valid key
        testKey("Test -SymbolTable Class- Valid Key", "0", Integer.toString(st.getCode("halt")));

        //Test getCode
        testKey("Test -SymbolTable Class- getCode", "1",Integer.toString(st.getCode("pop")));
    }

    public static void testKey(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        //TestCase Name
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

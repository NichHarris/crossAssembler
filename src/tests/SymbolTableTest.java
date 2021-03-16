package tests;

import main.SymbolTable;

public class SymbolTableTest {
    public static void main(String[] args) throws Exception {
        //Testing invalid key
        SymbolTable st = new SymbolTable();
        testKey("Test -Java.SymbolTable Class- Invalid Key", "-1", Integer.toString(st.getCode("apple")));

        //Testing valid key
        testKey("Test -Java.SymbolTable Class- Valid Key", "0", Integer.toString(st.getCode("halt")));

        //Test getCode
        testKey("Test -Java.SymbolTable Class- getCode", "1",Integer.toString(st.getCode("pop")));
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

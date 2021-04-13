package test.java;

import main.interfaces.*;
import main.java.*;

public class ScannerTest {
    public static void main(String[] args) throws Exception {
        TextFileGenerator.textFileGenerator("ScannerTest.txt");

        IReader test = new Reader("TestImmediate.asm");
        test.readFile();
        ISymbolTable symT= new SymbolTable();
        IErrorReporter er = new ErrorReporter("TestImmediate.asm");
        IScanner scn= new Scanner(symT,er);

        //used for testing invalid cases
        IErrorReporter er2 = new ErrorReporter("scannerTests.asm");
        IScanner scn2= new Scanner(symT,er2);

        //used for testing .cstring
        IErrorReporter er3 = new ErrorReporter("cstring_scannerTest.asm");
        IScanner scn3= new Scanner(symT,er3);

        //Testing scanFile()
        testScanner("scanFile()","; TestImmediate.asm - Test immediate instructions.",scn.scanFile(test).getName());

        //Testing getTokenType() - Generate Correct Token Type
        testScanner("getTokenType()","LabelOperand",scn.getTokenType("ADD", 2).name());

        //Testing isNumeric()
        testScanner("isNumeric()", "true", Boolean.toString(scn.isNumeric("50")));

        //Testing getCurrPos()
        testScanner("getCurrPos()", "50", Integer.toString(scn.getCurrPos()));

        scn.getErrors().report();

        // Multiple Spaces and Tabs in Front, In Between Tokens, End of Line
        testScanner("Multiple Spaces or Tabs found", "None", scn2.getTokenType("",3).name());

        // No Spaces in Front and End of Line
        testScanner("No Spaces in Front and End of Line", "None", scn2.getTokenType("",4).name());

        // Empty Lines with and without spaces
        testScanner("Empty Lines with and without spaces", "None", scn2.getTokenType("",5).name());

        // Invalid Characters
        testScanner("Invalid Characters", "None", scn2.getTokenType("",3).name());

        // Closing .cstring quotation marks "ABC
        testScanner("Closing .cstring quotation marks \"ABC", "false",(TokenType.None == scn3.getTokenType("",3))?"false":"true" );
    }

    public static void testScanner(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test " + testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

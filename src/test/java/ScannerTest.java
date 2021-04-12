package test.java;

import main.interfaces.IErrorReporter;
import main.interfaces.IReader;
import main.interfaces.IScanner;
import main.interfaces.ISymbolTable;
import main.java.ErrorReporter;
import main.java.Reader;
import main.java.Scanner;
import main.java.SymbolTable;

public class ScannerTest {
    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("ScannerTest.txt");

        IReader test = new Reader("TestImmediate.asm");
        test.readFile();
        ISymbolTable symT= new SymbolTable();
        IErrorReporter er = new ErrorReporter("TestImmediate.asm");
        IScanner scn= new Scanner(symT,er);

        //Testing scanFile()
        testScanner("scanFile()","; TestImmediate.asm - Test immediate instructions.",scn.scanFile(test).getName());

        //Testing getTokenType()
        testScanner("getTokenType()","LabelOperand",scn.getTokenType("ADD", 2).name());

        //Testing isNumeric()
        testScanner("isNumeric()", "true", Boolean.toString(scn.isNumeric("50")));

        //Testing getCurrPos()
        testScanner("getCurrPos()", "50", Integer.toString(scn.getCurrPos()));

        //Testing setCurrPos()
        scn.setCurrPos(6);
        testScanner("setCurrPos()", "6", Integer.toString(scn.getCurrPos()));

        scn.getErrors().report();

        // Multiple Spaces and Tabs in Front, In Between Tokens, End of Line
        // No Spaces in Front and End of Line
        // Empty Lines with and without spaces
        // Invalid Characters
        // Closing .cstring quotation marks
        // Generate Correct Token Type

    }

    public static void testScanner(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test " + testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

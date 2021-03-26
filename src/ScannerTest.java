public class ScannerTest {
    public static void main(String[] args) throws Exception {

        Reader test = new Reader("TestImmediate.asm");
        test.readFile();
        SymbolTable symT= new SymbolTable();
        ErrorReporter er = new ErrorReporter("TestImmediate.asm");
        Scanner scn= new Scanner(symT,er);

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

    }

    public static void testScanner(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test " + testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

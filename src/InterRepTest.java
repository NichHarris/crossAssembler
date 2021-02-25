public class InterRepTest {
    public static void main(String[] args) throws Exception {

        //Paramerized Constructor
        InterRep IR = new InterRep(1);

        //1 Testing setter with a Line Statement
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");
        IInstruction instruction = new Instruction (m, op);
        LineStatement ls = new LineStatement("Fct", instruction, "");

        //Testing addLine1
        IR.addLine(0, ls);
        TestInterRep("Test -InterRep Class- setter with a LineStatement object", "Fct addv.u3 3", IR.getLine(0).toString());

        //Testing addLine 2
        IR.addLine(0, "jmp", instruction, "; Test Comment 2");
        TestInterRep("Test -InterRep Class- setter with Line Statement components label, instruction and comment", "'jmp 'add 5' ; Test Comment 2'", IR.getLine(0).toString());

        //4 Testing Get LineStatement
        ILineStatement ls1 = IR.getLine(0);
        TestInterRep("Test -InterRep Class- getLine()", "'jmp 'add 5' ; Test Comment 2'", ls1.toString());

        //5 Testing Get length of LineStatement
        int numberOfLines = IR.getLength();
        TestInterRep("Test -InterRep Class- getLength()", "1", Integer.toString(numberOfLines));

        //6 **Testing toString() having problem with testing toString as aunit will only test in pairs but toString will print out the entire asm file (26 lines) so
        //to go around this problem we are printing just the first line**
        TestInterRep("Test -InterRep Class- toString()", "Line 0: 'jmp 'add 5' ; Test Comment 2' 100", IR.toString());
    }

    public static void TestInterRep(String testCaseName, String expectedOutput, String methodOutput) throws Exception{
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

    public static void testFalseInterRep(String testCaseName, String methodOutput) throws Exception {

        System.out.println(testCaseName);

        // actual output
        System.out.println(methodOutput);
    }
}

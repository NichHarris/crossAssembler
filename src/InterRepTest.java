public class InterRepTest {
    public static void main(String[] args) throws Exception {

        //Paramerized Constructor
        InterRep IR = new InterRep(1);

        // Testing setter with a Line Statement
        ISymbolTable symbolTable = new SymbolTable();
        int code = symbolTable.getCode("addv.u3");
        IMnemonic m = new Mnemonic("addv.u3", code);
        IOperand op = new Operand("3");
        IInstruction instruction = new Instruction (m, op);
        LineStatement ls = new LineStatement("Fct", instruction, "");

        // Testing addLine1
        IR.addLine(0, ls);
        TestInterRep("Test -InterRep Class- addLine with just a line statement object", "'Fct '\"addv.u3: 152\" 3' '", IR.getLine(0).toString());

        // Testing addLine 2
        IR.addLine(0, "jmp", instruction, "; Test Comment 2");
        TestInterRep("Test -InterRep Class- addLine with Line Statement components label, instruction and comment", "'jmp '\"addv.u3: 152\" 3' ; Test Comment 2'", IR.getLine(0).toString());

        // Testing Get LineStatement
        ILineStatement ls1 = IR.getLine(0);
        TestInterRep("Test -InterRep Class- getLine()", "'jmp '\"addv.u3: 152\" 3' ; Test Comment 2'", ls1.toString());

        // Testing Get length of LineStatement
        int numberOfLines = IR.getLength();
        TestInterRep("Test -InterRep Class- getLength()", "1", Integer.toString(numberOfLines));

        // feeding an empty line statement()
        LineStatement ls2 = new LineStatement();
        IR.addLine(0, ls2);
        TestInterRep("Test -InterRep Class- empty LineStatement passed to InterRep object", "' '\": -1\" ' '", IR.getLine(0).toString());

        //Integration Test
        InterRep IR2 = new InterRep(1);
        LineStatement ls3 = new LineStatement();
        IR2.addLine(0, ls3);
        IR2.addLine(0,"jmp", instruction, "; This is a comment");
        TestInterRep("Test -InterRep Class- Integration", "'jmp '\"addv.u3: 152\" 3' ; This is a comment'",IR2.getLine(0).toString());

        //Set LineStatement's Label
        IR2.setLabel(0, "ADD");
        TestInterRep("Test -InterRep Class- setLabel()", "ADD", IR2.getLine(0).getLabel());

        //Set LineStatement's comments
        IR2.setComment(0, "; this is a new comment from the setter");
        TestInterRep("Test -InterRep Class- setComment()", "; this is a new comment from the setter", IR2.getLine(0).getComment());

        //Set LineStatement's instruction and hasInstruction method
        IR2.setInstruction(0, instruction);
        TestInterRep("Test -InterRep- setInstruction() and hasInstruction()","true",Boolean.toString(IR2.hasInstruction(0)));

        //Set Addr and get Addr
        IR2.setAddr(0,100);
        TestInterRep("Test -InterRep- setAddr() and getAddr()","100",Integer.toString(IR2.getAddr(0)));

        //Set code of mnemonic
        IR2.updateCode(0);
        TestInterRep("Test -InterRep- updateCode()","ADD",IR2.getLine(0).getLabel());

        // **Testing toString() having problem with testing toString as aunit will only test in pairs but toString will print out the entire asm file (26 lines) so
        //to go around this problem we are printing just the first line**
        TestInterRep("Test -InterRep Class- toString()", "Line 0: ' '\": -1\" ' '", IR.toString());

}
public static void TestInterRep(String testCaseName, String expectedOutput, String methodOutput) throws Exception{
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

}

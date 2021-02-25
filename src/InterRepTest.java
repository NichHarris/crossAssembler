public class InterRepTest {
    public static void main(String[] args) throws Exception {
        InterRep IR = new InterRep(1);

        //negative int
//        InterRep IR1 = new InterRep(-3);
//        IR.getLine(0).toString();
        /*
        negative integer testing doesn't work because it causes an exception
        but the rest results in a runtime error.
         */

        //1 Testing setter with a Line Statement
        LineStatement ls = new LineStatement("Fct", new Instruction("ldr.i3", "3"), "; Test Comment 1");
        IR.setLine(0, ls);
        TestInterRep("Test -InterRep Class- setter with a LineStatement object", "'Fct 'ldr.i3 3' ; Test Comment 1'", IR.getLine(0).toString());

        //2 Testing setter with Line Statement (label, instruction and comment)
        IR.setLine(0, "jmp", new Instruction("add", "5"), "; Test Comment 2");
        TestInterRep("Test -InterRep Class- setter with Line Statement components label, instruction and comment", "'jmp 'add 5' ; Test Comment 2'", IR.getLine(0).toString());

        //3 Testing setter with Line Statement code
        IR.setCode(0, 100);
        TestInterRep("Test -InterRep Class- setter with LineStatement Code", "100", Integer.toString(IR.getCode(0)));

        //4 Testing Get LineStatement
        ILineStatement ls1 = IR.getLine(0);
        TestInterRep("Test -InterRep Class- getLine()", "'jmp 'add 5' ; Test Comment 2'", ls1.toString());

        //5 Testing Get length of LineStatement
        int numberOfLines = IR.getLength();
        TestInterRep("Test -InterRep Class- getLength()", "1", Integer.toString(numberOfLines));

        //6 **Testing toString() having problem with testing toString as aunit will only test in pairs but toString will print out the entire asm file (26 lines) so
        //to go around this problem we are printing just the first line**
        TestInterRep("Test -InterRep Class- toString()", "Line 0: 'jmp 'add 5' ; Test Comment 2' 100", IR.toString());

        //**There is a problem with toString where it adds another line at the end of the function making aunit fail because it thinks there is another test**
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

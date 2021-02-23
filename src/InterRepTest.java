public class InterRepTest {
    public static void main(String[] args) throws Exception {
        InterRep IR = new InterRep(1);

//        //**Testing Default constructor //Problem With default constructor lines.length is null giving us a run time error when we try to call InterRep.toString()**
//        InterRep IR1 = new InterRep();
//        TestInterRep("Testing Default Constructor","", IR1.toString());
        //1 Testing setter with a Line Statement
        LineStatement ls = new LineStatement("Fct", new Instruction("ldr.i3", "3"), "; Test Comment 1");
        IR.setLine(0, ls);
        TestInterRep("Test setter with a LineStatement object", "'Fct 'ldr.i3 3' ; Test Comment 1'", IR.getLine(0).toString());

        //2 Testing setter with Line Statement (label, instruction and comment)
        IR.setLine(0, "jmp", new Instruction("add", "5"), "; Test Comment 2");
        TestInterRep("Test setter with Line Statement components label, instruction and comment", "'jmp 'add 5' ; Test Comment 2'", IR.getLine(0).toString());

        //7 Testing setter with Line Statement code
        IR.setCode(0, 100);
        TestInterRep("Test setter with LineStatement Code", "100", Integer.toString(IR.getCode(0)));

        //8 Testing Get LineStatement
        ILineStatement ls1 = IR.getLine(0);
        TestInterRep("Test getLine()", "'Fct 'null null' ; Test Comment 6'", ls1.toString());

        //9 Testing Get length of LineStatement
        int numberOfLines = IR.getLength();
        TestInterRep("Test getLength()", "1", Integer.toString(numberOfLines));

        //10 **Testing toString() having problem with testing toString as aunit will only test in pairs but toString will print out the entire asm file (26 lines) so
        //to go around this problem we are printing just the first line**
        TestInterRep("Test toString()", "Line 0: 'Fct 'null null' ; Test Comment 6' 100", IR.toString());

        //**There is a problem with toString where it adds another line at the end of the function making aunit fail because it thinks there is another test**
    }

    public static void TestInterRep(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

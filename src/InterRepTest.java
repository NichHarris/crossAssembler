public class InterRepTest {
    public static void main(String[] args) throws Exception {
        InterRep IR = new InterRep();

        //Testing LineStatement with a LineStatement object
        //TestInterRep("Test setLine()");

        //Set LineStatement with a label, Instruction object and comment
        //TestInterRep("Test setLine()");

        //Set LineStatement with an Instruction object and comment
        //TestInterRep("Test setLine()");

        //Set LineStatement with a label and Instruction object
        //TestInterRep("Test setLine()");

        //Set LineStatement with a label
        //TestInterRep("Test setLine()");

        //Set LineStatement with a label and comment
        //TestInterRep("Test setLine()");

        //Set the code of a particular LineStatement
        //TestInterRep("Test setCode()");

        //TestInterRep("Test getLine()");
        //TestInterRep("Test getLength()");
        //TestInterRep("Test toString()");
    }

    public static void TestInterRep(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }




//    //Parameterized Constructor
//    public InterRep(int len) {
//        lines = new LineStatement[len];
//        codes = new Integer[len];
//    }


//    //Get LineStatement
//    public LineStatement getLine(int i) {
//        return lines[i];
//    }
//
//    //Get length of LineStatement
//    public int getLength() {
//        return lines.length;
//    }
//    //Returns a String representable of an InterRep object
//    public String toString() {
//        String IR = "";
//        for(int i = 0; i < this.getLength(); i++) {
//            IR = IR.concat(String.format("Line %s: %s", i, lines[i].toString()));
//            IR = IR.concat(String.format(" %s", codes[i]));
//            IR = IR.concat("\n");
//        }
//        return IR;
//    }
}

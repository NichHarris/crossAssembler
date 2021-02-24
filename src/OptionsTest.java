public class OptionsTest {
    public static void main(String[] args) throws Exception{
        //Incorrect Option
        String[] options = {"-v", "-l"};
        Options op = new Options(options);

        //empty
//
//        testFalseInstruction("Test Empty", Boolean.toString(options.toString().isEmpty()));


        //invalid char (not -h,-l,-v)

        testFalseInstruction("Test Invalid Char",Boolean.toString(op.isListing()) );

        //if not --options

        testFalseInstruction("Test Two Character Option", Boolean.toString(op.isListing()));

        //correct options
        String[] options2 = {"-v","-l"};
        Options op2 = new Options(options2);

        //Testing the function isListing() from Options Class
        testInstruction("Test isListing()", "true", Boolean.toString(op2.isListing()));

        testInstruction("Test isVerbose()", "true", Boolean.toString(op2.isVerbose()));
    }

    public static void testInstruction(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

    public static void testFalseInstruction(String testCaseName, String methodOutput) throws Exception {

        System.out.println(testCaseName);

        // actual output
        System.out.println(methodOutput);
    }
}


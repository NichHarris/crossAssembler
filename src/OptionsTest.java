public class OptionsTest {
    public static void main(String[] args) throws Exception{
        //Incorrect Option
        String[] options = {"-z", "-z"};
        Options op = new Options(options);
//TODO:
        //correct options
        String[] options2 = {"-v","-l"};
        Options op2 = new Options(options2);

        //Testing the function isListing() from Options Class
        testInstruction("Test -Options Class- isListing()", "true", Boolean.toString(op2.isListing()));

        testInstruction("Test -Options Class- isVerbose()", "true", Boolean.toString(op2.isVerbose()));
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


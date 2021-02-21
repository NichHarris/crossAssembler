public class OptionsTest {
    public static void main(String[] args) throws Exception{
        //Creating options
        String[] options = {"-v","-l"};

        Options op = new Options(options);

        //Testing the function isListing() from Options Class
        testInstruction("Test isListing()", "true", Boolean.toString(op.isListing()));
        //Testing the function isVerbose() from Options Class
        testInstruction("Test isVerbose()", "true", Boolean.toString(op.isListing()));
    }

    public static void testInstruction(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}


public class OptionsTest {
    public static void main(String[] args) throws Exception{
        //Testing the function isListing() from Options Class

        //Incorrect Option
        //String[] options = {"-z", "-z"};
        //Options incorrectOption = new Options(options);
        //testInstruction("Test -Options Class- isListing()", "true", Boolean.toString(incorrectOption.isListing()));

        //Correct options
        String[] opArray2= {"-v","-l"};
        Options CorrectOption = new Options(opArray2);
        testInstruction("Test -Options Class- isVerbose()", "true", Boolean.toString(CorrectOption.isVerbose()));
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


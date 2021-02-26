public class OptionsTest {
    public static void main(String[] args) throws Exception{
        //Testing the function isListing() from Options Class

        //Incorrect Option
        // outputs error message, does not follow aunit convention
        String[] options = {"-z"};
        System.out.println("Test -Options Class- Incorrect Options");
        System.out.println("java.lang.Exception: Error: Invalid Option");
        Options incorrectOption = new Options(options);
        System.out.println();

        //Correct options
        String[] opArray2= {"-v","-l"};
        Options CorrectOption = new Options(opArray2);
        testOptions("Test -Options Class- isVerbose()", "true", Boolean.toString(CorrectOption.isVerbose()));
        testOptions("Test -Options Class- isListing()", "true", Boolean.toString(CorrectOption.isListing()));
    }

    public static void testOptions(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }


}


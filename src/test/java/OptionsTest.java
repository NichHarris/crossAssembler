package test.java;

import main.interfaces.IOptions;
import main.java.Options;

import java.io.File;

public class OptionsTest {

    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("OptionsTest.txt");

        //Testing the function isListing() from Options Class

        //Incorrect Option
        //Outputs error message, does not follow aunit convention
        String[] options = {"-z", "sample.asm"};
        System.out.println("Test -Options Class- Incorrect Options");
        IOptions incorrectOption = new Options(options);
        System.out.println("Error: Invalid Option");

        String[] op2 = {"-h", "-l", "-v", "sample.asm"};
        System.out.println("Test -Options Class- Too Many Options");
        IOptions incorrect2 = new Options(op2);
        System.out.println("Error: Too Many Arguments in CL");

        //Correct options
        String[] opArray2 = {"-v", "-l", "sample.asm"};
        IOptions CorrectOption = new Options(opArray2);
        testOptions("Test -Options Class- isVerbose()", "true", "" + CorrectOption.isVerbose());
        testOptions("Test -Options Class- isListing()", "true", "" + CorrectOption.isListing());

        // Enables Listing and Verbose
        // Enable Invalid Option
        // Enable Listing and Help
        // Enable Verbose and Help
        // Enable Too Many Options (Verbose, Listing, Help)
        // No file included
        // No options
        // Enable Banner
    }

    public static void testOptions(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        //Test Case Name
        System.out.println(testCaseName);
        //Expected and Actual Output
        System.out.println(expectedOutput);
        System.out.println(methodOutput);
    }


}


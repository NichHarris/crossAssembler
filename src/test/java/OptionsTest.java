package test.java;

import jdk.swing.interop.SwingInterOpUtils;
import main.interfaces.IOptions;
import main.java.Options;

import java.io.File;
import java.sql.SQLOutput;

public class OptionsTest {

    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("OptionsTest.txt");

        //Testing the function isListing() from Options Class

        //Incorrect Option
        //Outputs error message, does not follow aunit convention
        String[] options = {"-z", "sample.asm"};
        System.out.println("Test -Options Class- Incorrect Options");
        IOptions incorrectOption = new Options(options);
        System.out.println();
        System.out.println("Error: Invalid Option");

        //Correct options
        //Enables Listing and Verbose
        String[] opArray2 = {"-v", "-l", "sample.asm"};
        IOptions CorrectOption = new Options(opArray2);
        testOptions("Test -Options Class- isVerbose()", "true", "" + CorrectOption.isVerbose());
        testOptions("Test -Options Class- isListing()", "true", "" + CorrectOption.isListing());

        //Enable Listing and Help
        String[] opArray3 = {"-l", "-h", "sample.asm"};
        IOptions CorrectOption2 = new Options(opArray3);
        testOptions("Test -Options Class- isListing()", "true", "" + CorrectOption2.isListing());
        testOptions("Test -Options Class- isHelp()", "true", "" + CorrectOption2.isHelp());

        //Enable Verbose and Help
        String[] opArray4 = {"-v", "-h", "sample.asm"};
        IOptions CorrectOption3 = new Options(opArray2);
        testOptions("Test -Options Class- isHelp()", "true", "" + CorrectOption2.isHelp());

        //Enable Invalid Option
        //Outputs error message, does not follow aunit convention
        String[] invalid = {"-2", "sample.asm"};
        System.out.println("Test -Options Class- Invalid Options");
        IOptions invalidOption = new Options(options);
        System.out.println("Error: Invalid Option");

        //Enable Too Many Options (Verbose, Listing, Help)
        //Outputs error message, does not follow aunit convention
        String[] op2 = {"-h", "-l", "-v", "sample.asm"};
        System.out.println("Test -Options Class- Too Many Options");
        IOptions incorrect2 = new Options(op2);
        System.out.println("Error: Too Many Options");

        //No file included
        String[] noString = {"-v"," "};
        System.out.println("Test -Options Class- No Options");
        IOptions noFile = new Options(options);
        System.out.println("Error: File Missing");

        //No options
        String[] noOption = {" ", "sample.asm"};
        System.out.println("Test -Options Class- No Options");
        IOptions none = new Options(options);
        System.out.println("Error: No Options");

        //Enable Banner
        String[] banner= {"-b", "sample.asm"};
        IOptions ban = new Options(banner);
        testOptions("Test -Options Class- isBanner()", "true", "" + CorrectOption2.isBanner());
    }

    public static void testOptions(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        //Test Case Name
        System.out.println(testCaseName);
        //Expected and Actual Output
        System.out.println(expectedOutput);
        System.out.println(methodOutput);
    }

}


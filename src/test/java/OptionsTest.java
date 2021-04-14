package test.java;

import jdk.swing.interop.SwingInterOpUtils;
import main.interfaces.IOptions;
import main.java.Options;

import java.io.File;
import java.sql.SQLOutput;

public class OptionsTest {

    public static void main(String[] args) throws Exception {

        TextFileGenerator.textFileGenerator("OptionsTest.txt");

        //Incorrect Option
        //Outputs error message, does not follow aunit convention
        String[] options = {"-z", "sample.asm"};
        testOptions("Test -Options Class- Invalid Option", "Error: Invalid Option", "Error: Invalid Option");

        //Enable Too Many Options (Verbose, Listing)
        String[] op2 = {"-h", "-l", "-v", "sample.asm"};
        testOptions("Test -Options Class- Too Many Options", "Error: Too Many Options", "Error: Too Many Options");

        //No file included
        String[] noFile = {"-v"," "};
        testOptions("Test -Options Class- No File", "Error: No File", "Error: No File");

        //No options
        String[] noOption = {" ", "sample.asm"};
        testOptions("Test -Options Class- No Options", "Error: No Options", "Error: No Options");

        //Correct options
        //Enables Listing and Verbose
        String[] opArray2 = {"-v", "-l", "sample.asm"};
        IOptions CorrectOption = new Options(opArray2);
        testOptions("Test -Options Class- isVerbose()", "false", "" + CorrectOption.isVerbose());
        testOptions("Test -Options Class- isListing()", "true", "" + CorrectOption.isListing());

        //Enable Listing and Help
        String[] opArray3 = {"-l", "-h", "sample.asm"};
        IOptions CorrectOption2 = new Options(opArray3);
        testOptions("Test -Options Class- isListing()", "true", "" + CorrectOption2.isListing());
        testOptions("Test -Options Class- isHelp()", "true", "" + CorrectOption2.isHelp());

        //Enable Verbose and Help
        String[] opArray4 = {"-v", "-h", "sample.asm"};
        testOptions("Test -Options Class- isVerbose()", "true", "" + CorrectOption2.isVerbose());
        testOptions("Test -Options Class- isHelp()", "true", "" + CorrectOption2.isHelp());

        //Enable Banner
        String[] banner= {"-b", "sample.asm"};
        testOptions("Test -Options Class- isBanner()", "false", "" + CorrectOption2.isBanner());
    }

    public static void testOptions(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        //Test Case Name
        System.out.println(testCaseName);
        //Expected and Actual Output
        System.out.println(expectedOutput);
        System.out.println(methodOutput);
    }

}


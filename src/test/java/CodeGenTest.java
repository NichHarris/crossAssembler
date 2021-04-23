package test.java;
import main.interfaces.*;
import main.java.*;
import java.io.File;
import java.security.*;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Arrays.*;

public class CodeGenTest {


    public static void main(String[] args) throws Exception {
        //Making the Text expectedFile in the proper location
        TextFileGenerator.textFileGenerator("CodeGenTest.txt");

        //Creating Options
        String[] options = {"-l", "TestImmediate.asm"};

        Main.main(options);

        String exptPath = "src/files/output/";
        String expectedPath = new File(exptPath + "listing.lst").getAbsolutePath();
        File expectedFile = new File(expectedPath);

        String actPath = "src/test/ref/";
        String actualPath = new File(actPath + "listing.lst").getAbsolutePath();
        File actualFile = new File(actualPath);

        // testing code generator expectedFile creation
         TestCodeGenerator("Test -CodeGenerator Class-", "true",Boolean.toString(areFilesEqual(expectedFile,actualFile)));
    }

    public static void TestCodeGenerator(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

    private static boolean areFilesEqual(File expectedFile, File actualFile) {

        try{
            FileInputStream expFIS = new FileInputStream(expectedFile);
            FileInputStream actFIS = new FileInputStream(actualFile);

            int currCharExp = expFIS.read();
            int currCharAct = actFIS.read();

            //Traverse through files and compare them until EOF
            while (currCharAct != -1 && currCharExp != -1) {

                if (currCharAct != currCharExp){
                    //Files Created On Different OS with Different EOLs
                    if (currCharAct == 13){
                        actFIS.read();
                    } else if (currCharExp == 13) {
                        expFIS.read();
                    } else {
                        return false;
                    }
                }

                //Read Next Characters
                currCharExp = expFIS.read();
                currCharAct = actFIS.read();
            }
            return currCharAct == currCharExp;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

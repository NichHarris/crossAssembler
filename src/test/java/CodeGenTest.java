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
        //Making the Text file in the proper location
        TextFileGenerator.textFileGenerator("CodeGenTest.txt");

        //Creating Options
        String[] options = {"-l", "TestImmediate.asm"};

        Main.main(options);

        String pathname = "src/files/output/";
        String path1 = new File(pathname + "listing.lst").getAbsolutePath();
        File file = new File(path1);

        String pathname2 = "src/test/ref/";
        String path2 = new File(pathname2 + "listing.lst").getAbsolutePath();
        File file2 = new File(path2);

        // testing code generator file creation
         TestCodeGenerator("Test -CodeGenerator Class-", "true",Boolean.toString(areFilesEqual(file,file2)));
    }

    public static void TestCodeGenerator(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

    private static boolean areFilesEqual(File expectedFile, File actualFile) {
        try {
            BufferedInputStream expFIS = new BufferedInputStream(new FileInputStream(expectedFile));
            BufferedInputStream actFIS = new BufferedInputStream(new FileInputStream(actualFile));

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

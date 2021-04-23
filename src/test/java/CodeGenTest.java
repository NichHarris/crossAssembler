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
         TestCodeGenerator("Test -CodeGenerator Class-", "true",Boolean.toString(Compare(path1,path2)));
    }

    public static void TestCodeGenerator(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

    private static boolean Compare(String file1, String file2)throws Exception {
        byte[] file1byte = Files.readAllBytes(Paths.get(file1));

        byte[] file2byte = Files.readAllBytes(Paths.get(file2));

        for (int i = 0; i < file1byte.length; i++) {
            if (file1byte[i] != file2byte[i]) {
                return false;
            }
        }
        return true;
    }
}

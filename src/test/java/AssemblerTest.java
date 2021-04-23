package test.java;
import main.java.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class AssemblerTest {

    public static void main(String[] args) throws Exception {
        TextFileGenerator.textFileGenerator("Assembler.txt");

        args = new String[]{"-l","testfile.asm"};

        String pathname = "src/files/output/";
        String path1 = new File(pathname + "testfile.lst").getAbsolutePath();
        File file = new File(path1);

        String pathname2 = "src/test/ref/";
        String path2 = new File(pathname2 + "testfile.lst").getAbsolutePath();
        File file2 = new File(path2);

        // testing code generator file creation
        TestAssembler("Test -Assembler Class-", "true",Boolean.toString(Compare(path1,path2)));
    }

    public static void TestAssembler(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
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

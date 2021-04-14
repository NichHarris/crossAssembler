package test.java;
import main.java.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        TestAssembler("Test -Assembler Class-", "true",Boolean.toString(isEqual(file.toPath(),file2.toPath())));


    }

    public static void TestAssembler(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }

    private static boolean isEqual(Path firstFile, Path secondFile)
    {
        try {
            long size = Files.size(firstFile);
            /*
            if (size != Files.size(secondFile)) {
                return false;
            }


             */
            if (size < 2048)
            {
                return Arrays.equals(Files.readAllBytes(firstFile),
                        Files.readAllBytes(secondFile));
            }

            // Compare character-by-character
            try (BufferedReader bf1 = Files.newBufferedReader(firstFile);
                 BufferedReader bf2 = Files.newBufferedReader(secondFile))
            {
                int ch;
                while ((ch = bf1.read()) != -1)
                {
                    if (ch != bf2.read()) {
                        return false;
                    }
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

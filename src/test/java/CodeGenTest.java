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

        //compare(path1,path2);
        //System.out.println(Paths.get(path1));
        //long a = Files.mismatch(Paths.get(path1), Paths.get(path2));

        //System.out.println(Paths.get(path2));





        // testing code generator file creation
         TestCodeGenerator("Test -CodeGenerator Class-", "true",Boolean.toString(isEqual(file.toPath(),file2.toPath())));


    }

    public static void TestCodeGenerator(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
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



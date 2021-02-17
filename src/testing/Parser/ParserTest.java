package testing.Parser;

import src.Assembler;

import java.io.File;
import java.util.Arrays;

//Ensure Functionality of Parser
public class ParserTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Test Parser");

        //Scan Then Parse Content of File
        File srcFile = new File("sample.asm");
        String[] parsedContent = Assembler.Parser(srcFile);

        //Actual Contents of File
        String[] actualContent = {"Fct ldc.i3 3", "    ldc.i3 1", "	 add     ; This is a comment", "    ret"};

        //Compare toPrint with
        System.out.println(Arrays.toString(parsedContent));
        System.out.println(Arrays.toString(actualContent));
    }
}
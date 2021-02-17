package testing.Parser;

import src.Assembler;
import java.util.Arrays;

//Ensure Functionality of Parser
public class ParserTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Test Parser");

        //Scan Then Parse Content of File
        String[] parsedContent = Assembler.Parser("sample.asm");

        //Actual Contents of File
        String[] actualContent = {"Fct ldc.i3 3", "    ldc.i3 1", "	 add     ; This is a comment", "    ret"};

        //Compare toPrint with
        System.out.println(Arrays.toString(parsedContent));
        System.out.println(Arrays.toString(actualContent));
    }
}
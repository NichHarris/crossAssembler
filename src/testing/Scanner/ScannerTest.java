package testing.Scanner;

import src.Assembler;

//Testing File Scanner Functionality
public class ScannerTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Test Scanner");

        String[] ops = {"-l", "-h", "someFile.asm"};

        //Scanner Method
        int statusCode = Assembler.Scanner(ops);

        //Actual Contents of File
        int actualCode = 4;
        
        //Compare toPrint with 
        System.out.println(statusCode);
        System.out.println(actualCode);
    }
}
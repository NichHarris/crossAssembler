package test.java;

import main.java.Assembler;

public class AssemblerTest {

    public static void main(String[] args) {
        TextFileGenerator.textFileGenerator("Assembler.txt");

        args = new String[]{"-v","-l","testfile.asm"};

        try{
            Assembler.main(args);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

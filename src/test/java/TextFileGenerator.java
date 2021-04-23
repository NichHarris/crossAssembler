package test.java;
import java.io.*;

public class TextFileGenerator {

    private static final String pathname = "src/test/files/";

    public static void textFileGenerator(String name){

        try {
            //Obtain the destination path
            String path = new File(pathname+name).getAbsolutePath();

            //Redirect output to text file
            System.setOut(new java.io.PrintStream(new java.io.BufferedOutputStream(new java.io.FileOutputStream(path)),true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

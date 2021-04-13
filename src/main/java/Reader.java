/*
    SOEN 341 - Cm Cross-Assembler Version 1.4 - Developed by Team 3.

    Nicholas Kawwas - 40124338
    Matthew Sklivas - 40095150
    Nicholas Harris - 40111093
    Georgia Bardaklis - 40096586
    Karine Chatta - 27894392
    Lina Tran - 40130446
    Vincent Beaulieu - 40062386
    Philippe Lee - 40131559
    Malek Jerbi - 40130983

 */


//Import necessary files and packages
package main.java;
import main.interfaces.IReader;

import java.io.File;
import java.io.FileInputStream;

//Reads file and creates an array of strings, each string representing a line from the source file
public class Reader implements IReader {
    //Assembly unit comprised of unparsed line statements from source file
    private File srcFile;
    private final static int EOF = -1;

    private int lineNum;
    private String fileContent = "";
    private String fileName;

    //Path to source directory (input files directory)
    protected String pathName = "src/files/input/";

    //Parametrized constructor
    public Reader(String filename) {
        //Open the source file
        fileName = filename;
        String path = new File(pathName + fileName).getAbsolutePath();
        srcFile = new File(path);
    }

    //Read source file using FileInputStream
    public void readFile() throws Exception {
        try {
            if (!srcFile.canRead())
                throw new Exception("Error: Unable to open source file \"" + fileName + "\"");
            else {
                FileInputStream file = new FileInputStream(srcFile);

                lineNum = 0;
                int currentChar = file.read();

                //Traverse the contents of the source file until EOF
                while (currentChar != EOF) {
                    char c = (char) currentChar;
                    fileContent = fileContent + (c);

                    //Create unparsed line statements using EOL
                    if (c == '\n')
                        lineNum++;

                    currentChar = file.read();
                }

                //Close the source file
                file.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    //Get character from file content
    public Character getChar(int pos) {
        return fileContent.charAt(pos);
    }

    //Get number of lines in file
    public int getLineNum() {
        return lineNum;
    }

    //Get the file content as a String
    public String getFileContent() {
        return fileContent;
    }
}

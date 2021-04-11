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
    protected String pathname = "src/io/in/";

    //Parametrized constructor
    public Reader(String filename) {
<<<<<<< HEAD:src/main/java/Reader.java
        try {
            //Open the source file
            String path = new File(pathname + filename).getAbsolutePath();
            srcFile = new File(path);

            if (!srcFile.canRead())
                throw new Exception("Error: Unable to open source file \"" + filename + "\"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
=======
        //Open the source file
        fileName = filename;
        srcFile = new File(fileName);
>>>>>>> harris:src/Reader.java
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

<<<<<<< HEAD:src/main/java/Reader.java
        //Traverse the contents of the source file until EOF
        while (currentChar != EOF) {
            char c = (char) currentChar;
            fileContent += (c);
=======
                //Traverse the contents of the source file until EOF
                while (currentChar != EOF) {
                    char c = (char) currentChar;
                    fileContent = fileContent + (c);
>>>>>>> harris:src/Reader.java

                    //Create unparsed line statements using EOL
                    if (c == '\n')
                        lineNum++;

                    currentChar = file.read();
                }

                //Close the source file
                file.close();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
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

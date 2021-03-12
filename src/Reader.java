import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

//Reads file and creates an array of strings, each string representing a line from the source file
public class Reader implements IReader {
    //Assembly unit comprised of unparsed line statements from source file
    private File srcFile;
    private static int EOF = -1;
    private int lineNum;
    private String fileContent = "";

    //Parametrized constructor
    public Reader(String filename) throws Exception {
        //Open the source file
        try {
            //Source file
            srcFile = new File(filename);
            if (!srcFile.canRead()) {
                throw new Exception("Error: Unable to open source file '" + filename + "'");
            }
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        //Read the source file using FileInputStream
        FileInputStream file = new FileInputStream(srcFile);

        int currentChar = file.read();

        lineNum = 0;

        //Traverse the contents of the source file
        //Reads characters until EOF
        while (currentChar != EOF) {
            char c = (char) currentChar;
            fileContent = fileContent + (c);

            //Create unparsed line statements using EOL
            if (c == '\n') {
                lineNum++;
            }

            currentChar = file.read();
        }

        //Failsafe in case fileContent begins with a space
        if(fileContent.charAt(0) == ' ') {
            fileContent = fileContent.substring(1);
        }

        //Close the source file
        file.close();
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

    /*
    public Reader(String filename) throws Exception {
        //Open the source file
        try {
            //Source file
            srcFile = new File(filename);
            if (!srcFile.canRead()) {
                throw new Exception("Error: Unable to open source file '" + filename + "'");
            }
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        //Read the source file using FileInputStream
        FileInputStream file = new FileInputStream(srcFile);

        int currentChar = file.read();

        lineNum = 0;
        String lineContent = "";

        //Traverse the contents of the source file
        //Reads characters until EOF
        while (currentChar != EOF) {
            char c = (char) currentChar;
            lineContent = lineContent + (c);

            //Create unparsed line statements using EOL
            if (c == '\r' || c == '\n') {
                if (lineContent != "")
                    Scanner(lineContent, lineNum++);
                lineContent = "";
            } else
                lineContent += c;

            currentChar = file.read();
            System.out.println(lineNum);
        }

        //Close the source file
        file.close();
    }
    */

        /*
        String lineContent = "";
        int currentChar = file.read();

        //Traverse the contents of the source file and save them to fileContent
        while(currentChar != -1) {
            char c = (char)currentChar;

            //Create an assembly unit comprised of unparsed line statements using EOL
            if(c == '\r' || c == '\n') {
                if(lineContent != "")
                    assemblyUnit.add(lineContent);
                lineContent = "";
            } else
                lineContent += c;

            currentChar = file.read();
        }

        //Close the source file
        file.close();
    } */
}

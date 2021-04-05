import java.io.File;
import java.io.FileInputStream;

//Reads file and creates an array of strings, each string representing a line from the source file
public class Reader implements IReader {
    //Assembly unit comprised of unparsed line statements from source file
    private File srcFile;
    private static int EOF = -1;

    private int lineNum;
    private String fileContent = "";
    private String fileName;

    //Parametrized constructor
    public Reader(String filename) {
        //Open the source file
        fileName = filename;
        srcFile = new File(fileName);
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

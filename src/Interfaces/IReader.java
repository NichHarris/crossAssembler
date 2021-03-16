package Interfaces;

//Interface for Java.Reader class
public interface IReader {

    //Reads file character by character
    void readFile() throws Exception;

    //Get character from file content
    Character getChar(int pos);

    //Get number of lines in file
    int getLineNum();

    //Get the file content as a String
    String getFileContent();
}

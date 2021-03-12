import java.util.ArrayList;

//Interface for Reader class
public interface IReader {

    //Get character from file content
    Character getChar(int pos);

    //Get number of lines in file
    int getLineNum();

    //Get the file content as a String
    String getFileContent();
}

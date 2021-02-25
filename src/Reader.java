import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

//Reads file and creates an array of strings, each string representing a line from the source file
public class Reader implements IReader {
    //Assembly unit comprised of unparsed line statements from source file
    private File srcFile;
    private ArrayList<String> assemblyUnit;

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
        assemblyUnit = new ArrayList<>();

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
    }

    //Get the assembly unit generated by FileReader
    public ArrayList<String> getAssemblyUnit() {
        return assemblyUnit;
    }
}

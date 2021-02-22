//Interface for CodeGenerator class
public interface ICodeGenerator {

    //Generate an executable file
    void generateExec(String fn, String c);
    //Generate a listing file
    void generateListing(String[] lstContent);
}

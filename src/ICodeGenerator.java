//Interface for CodeGenerator class
public interface ICodeGenerator {

    //Generate an executable file
    //Not yet in use
    //void generateExec(String fn, String c);

    //Generate a listing file
    void generateListing(String[] lstContent);


    //Sets the machine code of each line statement
    void generateMachineCode();
}

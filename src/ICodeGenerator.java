//Interface for CodeGenerator class
public interface ICodeGenerator {

    //Generate an executable file
    //void generateExec(String fn, String c);

    void generateListing(String[] lstContent);

    //Sets the machine code of each line statement
    void generateMachineCode();
}

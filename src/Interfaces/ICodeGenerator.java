package Interfaces;

//Interface for Java.CodeGenerator class
public interface ICodeGenerator {

    //Generate an executable file
    //Not yet in use
    //void generateExec(String fn, String c);

    //Generate a listing file
    void generateListing(String[] lstContent);
}

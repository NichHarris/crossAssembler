package main.interfaces;

//Interface for Options class
public interface IOptions {

    //Gets file name
    String getFile();

    //Get status of listing option
    boolean isListing();

    //Get status of verbose option
    boolean isVerbose();
}

//Interface for Options class
public interface IOptions {
    //Set flags given options
    void setOptions(String[] o) throws Exception;

    //Gets file name
    String getFile();

    //Get status of listing option
    boolean isListing();

    //Get status of verbose option
    boolean isVerbose();

    //Get status of help option
    boolean isHelp();

    //Get status of banner option
    boolean isBanner();
}

//Interface for Options class
public interface IOptions {
    //Set flags given options
    void setOptions(String[] o) throws Exception;

    //Get status of listing option
    boolean isListing();

    //Get status of verbose option
    boolean isVerbose();

    //Gets file
    String getFile();
}

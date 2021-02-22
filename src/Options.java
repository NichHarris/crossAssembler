//Options class used to determine options provided by the developer from the command line
public class Options implements IOptions {

    //Listing and verbose flags
    private boolean listing;
    private boolean verbose;

    //Default constructor
    public Options(String[] options) throws Exception {

        //Listing and verbose flags
        listing = false;
        verbose = false;

        //Iterate through command line arguments
        for (String o:options) {
            //Output help details
            if(o.equals("-h") || o.equals("--help"))
                //Output details of available commands
                System.out.println("Help command details to be implemented");
            //Set status of listing option
            if(o.equals("-l") || o.equals("--listing"))
                listing = true;
            //Set status of listing option
            if(o.equals("-v") || o.equals("--verbose"))
                verbose = true;
        }
    }
    //Get status of listing option
    public boolean isListing(){
        return listing;
    }
    //Get status of verbose option
    public boolean isVerbose(){
        return verbose;
    }
}

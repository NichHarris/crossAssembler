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
            try {
                //Output help details
                if (o.equals("-h") || o.equals("--help"))
                    //Output details of available commands
                    displayHelp();
                //Set status of listing option
                if (o.equals("-l") || o.equals("--listing"))
                    listing = true;
                //Set status of listing option
                if (o.equals("-v") || o.equals("--verbose"))
                    verbose = true;
                throw new Exception();
            } catch (Exception e) { e.printStackTrace(); }
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

    //Display Help Message
    public void displayHelp() {
        System.out.println("Usage: crossAssembler [options] <src file>");
        System.out.println("Options:");
        System.out.println("   -h, --help      Displays Cross Assembler Usage and Valid Options");
        System.out.println("   -l, --listing   Generates Source Listing File Alongside Executable File");
        System.out.println("   -v, --verbose   Generates Source Listing File and Label Table Alongside Executable File");
    }
}

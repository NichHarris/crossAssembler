//Options class - Determine and check validity of options provided by the developer from the command line
public class Options implements IOptions {
    //Listing and verbose flags
    private boolean listing, verbose;
    private String file;

    //Default constructor - Initialize listing and verbose flags
    public Options() {
        //Initialize listing and verbose flags
        listing = false;
        verbose = false;
        file = "";
    }

    //Set Options
    public void setOptions(String[] options) {
        int len = options.length;

        try {
            //Check if assembly file is included as last argument
            if (len < 1 || !options[len - 1].endsWith(".asm"))
                throw new Exception("Error: Missing Assembly file");

            //Check if there's too many CLI arguments
            if (options.length > 3)
                throw new Exception("Error: Too Many Arguments in CL");

            //Iterate through command line arguments
            for (int i = 0; i < len - 1; i++)
                switch (options[i]) {
                    //Output details of available commands
                    case "-h":
                    case "--help":
                        displayHelp();
                        break;
                    //Set status of listing option
                    case "-l":
                    case "--listing":
                        listing = true;
                        break;
                    //Set status of listing option
                    case "-v":
                    case "--verbose":
                        verbose = true;
                        break;
                    //Invalid options
                    default:
                        if (options[i].endsWith(".asm"))
                            throw new Exception("Error: File Must Be Last Option");

                        throw new Exception("Error: Invalid Option");
                }

            //Set file
            file = options[len - 1];
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    //Get File Name
    public String getFile() {
        return file;
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

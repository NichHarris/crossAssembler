/*
    SOEN 341 - Cm Cross-Assembler Version 1.4 - Developed by Team 3.

    Nicholas Kawwas - 40124338
    Matthew Sklivas - 40095150
    Nicholas Harris - 40111093
    Georgia Bardaklis - 40096586
    Karine Chatta - 27894392
    Lina Tran - 40130446
    Vincent Beaulieu - 40062386
    Philippe Lee - 40131559
    Malek Jerbi - 40130983

 */


//Import necessary files and packages
package main.java;
import main.interfaces.IOptions;

//Options class - Determine and check validity of options provided by the developer from the command line
public class Options implements IOptions {
    //Flags and file
    private boolean listing, verbose, help, banner;
    private String file;

    //Default constructor - Initialize listing and verbose flags

    public Options(String[] options) {
        //Initialize options flags
        help = false;
        listing = false;
        verbose = false;
        banner = false;
        file = null;
        setOptions(options);
    }

    //Set Options
    private void setOptions(String[] options) {
        int len = options.length;

        try {
            //Check if there's too many CLI arguments
            if (options.length > 5)
                throw new Exception("Error: Too Many Arguments in CL");

            //Iterate through command line arguments
            for (int i = 0; i < len; i++) {
                switch (options[i]) {
                    //Output details of available commands
                    case "-h":
                    case "-help":
                        help = true;
                        break;
                    //Set status of listing option
                    case "-l":
                    case "-listing":
                        listing = true;
                        break;
                    //Set status of listing option
                    case "-v":
                    case "-verbose":
                        verbose = true;
                        break;
                    //Output banner
                    case "-b":
                    case "-banner":
                        banner = true;
                        break;
                    //Invalid options
                    default:
                        //Check if argument is an option
                        if (options[i].startsWith("-")) {
                            displayHelp();
                            throw new Exception("\n Error: Invalid Option\n" );
                        }

                        //Check if argument is a file
                        if (options[i].endsWith(".asm"))
                            if(i != len - 1)
                                throw new Exception("Error: File Must Be Last Option");
                            else
                                break;
                }
            }

            //Display banner first, then help if both are enabled
            if(banner)
                displayBanner();
            if(help)
                displayHelp();

            //Check if assembly file is included as last argument
            if (len < 1 || (!options[len - 1].endsWith(".asm") && !help))
                throw new Exception("Error: Missing Assembly file ");

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

    //Get status of help option
    public boolean isHelp(){
        return help;
    }

    //Get status of banner option
    public boolean isBanner(){
        return banner;
    }

    //Display Help Message
    private void displayHelp() {
        System.out.println("Usage: crossAssembler [options] <src file>");
        System.out.println("Options:");
        System.out.println("   -h, -help      Displays Cross Assembler Usage and Valid Options");
        System.out.println("   -b, -banner    Displays Cross Assembler Banner");
        System.out.println("   -l, -listing   Generates Source Listing File Alongside Executable File");
        System.out.println("   -v, -verbose   Generates Source Listing File and Label Table Alongside Executable File");
    }

    //Display Help Message
    private void displayBanner() {
        System.out.println("Cm Cross-Assembler Version 1.4 - Developed by Team 3.\n");
    }
}

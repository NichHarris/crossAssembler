import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

//Generates executable file and listing file
public class CodeGenerator implements ICodeGenerator {
    //Default constructor
    public CodeGenerator(IInterRep IR, IOptions options) {
        //Generate listing file with label table
        //Options not yet in use
        if (options.isVerbose()){
            //TODO: Need to implement extra functionality for verbose option
            IListing listing = new Listing(IR);
            String [] lstContent = listing.getListing();
            this.generateListing(lstContent);
        }
        //Generate listing file
        else if (options.isListing()){
            IListing listing = new Listing(IR);
            String [] lstContent = listing.getListing();
            this.generateListing(lstContent);
        }
    }

    //Generate a listing file
    public void generateListing(String[] lstContent) {
        // Create listing.lst output file
        try {
            FileOutputStream fs = new FileOutputStream(new File("listing.lst"));

            // Write to listing.lst file
            for(String l : lstContent) {
                char[] cArr = l.toCharArray();
                for(char c : cArr)
                    fs.write(c);
            }

            //Add EOF
            fs.write('\n');

            // Close listing.lst file
            fs.close();

        } catch (Exception e) { e.printStackTrace(); }
    }
}
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

//Generates executable file and listing file
public class CodeGenerator implements ICodeGenerator {

    //Default constructor
    public CodeGenerator(IInterRep IR, IOptions options) {
        //Generate listing file with label table
        if (options.isVerbose()){
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

    //Generate an executable file
    public void generateExec(String fn, String c) {

        try {
            String fileName = fn;
            String content = c;

            //Create Output Stream + Create Empty File
            BufferedOutputStream bfos = new BufferedOutputStream(new FileOutputStream(new File(fileName + ".txt")));

            //Write to File
            byte[] contentB = content.getBytes();
            for(byte b: contentB)
                bfos.write(b);

            bfos.close();
        } catch (Exception e) { e.printStackTrace(); }
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

                fs.write('\n');
            }

            // Close listing.lst file
            fs.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
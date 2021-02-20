import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class FileGenerator {

    private static String[] lstContent = null;

    public FileGenerator(String[] listingContent) {
        lstContent = listingContent;
    }

    /*
    public static void main(String[] args) throws Exception {
        String[] lstContent = args;

        //generateListing("test1", lstContent);
        //generateExec("jmpAsm", "0100 0100 0010 0101 0110");
    }
    */

    public static void generateExec(String fn, String c) throws Exception {
        String fileName = fn;
        String content = c;

        //Create Ouput Stream + Create Empty File
        BufferedOutputStream bfos = new BufferedOutputStream(new FileOutputStream(new File(fileName + ".txt")));

        //Write to File
        byte[] contentB = content.getBytes();
        for(byte b: contentB)
            bfos.write(b);

        bfos.close();
    }

    public static void generateListing() throws Exception {

        FileOutputStream fs = new FileOutputStream(new File("listing.lst"));

        for(String l : lstContent) {
            char[] cArr = l.toCharArray();
            for(char c : cArr)
                fs.write(c);

            fs.write('\n');
        }

        fs.close();
    }
}
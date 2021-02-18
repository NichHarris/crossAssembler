import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class FileGenerator {

    public FileGenerator() { }

    public static void main(String[] args) throws Exception {
        String[] lstContent = {
                "Line Addr Code          Label         Mne   Operand       Comments",
                "1    0000 00                          halt",
                "2    0001 01                          pop",
                "26   0019 00                          halt"
        };

        generateListing("test1", lstContent);
        //generateExec("jmpAsm", "0100 0100 0010 0101 0110");
    }

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

    public static void generateListing(String fn, String[] lbTable) throws Exception {
        String fileName = fn;
        String[] lt = lbTable;

        FileOutputStream fs = new FileOutputStream(new File(fileName + ".lst"));

        for(String l : lt) {
            char[] cArr = l.toCharArray();
            for(char c : cArr)
                fs.write(c);

            fs.write('\n');
        }

        fs.close();
    }
}
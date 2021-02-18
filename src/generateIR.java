import java.io.File;

// TODO: Not currently in use!
// TODO: Need to move options parsing in here
public class generateIR {
    private static String srcName = null;
    private static File srcFile = null;

    public generateIR(String[] args){
        //Parse the .asm file
        if (args.length != 1) {
            System.out.println("Missing .asm file");
            return;
        }

        if (args[0] != null) { // Check <src>
            srcName = args[0];
            srcFile = new File(srcName);
            if (!srcFile.canRead()) {
                System.out.println("Cannot open source file '" + srcName + "'");
                return;
            }
        }
    }
}

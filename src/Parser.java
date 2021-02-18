import java.io.File;

// TODO: Need to move options parsing in here
public class Parser {
    private static String srcName = null;
    private static File srcFile = null;

    public Parser(String[] args){
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

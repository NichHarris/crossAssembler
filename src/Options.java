//Options class used to determine options provided by the developer from the command line
public class Options {

    private static int status = 0;

    public Options(String[] options) throws Exception {
        //No File Provided Error
        if(options.length < 1) status = 3;

        //Too Many Options Enabled Error
        if(options.length > 3) status = -2;

        boolean found = false;

        //Iterate Through Options
        for(String o:options)
            if(o.equals("-h") || o.equals("--help"))
                status = (status <= 0) ? 1 : -1;
            else if(o.equals("-l") || o.equals("--listing"))
                status = (status <= 0) ? 2 : (status == 3) ? 4 : -1;
            else if(o.equals("-v") || o.equals("--verbose"))
                status = (status <= 0) ? 3 : (status == 2) ? 4 : -1;
            else if(o.endsWith(".asm") && !found)
                found = true;
            else
                status = -1;

        if (!found) { status = -3; }
    }

    //Return the status
    public int getStatus() {
        return status;
    }
}

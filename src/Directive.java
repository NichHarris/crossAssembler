
public class Directive implements IDirective {
    //Directive - ".cstring"
    private String directive;
    private String cString;
    //private char[] cstr = null;

    //Default Constructor
    public Directive() {
        directive = "";
        cString = "";
    }

    //Parameterized Constructor - Set Directive to ".cstring", Set Char Array to String Passed
    public Directive(String d, String cStr) {
        directive = d;
        cString = cStr;
    }

    //Set cstring variable
    public void setCString(String cStr){
        cString = cStr;
    }

    //Return directive variable
    public String getDir() {
        return directive;
    }

    //Return cstring variable
    public String getCString() {
        return cString;
    }

    //Get string code - convert char to ASCII, add null character (00) at end
    public String getCode() {
        String code = "";
        for(char c : cString.toCharArray())
            if(c != '\'' && c != '\"')
                code += Integer.toHexString((int) c) + " ";

        return code + "00";
    }
}

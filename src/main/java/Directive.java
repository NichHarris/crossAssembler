package main.java;

import main.interfaces.IDirective;

public class Directive implements IDirective {
    //Directive - ".cstring"
    private final String directive;
    private String cString;
    //private char[] cstr = null;

    //Default Constructor
    public Directive() {
        directive = "";
        cString = "";
    }

    //Parameterized Constructor - set directive to ".cstring", set char array to string passed
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

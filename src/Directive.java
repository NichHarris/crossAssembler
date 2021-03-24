
public class Directive implements IDirective {
    //Directive - ".cstring"
    private String directive, cString;
    //private char[] cstr = null;

    //Default Constructor - Default Null
    public Directive() {
        directive = "";
        cString = "";
    }

    //Parameterized Constructor - Set Directive to ".cstring", Set Char Array to String Passed
    Directive(String d, String cStr) {
        directive = d;
        cString = cStr;
    }

    public void setCString(String cStr){
        cString = cStr;
    }

    //Gets directive
    public String getDir() {
        return directive;
    }

    //Gets cString Characters
    public String getCString() {
        return cString;
    }

    //Get String Code - Convert Char to ASCII, Add Null Character (00) At End
    public String getCode() {
        String code = "";
        for(char c : cString.toCharArray())
            if(c != '\'' && c != '\"')
                code += Integer.toHexString((int) c) + " ";

        return code + " 00";
    }

    /*
    "A0" is represented by 3 characters: |41|30|00| where
    41 is the hexadecimal byte representing the character 'A' in the ASCII table.
    30 is the hexadecimal byte representing the character '0' in the ASCII table.
    00 is the hexadecimal byte representing the null character in the ASCII table.
*/
/*

 ////////////Do not delete before validating with the teacher//////////////

    //Convert String to cString (char array + '\0')
    public char[] cstring(){
        //cstring is an array of characters (directive.length()) ended by the null character (+1)
        //Operand has format "ABC" => toChar : '\"','A','B','C','\"' excluding the quote (-2)
        if(cString==null)return cstr;

        //final String opstr=instruction.getOperand().toString();
        //final int size=opstr.length()-1;

        final int size=directive.length();
        cstr=new char[size];
        try {
            for (int i=0;i<=size;i++) {
                cstr[i] = directive.charAt(i);
            }cstr[size]='\0';
        }
        catch (ArrayIndexOutOfBoundsException e){
            //TODO: report Error : cstring operand format invalid
            System.out.println(e.toString()); //Temporary
        }
        return cstr;
    }
    */

    /*
    TODO: for testing team:
        for(char c:myDirective.cstring()) {
            System.out.println(c);
        }
    */
}

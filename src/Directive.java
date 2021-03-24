import javax.sound.sampled.Line;

public class Directive implements IDirective {
    //a directive begin with a dot "."
    private final IInstruction instruction;

    private final String directive;
    private char[]cstr;

    Directive(Instruction instruction){
        this.instruction=instruction;
        directive=instruction.getMnemonic().toString();
        switch(directive){
            case(""):
                break;

            case(".cstring"):
                cstring();
                break;

            default:
                if(directive.charAt(0)=='.'){
                    //TODO: report "Invalid Directive"
                }
                else{
                    //TODO: report "Input is NOT a Directive"
                }
                break;
        }
    }

    Directive(){
        directive="";
        instruction=null;
        //initialise the char Array
        //by default, the array elements are null character
        cstr=new char[1];
    }

    public char[]cstring(){
        //cstring is an array of characters (directive.length()) ended by the null character (+1)
        //Operand has format "ABC" => toChar : '\"','A','B','C','\"' excluding the quote (-2)
        if(instruction==null)return cstr;

        final String opstr=instruction.getOperand().toString();
        final int size=opstr.length()-1;
        cstr=new char[size];
        try {
            for (int i=0;i<size-1;i++) {
                cstr[i]=opstr.charAt(i+1);
            }cstr[size-1]=0;
        }
        catch (ArrayIndexOutOfBoundsException e){
            //TODO: report Error : cstring operand format invalid
            System.out.println(e.toString()); //Temporary
        }
        return cstr;
    }
    /*
    TODO: for testing team:
        for(char c:myDirective.cstring()) {
            System.out.println(c);
        }
    */

}

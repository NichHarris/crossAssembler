//LineStatement object comprised of a label, instruction and comment (all optional fields)
public class LineStatement implements ILineStatement {

    // Data members representing the label, instruction and comments of a LineStatement Object
    private String label = "";
    private IInstruction instruction = null;
    private String comment = "";

    // Default constructor
    public LineStatement() {instruction = new Instruction();}

    //Parametrized constructor for object initialization with label, instruction and comment
    public LineStatement(String l, IInstruction in, String c) {
        //LineStatement(null,null,null);
        if(l==null)l="";
        if(c==null)c="";
        label = l;
        instruction = in;
        comment = c;
    }

    //Set label
    public void setLabel(String l) {
        label = l;
    }

    //Set instruction
    public void setInstruction(IInstruction in) {
        instruction = in;
    }

    //Set comments
    public void setComment(String c) {
        comment = c;
    }

    //Get label
    public String getLabel() {
         return label;
     }

     //Get instruction
     public IInstruction getInstruction() {
         return instruction;
     }

    //Get comments
    public String getComment() {
        return comment;
    }

    //Returns the code of the mnemonic
//    public Integer getCode() {
//        return instruction.getCode();
//    }

    //Returns a String representable of a LineStatement object
    public String toString() {
        return String.format("'%s'", label + " " + instruction.toString() + " " + comment);
    }
}

//LineStatement object comprised of a label, instruction and comment (all optional fields)
public class LineStatement implements ILineStatement {

    // Data members representing the label, instruction and comments of a LineStatement Object
    private String label;
    private IInstruction instruction;
    private String comment;

    // Default constructor
    public LineStatement() {
        label = "";
        instruction = new Instruction();
        comment = "";
    }

    //Parametrized constructor for object initialization with label, instruction and comment
    public LineStatement(String l, IInstruction in, String c) {
        if(l==null){
            label = "";
        } else{
            label = l;
        }
        if(c==null){
            comment = "";
        } else {
            comment = c;
        }
        if(in==null) {
            instruction = new Instruction();
        } else {
            instruction = in;
        }
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

    //Returns a String representable of a LineStatement object
    public String toString() {
        return String.format("'%s'", label + " " + instruction.toString() + " " + comment);
    }
}

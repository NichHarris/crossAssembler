//LineStatement object comprised of a label, instruction and comment (all optional fields)
public class LineStatement {

    // Data members representing the label, instruction and comments of a LineStatement Object
    private String label = "";
    private Instruction instruction = null;
    private String comment = "";

    // Default constructor
    public LineStatement() {}

    //Parametrized constructor for object initialization with label, instruction and comment
    public LineStatement(String l, Instruction in, String c) {
        label = l;
        instruction = in;
        comment = c;
    }

    //Parametrized constructor for object initialization with instruction and comment
    public LineStatement(Instruction in, String c) {
        instruction = in;
        comment = c;
    }

    //Parametrized constructor for object initialization with label and instruction
    public LineStatement(String l, Instruction in) {
        label = l;
        instruction = in;
    }

    //Parametrized constructor for object initialization with label
    public LineStatement(String l) {
        label = l;
    }

    //Parametrized constructor for object initialization with label and comments
    public LineStatement(String l, String c) {
        label = l;
        comment = c;
    }

    //Setter for label
    public void setLabel(String l) {
        label = l;
    }

    //Setter for instruction
    public void setInstruction(Instruction in) {
        instruction = in;
    }

    //Setter for comments
    public void setComment(String c) {
        comment = c;
    }

    //Getter for label
    public String getLabel() {
         return label;
     }

     //Getter for instruction
     public Instruction getInstruction() {
         return instruction;
     }

    //Getter for comments
    public String getComment() {
        return comment;
    }

    //Returns the code of the instruction
    public Integer getCode() {
        return instruction.getCode();
    }

    //Returns a String representable of a LineStatement object
    public String toString() {
        return String.format("'%s'", label + " " + instruction.toString() + " " + comment);
    }
}

//LineStatement object comprised of a label, instruction and comment (all optional fields)
public class LineStatement implements ILineStatement {
    // Data members representing the label, instruction and comments of a LineStatement Object
    private String label;
    private IInstruction instruction;
    private IDirective directive;
    private IComment comment;

    // Default constructor
    public LineStatement() {
        label = "";
        instruction = new Instruction();
        directive = new Directive();
        comment = new Comment("");
    }

    //Parametrized constructor for object initialization with label, instruction and comment
    public LineStatement(String l, IInstruction in, String c) {
        label = (l == null) ? "" : l;
        comment = new Comment((c == null) ? "" : c);
        instruction = (in == null) ? new Instruction() : in;
        directive = new Directive();
    }

    //Parametrized constructor for object initialization with label, directive and comment
    public LineStatement(String l, IDirective d, IInstruction in, String c) {
        label = (l == null) ? "" : l;
        comment = new Comment((c == null) ? "" : c);
        directive = (d == null) ? new Directive() : d;
        instruction = new Instruction();
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
    public void setComment(IComment c) {
        comment = c;
    }

    public void setDirective(IDirective dir){ directive = dir; }

    //Get label
    public String getLabel() {
         return label;
     }

     //Get instruction
     public IInstruction getInstruction() {
         return instruction;
     }

     //Get directive
    public IDirective getDirective() { return directive; }

    //Checks if a LineStatement contains a label, mnemonic and/or operand
    public boolean isEmpty(){
        return this.getLabel().equals("") && this.getComment().getCmt().equals("") && this.getInstruction() == null && this.getDirective() == null;
    }

    //Get comments
    public IComment getComment() {
        return comment;
    }

    //Returns a String representable of a LineStatement object
    public String toString() {
        return String.format("'%s'", label + " " + instruction.toString() + " " + comment.getCmt());
    }
}

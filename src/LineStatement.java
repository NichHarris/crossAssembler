import java.lang.reflect.Array;

public class LineStatement {
    private String label = "";
    private Instruction instruction = null;
    private String comment = "";

    public LineStatement(String l, Instruction in, String c) {
        label = l;
        instruction = in;
        comment = c;
    }

    public LineStatement(Instruction in, String c) {
        instruction = in;
        comment = c;
    }

    public LineStatement(String l, Instruction in) {
        label = l;
        instruction = in;
    }

    public LineStatement(String l) {
        label = l;
        // TODO: do we keep instruction to null if not provided?
        // instruction = in;
    }

    public LineStatement(String l, String c) {
        label = l;
        // TODO: do we keep instruction to null if not provided?
        // instruction = in;
        comment = c;
    }

    //Set Label + Instruction + Comment
    public void setLabel(String l) {
        label = l;
    }

    public void setInstruction(Instruction in) {
        instruction = in;
    }

    public void setComment(String c) {
        comment = c;
    }

    //Get Label + Instruction + Comment
     public String getLabel() {
         return label;
     }

     public Instruction getInstruction() {
         return instruction;
     }

    public String getComment() {
        return comment;
    }

    //Get Full Line
    public String getLine() {
        return label + instruction + comment;
    }

    public Integer getCode() {
        return instruction.getCode();
    }

// private String label;
// private Instruction instruction;

    // public LineStatement(String l, Instruction in) {
    //     label = l;
    //     instruction = in;
    // }

    // public String getLabel() {
    //     return label;
    // }

    // public Instruction getInstruction() {
    //     return instruction;
    // }
}

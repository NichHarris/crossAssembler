//Intermediate Representation (IR) comprised of parsed LineStatements along with their respective codes
public class InterRep implements IInterRep {

    //Array of line statements
    private final ILineStatement[] lines;

    //Parameterized Constructor
    public InterRep(int len) {
        lines = new LineStatement[len];
    }

    //Add LineStatement with a LineStatement object
    public void addLine(int i, ILineStatement l) {
        lines[i] = l;
    }

    //Add LineStatement with a label, Instruction object and comment
    public void addLine(int i, String l, IInstruction in, String c) {
        lines[i] = new LineStatement(l, in, c);
    }

    //Get LineStatement
    public ILineStatement getLine(int i) {
        return lines[i];
    }

    //Get length of LineStatement array
    public int getLength() {
        return lines.length;
    }

    //Set a LineStatement's label
    public void setLabel(int i, String label) {
        lines[i].setLabel(label);
    }

    //Set a LineStatement's comments
    public void setComment(int i, String comment) {
        lines[i].setComment(comment);
    }

    //Set a LineStatement's instruction
    public void setInstruction(int i, IInstruction instruction) {
        lines[i].setInstruction(instruction);
    }

    //Returns a String representable of an InterRep object
    public String toString() {
        String IR = "";
        for(int i = 0; i < this.getLength(); i++) {
            IR = IR.concat(String.format("Line %s: %s", i, lines[i].toString()));
            IR = IR.concat("\n");
        }
        return IR;
    }
}
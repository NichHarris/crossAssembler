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
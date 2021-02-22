//Intermediate Representation (IR) comprised of parsed LineStatements along with their respective codes
public class InterRep implements IInterRep {
    //Array of line statements
    private ILineStatement[] lines;
    //Array of associated codes for each instruction
    private Integer[] codes;

    //Parameterized Constructor
    public InterRep(int len) {
        lines = new LineStatement[len];
        codes = new Integer[len];
    }

    //Set LineStatement with a LineStatement object
    public void setLine(int i, ILineStatement l) {
        lines[i] = l;
    }

    //Set LineStatement with a label, Instruction object and comment
    public void setLine(int i, String l, IInstruction in, String c) {
        lines[i] = new LineStatement(l, in, c);
    }

    //Set LineStatement with an Instruction object and comment
    public void setLine(int i, IInstruction in, String c) {
        lines[i] = new LineStatement(in, c);
    }

    //Set LineStatement with a label and Instruction object
    public void setLine(int i, String l, IInstruction in) {
        lines[i] = new LineStatement(l, in);
    }

    //Set LineStatement with a label
    public void setLine(int i, String l) {
        lines[i] = new LineStatement(l);
    }

    //Set LineStatement with a label and comment
    public void setLine(int i, String l, String c) {
        lines[i] = new LineStatement(l, c);
    }


    //Set the code of a particular LineStatement
    public void setCode(int i, int code) {
        codes[i] = code;
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
            IR = IR.concat(String.format(" %s", codes[i]));
            IR = IR.concat("\n");
        }
        return IR;
    }
}

/*
  Possible Solution
  ir.setLine(0, new LineStatement(l,c));
  line[i] = ls;
*/
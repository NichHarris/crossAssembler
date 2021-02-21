//Intermediate Representation (IR) comprised of parsed LineStatements along with their respective codes
public class InterRep {

    //Array of line statements and
    private LineStatement[] lines;
    //Array of associated codes for each instruction
    private Integer[] codes;

    //Default Constructor
    public InterRep() {}

    //Parameterized Constructor
    public InterRep(int len) {
        lines = new LineStatement[len];
        codes = new Integer[len];
        for(int i=0;i<len;i++){
            lines[i]= new LineStatement();
            codes[i] = 0;
        }
    }

    //1 Set LineStatement with a LineStatement object
    public void setLine(int i, LineStatement l) {
        lines[i] = l;
    }

    //2 Set LineStatement with a label, Instruction object and comment
    public void setLine(int i, String l, Instruction in, String c) {
        lines[i] = new LineStatement(l, in, c);
    }

    //3 Set LineStatement with an Instruction object and comment
    public void setLine(int i, Instruction in, String c) {
        lines[i] = new LineStatement(in, c);
    }

    //4 Set LineStatement with a label and Instruction object
    public void setLine(int i, String l, Instruction in) {
        lines[i] = new LineStatement(l, in);
    }

    //5 Set LineStatement with a label
    public void setLine(int i, String l) { lines[i]= new LineStatement(l);}

    //6 Set LineStatement with a label and comment
    public void setLine(int i, String l, String c) {
        lines[i] = new LineStatement(l, c);
    }

    //7 Set the code of a particular LineStatement
    public void setCode(int i, int code) {
        codes[i] = code;
    }

    //Get code
    public int getCode(int i){ return codes[i];}

    //8 Get LineStatement
    public LineStatement getLine(int i) {
        return lines[i];
    }

    //9 Get length of LineStatement
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
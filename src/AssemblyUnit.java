import javax.sound.sampled.Line;
import java.lang.reflect.Array;

//Assembly Unit - Set of LineStatements + EOF
public class AssemblyUnit {
    private LineStatement[] lines;

    //Default Constructor
    public AssemblyUnit() {}

    //Parameterized Constructor
    public AssemblyUnit(int len) {
        lines = new LineStatement[len];
    }

    //Set LineStatement
    public void setLine(LineStatement l, int i) {
        lines[i] = l;
    }

    //Set LineStatement
    public void setLine(int i, String l, Instruction in, String c) {
        lines[i] = new LineStatement(l, in, c);
    }

    //Set LineStatement
    public void setLine(int i, Instruction in, String c) {
        lines[i] = new LineStatement(in, c);
    }

    //Set LineStatement
    public void setLine(int i, String l, Instruction in) {
        lines[i] = new LineStatement(l, in);
    }

    //Set LineStatement
    public void setLine(int i, String l) {
        lines[i] = new LineStatement(l);
    }

    //Set LineStatement
    public void setLine(int i, String l, String c) {
        lines[i] = new LineStatement(l, c);
    }

    //Get LineStatement
    public LineStatement getLine(int i) {
        return lines[i];
    }

    //Get LineStatements
    public int getLength() {
        return lines.length;
    }
}
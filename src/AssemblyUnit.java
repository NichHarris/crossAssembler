package src;

//Assembly Unit - Set of LineStatements + EOF
public class AssemblyUnit {
    private LineStatement[] lines;

    //Default Constructor
    public AssemblyUnit() {}

    //Parameterized Constructor
    public AssemblyUnit(LineStatement[] l) {
        lines = l;
    }

    //Set LineStatements
    public void setLines(LineStatement[] l) {
        lines = l;
    }

    //Get LineStatements
    public LineStatement[] getLines() {
        return lines;
    }
}
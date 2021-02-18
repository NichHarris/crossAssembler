package src;

//Assembly Unit - Set of LineStatements + EOF
public class AssemblyUnit {
    private src.LineStatement[] lines;

    //Default Constructor
    public AssemblyUnit() {}

    //Parameterized Constructor
    public AssemblyUnit(src.LineStatement[] l) {
        lines = l;
    }

    //Set LineStatements
    public void setLines(src.LineStatement[] l) {
        lines = l;
    }

    //Get LineStatements
    public src.LineStatement[] getLines() {
        return lines;
    }
}
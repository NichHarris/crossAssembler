package src;

import java.lang.reflect.Array;

//Assembly Unit - Set of LineStatements + EOF
public class AssemblyUnit {
    private src.LineStatement[] lines;
    private static Array codeList = null;
    private static Array labelList = null;
    private static Array mneList = null;
    private static Array operList = null;
    private static Array commentList = null;

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
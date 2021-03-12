//Interface for InterRep class
public interface IInterRep {

    //Add LineStatement with a LineStatement object
    void addLine(int i, ILineStatement l);

    //Add LineStatement with a label, Instruction object and comment
    void addLine(int i, String l, IInstruction in, String c);

    //Get LineStatement
    ILineStatement getLine(int i);

    //Get length of LineStatements array
    int getLength();

    //Set a LineStatement's label
    void setLabel(int i, String label);

    //Set a LineStatement's comments
    void setComment(int i, String comment);

    //Set a LineStatement's instruction
    void setInstruction(int i, IInstruction instruction);

    //Returns a String representable of an InterRep object
    String toString();
}

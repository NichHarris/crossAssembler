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

    //Returns a String representable of an InterRep object
    String toString();
}

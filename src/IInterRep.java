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
    void setComment(int i, IComment comment);

    //Check if instruction is null
    boolean hasInstruction(int i);

    //Get the directive of a LineStatement
    IDirective getDirective(int i);

    //Checks if a LineStatement contains a directive
    boolean hasDirective(int i);

    //Set a LineStatement's instruction
    void setInstruction(int i, IInstruction instruction);

    //Get the address of a LineStatement
    int getAddr(int i);

    //Set the address of a LineStatement
    void setAddr(int i, int val);

    //Returns a String representable of an InterRep object
    String toString();

}

package main.interfaces;

//Interface for InterRep class
public interface IInterRep {
    //Add LineStatement with a LineStatement object
    void addLine(int i, ILineStatement l);

    //Get LineStatement
    ILineStatement getLine(int i);

    //Get length of LineStatements array
    int getLength();

    //Check if instruction is null
    boolean hasInstruction(int i);

    //Get the directive of a LineStatement
    IDirective getDirective(int i);

    //Checks if a LineStatement contains a directive
    boolean hasDirective(int i);

    //Get the address of a LineStatement
    int getAddr(int i);

    //Set the address of a LineStatement
    void setAddr(int i, int val);

    //Returns a String representable of an InterRep object
    String toString();

}

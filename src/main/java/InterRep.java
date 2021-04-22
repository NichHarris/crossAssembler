/*
    SOEN 341 - Cm Cross-Assembler Version 1.4 - Developed by Team 3.

    Nicholas Kawwas - 40124338
    Matthew Sklivas - 40095150
    Nicholas Harris - 40111093
    Georgia Bardaklis - 40096586
    Karine Chatta - 27894392
    Lina Tran - 40130446
    Vincent Beaulieu - 40062386
    Philippe Lee - 40131559
    Malek Jerbi - 40130983

 */


//Import necessary files and packages
package main.java;
import main.interfaces.IDirective;
import main.interfaces.IInterRep;
import main.interfaces.ILineStatement;

//Intermediate Representation (IR) comprised of parsed LineStatements along with their respective codes
public class InterRep implements IInterRep {
    //Array of line statements
    private final ILineStatement[] lines;
    //Array of addresses for each line statement
    private final int[] addr;

    //Parameterized constructor
    public InterRep(int len) {
        lines = new LineStatement[len];
        addr = new int[len];
    }

    //Add LineStatement with a LineStatement object
    public void addLine(int i, ILineStatement l) {
        lines[i] = l;
    }

    //Get LineStatement
    public ILineStatement getLine(int i) {
        return lines[i];
    }

    //Get length of LineStatement array
    public int getLength() {
        return lines.length;
    }

    //Check line i for instruction
    public boolean hasInstruction(int i) {
        //Check for empty line or no instruction
        if (lines[i] == null || lines[i].getInstruction().getMnemonic().getMne().equals(""))
            return false;
        return true;
    }

    //Get the directive of a LineStatement
    public IDirective getDirective(int i) { return lines[i].getDirective(); }

    //Check line i for directive
    public boolean hasDirective(int i) {
        //Check for empty line or no directive
        if (lines[i] == null || lines[i].getDirective().getCString().equals(""))
            return false;
        return true;
    }

    //Get the address of a LineStatement
    public int getAddr(int i) {
        return addr[i];
    }

    //Set the address of a LineStatement
    public void setAddr(int i, int val) {
        addr[i] = val;
    }
}

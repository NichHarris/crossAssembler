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

    Position.java - (c) 2000-2021 by Michel de Champlain
 */


//Import necessary files and packages
package main.java;
import main.interfaces.IPosition;

public class Position implements IPosition {
    private int lineNumber, columnNumber;

    //Parametrized constructor
    public Position(int ln, int cn) {
        lineNumber = ln;
        columnNumber = cn;
    }

    //Return positions line number
    public int getLineNumber() { return lineNumber; }

    //Return positions column number
    public int getColumnNumber() { return columnNumber; }

    //Output as string
    public String toString() {
        return "(" + getLineNumber() + "," + getColumnNumber() + ")";
    }
}

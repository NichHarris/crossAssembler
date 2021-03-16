package main;// Java.Position.java - (c) 2001-2021 by Michel de Champlain

import Interfaces.IPosition;

public class Position implements IPosition {
    private int lineNumber, columnNumber;

    Position(int ln, int cn) {
        lineNumber = ln;
        columnNumber = cn;
    }

    public int getLineNumber() { return lineNumber; }
    public int getColumnNumber() { return columnNumber; }

    public String toString() {
        return "(" + getLineNumber() + "," + getColumnNumber() + ")";
    }
}

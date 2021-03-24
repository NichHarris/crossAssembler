// Position.java - (c) 2001-2021 by Michel de Champlain

public class Position implements IPosition {
    private int lineNumber, columnNumber;

    public Position(int ln, int cn) {
        lineNumber = ln;
        columnNumber = cn;
    }

    public int getLineNumber() { return lineNumber; }
    public int getColumnNumber() { return columnNumber; }

    public String toString() {
        return "(" + getLineNumber() + "," + getColumnNumber() + ")";
    }
}

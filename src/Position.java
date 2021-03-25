// Position.java - (c) 2001-2021 by Michel de Champlain

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

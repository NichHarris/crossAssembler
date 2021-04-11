package main.interfaces;
import main.java.Offset;
public interface ILabelTable {

    //Create new entry into labelTable
    void newEntry(String label);

    //Set Label start address
    void setLabelStart(String label, int addr);

    //Set Label end address
    void setLabelEnd(String label, int addr);

    //Get Label Address Code
    Offset getAddr(String label);

    //Have encountered label 2 times
    boolean hasAddr(String label);

    //See if a label is present in the LabelTable
    boolean hasStartLabel(String label);

    //Prints label tale
    void toConsole();
}

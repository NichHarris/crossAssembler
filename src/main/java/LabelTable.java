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
import main.interfaces.ILabelTable;
import java.util.HashMap;
import java.util.Map;

//Used to store seen labels, to be referred to on second pass for offset or addr location
public class LabelTable implements ILabelTable {
    private Offset offset;
    private final HashMap<String, Offset> labelTable;

    //Default constructor
    public LabelTable() {
        offset = new Offset();
        labelTable = new HashMap<>();
    }

    //Create new entry into labelTable
    public void newEntry(String label) {
        offset = new Offset();
        labelTable.put(label, offset);
    }

    //Set Label start address
    public void setLabelStart(String label, int addr) {
        //Get Offset object
        offset = labelTable.getOrDefault(label, new Offset());
        //Update Offset object
        offset.setAddrStart(addr);
        //Put updated Offset object back in labelTable
        labelTable.put(label, offset);
    }

    //Set Label end address
    public void setLabelEnd(String label, int addr) {
        //Get Offset object
        offset = labelTable.getOrDefault(label, new Offset());
        //Update Offset object
        offset.setAddrEnd(addr);
        //Put updated Offset object back in labelTable
        labelTable.put(label, offset);
    }

    //Get Label Address Code
    public Offset getAddr(String label) {
        return labelTable.getOrDefault(label, new Offset());
    }

    //Have encountered label 2 times
    public boolean hasAddr(String label) {
        return labelTable.get(label).getNumTimes() != 2;
    }

    //See if a label is present in the LabelTable
    public boolean hasStartLabel(String label) {
        return labelTable.containsKey(label);
    }

    //Print Label Table
    public void toConsole() {
        System.out.printf("%s %12s %10s %10s", "Label", "Start Addr", "End Addr", "Offset");
        System.out.println();

        for(String label : labelTable.keySet()) {
            int start = labelTable.get(label).getStartAddr();
            int end = labelTable.get(label).getEndAddr();
            int offset = start - end;
            System.out.printf("%5s %12s %10s %10s", label, start, end, end != -1 && start != -1 ? offset : "");
            System.out.println();
        }
        System.out.println();
    }

    public Map<String, Offset> getMap() {
        return labelTable;
    }
}

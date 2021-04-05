public interface ILabelTable {

    //Create new entry into labelTable
    void newEntry(String label, Offset newOffset);

    //Set Label start address
    void setLabelStart(String label, int addr);

    //Set Label end address
    void setLabelEnd(String label, int addr);

    //Get Label Address Code
    Offset getAddr(String label);

    //Have encountered label 2 times
    boolean hasAddr(String label);
}

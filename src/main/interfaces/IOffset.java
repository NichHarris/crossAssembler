package main.interfaces;

//Interface for main.java.Offset class
public interface IOffset {

    //Set start address and increment numTimes
    void setAddrStart(int s);

    //Set end address and increment numTimes
    void setAddrEnd(int e);

    //Get address start and end
    int getStartAddr();
    int getEndAddr();

    //Get number of times label has been encountered
    int getNumTimes();
}

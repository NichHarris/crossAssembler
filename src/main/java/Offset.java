package main.java;

import main.interfaces.IOffset;

//Class for label offsets
public class Offset implements IOffset {
    private int addrStart, addrEnd, numTimes;

    //Default constructor
    public Offset() {
        addrStart = -1;
        addrEnd = -1;
        numTimes = 0;
    }

    //Set start address and increment numTimes
    public void setAddrStart(int s) {
        addrStart = s;
    }

    //Set end address and increment numTimes
    public void setAddrEnd(int e) {
        addrEnd = e;
        numTimes++;
    }

    public int getStartAddr() {
        return addrStart;
    }

    public int getEndAddr() {
        return addrEnd;
    }

    //Get number of times label has been encountered
    public int getNumTimes() {
        return numTimes;
    }
}

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

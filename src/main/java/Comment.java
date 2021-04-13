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

import main.interfaces.IComment;

public class Comment implements IComment {
    private final String comment;

    //Default constructor
    public Comment(){
        comment = "";
    }

    //Parametrized constructor
    public Comment(String cm){
        comment = cm;
    }

    //Return a comment
    public String getCmt(){
        return comment;
    }


}

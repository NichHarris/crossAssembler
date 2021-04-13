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
import main.interfaces.*;

//Main class for the VM Cross Assembler
public class Main {
    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {
        //Set options from CL
        IOptions options = new Options(args);

        //Don't run if help or banner option is enabled
        if(!options.isHelp()) {

            //Get file name
            String fileName = options.getFile();
            //if (fileName != null) {

                //Create instance of Assembler
                Assembler assembler = new Assembler(fileName, options);

                //Execute assembler
                assembler.assemble();
            //}
        }
    }
}
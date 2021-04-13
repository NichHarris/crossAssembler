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
import main.interfaces.IInterRep;
import main.interfaces.IListing;

//Listing object used to generate listing file content
public class Listing implements IListing {

    //String array in which the listing file content will be written
    private final String[] listing;

    //String identifiers used to construct the listing file content
    private String line, addr;
    private String code;
    private String label, mne, operand;
    private String comment;


    //Parameterized Constructor
    public Listing(IInterRep intRep, String[] mCode) {
        //Get the intermediate representation
        //InterRep object for which the listing content is derived

        //Initialize a new array of string for setting the listing file contents
        listing = new String[intRep.getLength() + 1];

        String lstFormat = "%1$-5s%2$-5s%3$-14s%4$-14s%5$-14s%6$-14s%7$-20s\n";

        //Add listing header to beginning of listing file contents
        listing[0] = String.format(lstFormat, "Line", "Addr", "Code", "Label", "Mnemonic", "Operand", "Comments");

        //Traverse the IR and get line statement data for each line statement
        for (int i = 0; i < intRep.getLength(); i++){
            try {
                //Get the line number
                line = Integer.toString(i + 1);
                //Convert opcode to hex and pad with zeros
                addr = String.format("%1$04X", intRep.getAddr(i));


                //Check if instruction exists
                if(intRep.hasInstruction(i)) {
                    if (intRep.getLine(i).getInstruction().getMnemonic().getOpcode() == -1)
                        code = "";
                    else
                        code = mCode[i];

                    //Get the value (if there is one), Set to empty if it is not present, Set value from LineStatement label = (IR.getLine(i).getLabel() == null) ? "" : IR.getLine(i).getLabel();
                    mne = (intRep.getLine(i).getInstruction().getMnemonic().getMne() == null) ? "" : intRep.getLine(i).getInstruction().getMnemonic().getMne();
                    operand = (intRep.getLine(i).getInstruction().getMnemonic().getMne() == null) ? "" : intRep.getLine(i).getInstruction().getOperand().getOp();//Check If Directive Exists

                //Check if directive exists
                } else if(intRep.hasDirective(i)) {
                    code = intRep.getLine(i).getDirective().getCode();
                    mne = intRep.getLine(i).getDirective().getDir();
                    operand = intRep.getLine(i).getDirective().getCString();

                //No mnemonic or directive present
                } else {
                    mne = "";
                    operand = "";
                    code =  "";
                }

                //Checks for empty line
                if(intRep.getLine(i) == null) {
                    label = "";
                    comment = "";
                } else {
                    label = (intRep.getLine(i).getLabel() == null) ? "" : intRep.getLine(i).getLabel();
                    //Set comment from line statement
                    comment = (intRep.getLine(i).getComment() == null) ? "" : intRep.getLine(i).getComment().getCmt();
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }

            //Add line statement components to listing in table format
            listing[i + 1] = String.format(lstFormat, line, addr, code, label, mne, operand, comment);
        }
    }

    // Get formatted listing string
    public String[] getListing() {
        return listing;
    }
}

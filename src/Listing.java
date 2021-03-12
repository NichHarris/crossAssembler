//Listing object used to generate listing file content
public class Listing implements IListing {

    //InterRep object for which the listing content is derived
    private IInterRep IR;

    //String identifiers used to construct the listing file content
    private String line;
    private String addr;
    private String code;
    private String label;
    private String mne;
    private String operand;
    private String comment;
    private boolean instructionExists;
    //String array in which the listing file content will be written
    private String[] listing;

    //Parameterized Constructor
    public Listing(IInterRep intRep) {
        //Get the intermediate representation
        IR = intRep;

        //Initialize a new array of string for setting the listing file contents
        listing = new String[IR.getLength() + 1];

        String lstFormat = "%1$-5s%2$-5s%3$-14s%4$-14s%5$-9s%6$-14s%7$-20s\n";

        //Add listing header to beginning of listing file contents
        listing[0] = String.format(lstFormat, "Line", "Addr", "Machine Code", "Label", "Assembly", "Code", "Comments");

        //Traverse the IR and get line statement data for each line statement
        for (int i = 0; i < IR.getLength(); i++){
            try {
                //Get the line number
                line = Integer.toString(i);
                //Convert opcode to hex and pad with zeros
                addr = String.format("%1$04X",i);
                //Get the opcode
                if (IR.getLine(i).getInstruction().getMnemonic().getOpcode() == -1){
                    code = "";
                }
                else {
                    code = String.format("%1$02X", IR.getLine(i).getInstruction().getMnemonic().getOpcode());
                }
                label = (IR.getLine(i).getLabel() == null) ? "" : IR.getLine(i).getLabel();
                //Get the value (if there is one), Set to empty if it is not present, Set value from line statementlabel = (IR.getLine(i).getLabel() == null) ? "" : IR.getLine(i).getLabel();
                if (IR.getLine(i).getInstruction() != null) {
                    if (IR.getLine(i).getInstruction().getMnemonic().getOpcode() == -1){
                        code = "";
                    }
                    else {
                        code = String.format("%1$02X", IR.getLine(i).getInstruction().getMnemonic().getOpcode());
                    }
                    mne = (IR.getLine(i).getInstruction().getMnemonic().getMne() == null) ? "" : IR.getLine(i).getInstruction().getMnemonic().getMne();
                    operand = (IR.getLine(i).getInstruction().getOperand().getOp() == null) ? "" : IR.getLine(i).getInstruction().getOperand().getOp();
                }else{
                    mne = "";
                    operand = "";
                    code =  ""; //String.format("%1$02X", "");
                }
                //Set comment from line statement
                comment = (IR.getLine(i).getComment() == null) ? "" : IR.getLine(i).getComment();
            } catch(Exception e){
                System.out.println(e.toString());
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

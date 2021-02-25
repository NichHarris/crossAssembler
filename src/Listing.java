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

    //String array in which the listing file content will be written
    private String[] listing;

    //Parameterized Constructor
    public Listing(IInterRep intRep) {
        //Get the intermediate representation
        IR = intRep;

        //Initialize a new array of string for setting the listing file contents
        listing = new String[IR.getLength() + 1];

        String lstFormat = "%1$-5s%2$-5s%3$-14s%4$-14s%5$-6s%6$-14s%7$-20s\n";

        //Add listing header to beginning of listing file contents
        listing[0] = String.format(lstFormat, "Line", "Addr", "Code", "Label", "Mne", "Operand", "Comments");

        //Traverse the IR and get line statement data for each line statement
        for (int i = 0; i < IR.getLength(); i++){
            //Get the line number
            line = Integer.toString(i);
            //Convert opcode to hex and pad with zeros
            addr = String.format("%1$04X",i);
            //Get the opcode
            code = String.format("%1$02X", IR.getLine(i).getInstruction().getMnemonic().getOpcode());

            //Get the label (if there is one)
            if (IR.getLine(i).getLabel() == null) {
                //Set label to empty if label is not present
                label = "";
            } else {
                //Set label from line statement
                label = IR.getLine(i).getLabel();
            }
            //Get the mnemonic (if there is one)
            if (IR.getLine(i).getInstruction().getMnemonic().getMne() == null) {
                //Set empty to string if mnemonic is not present
                mne = "";
            } else {
                //Set mnemonic from line statement
                mne = IR.getLine(i).getInstruction().getMnemonic().getMne();
            }
            //Get the operand (if there is one)
            if (IR.getLine(i).getInstruction().getOperand().getOp() == null) {
                //Set operand to empty string if operand is not present
                operand = "";
            }
            else {
                //Set operand from line statement
                operand = IR.getLine(i).getInstruction().getOperand().getOp();
            }
            //Set comment from line statement
            comment = IR.getLine(i).getComment();

            //Add line statement components to listing in table format
            listing[i + 1] = String.format(lstFormat, line, addr, code, label, mne, operand, comment);
        }
    }

    // Get formatted listing string
    public String[] getListing() {
        return listing;
    }
}

import javax.sound.sampled.Line;

// Need line, addr, code, label, mne, operand, comment
public class Listing {
    private AssemblyUnit unit;
    private String line;
    private String addr;
    private String code;
    private String label;
    private String mne;
    private String operand;
    private String comment;
    private LineStatement[] fullLine;
    private String[] listing;

    // Parameterized Constructor
    public Listing(AssemblyUnit assUnit) {
        unit = assUnit;
        listing = new String[unit.getLength() + 1];
        listing[0] = String.format("%1$-5s%2$-5s%3$-14s%4$-14s%5$-6s%6$-14s%7$-20s", "Line", "Addr", "Code", "Label", "Mne", "Operand", "Comments");
        for (int i = 0; i < unit.getLength(); i++){
            // TODO: need to find a better way to manage cases where certain fields returns null in Instruction before this can work
            // TODO: (contd.) need to decide if we keep data members as null or empty strings in their respective classes
            line = Integer.toString(i);
            addr = String.format("%1$04X",i); // convert to hex and pad with zeros
            code = String.format("%1$02X", unit.getLine(i).getCode());
            if (unit.getLine(i).getLabel() == null) {
                label = "";
            } else {
                label = unit.getLine(i).getLabel();
            }
            if (unit.getLine(i).getInstruction().getMnemonic() == null) {
                mne = "";
            } else {
                mne = unit.getLine(i).getInstruction().getMnemonic();
            }
            if (unit.getLine(i).getInstruction().getOperand() == null) {
                operand = "";
            }
            else {
                operand = unit.getLine(i).getInstruction().getOperand();
            }
            comment = unit.getLine(i).getComment();
            listing[i + 1] = String.format("%1$-5s%2$-5s%3$-14s%4$-14s%5$-6s%6$-14s%7$-20s", line, addr, code, label, mne, operand, operand);
        }
    }

    public String[] getListing() {
        return listing;
    }

    // TODO: Probably don't need these extra functions
    /*
    // Set Addr + Line + Code + Full Line
    public void setAddr(int a) {
        addr = a;
    }

    public void setLine(int l) {
        line = l;
    }

    public void setCode(int c) {
        code = c;
    }

    public void setLS(LineStatement[] ls) {
        fullLine = ls;
    }

    // Get Addr + Line + Code + Full Line
    public int getAddr() {
        return addr;
    }

    public int getLine() {
       return line;
    }

    public int getCode() {
        return code;
    }

    public LineStatement[] getLS() {
        return fullLine;
    }
     */
}

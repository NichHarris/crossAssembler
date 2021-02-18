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
        listing[0] = String.format("%16s%16s%16s%16s%16s%16s%16s\n", "Line", "Addr", "Code", "Label", "Mne", "Operand", "Comments");
        for (int i = 0; i < unit.getLength(); i++){
            // TODO: need to manage cases where certain fields (mainly mne) returns null in Instruction before this can work
            line = Integer.toString(i);
            addr = String.format("%1$04X",i); // convert to hex and pad with zeros
            code = Integer.toString(unit.getLine(i).getCode());
            label = unit.getLine(i).getLabel();
            mne = unit.getLine(i).getInstruction().getMnemonic();
            operand = unit.getLine(i).getInstruction().getOperand();
            comment = unit.getLine(i).getComment();
            listing[i + 1] = String.format("%16s%16s%16s%16s%16s%16s%16s\n", line, addr, code, label, mne, operand, operand);
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

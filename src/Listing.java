package src;

// Need line, addr, code, label, mne, operand, comment
public class Listing {
    private String mnemonic;
    private String operand;

    // Parameterized Constructor
    public Listing(String m, String o) {
        mnemonic = m;
        operand = o;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String getOperand() {
        return operand;
    }
}

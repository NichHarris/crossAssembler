import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

//Generates executable file and listing file
public class CodeGenerator implements ICodeGenerator {

    //Instance of the IR
    private IInterRep interRep;
    //Array of machine codes for each LineStatement
    private String[] mCode;
    private String fileName;

    //Default constructor
    public CodeGenerator(IInterRep IR, IOptions options, String filename) {

        interRep = IR;
        mCode = new String[interRep.getLength()];
        fileName = filename.substring(0, filename.indexOf("."));
        generateMachineCode();

        //Generate listing file with label table
        //Options not yet in use
        if (options.isVerbose()){
            //TODO: Need to implement extra functionality for verbose option
            IListing listing = new Listing(IR, mCode);
            String [] lstContent = listing.getListing();
            generateListing(lstContent);

            //Need to print label table also
        }
        //Generate listing file
        else if (options.isListing()){
            IListing listing = new Listing(IR, mCode);
            String [] lstContent = listing.getListing();
            generateListing(lstContent);
        }

        //Formatting mCode to String for executable output
        String str = "";
        for(String s: mCode)
            if(!s.equals(""))
                str += s + " ";
        System.out.println(str);
        //Return bin
        generateExec(str);
    }

    //Generate a listing file
    public void generateListing(String[] lstContent) {
        // Create listing.lst output file
        try {
            FileOutputStream fs = new FileOutputStream(fileName.concat(".lst"));

            // Write to listing.lst file
            for(String l : lstContent) {
                char[] cArr = l.toCharArray();
                for(char c : cArr)
                    fs.write(c);
            }

            // Close listing.lst file
            fs.close();

        } catch (Exception e) { e.printStackTrace(); }
    }

    //Sets the machine code of each line statement
    public void generateMachineCode() {
        //Set the machine code of each line statement
        for (int i = 0; i < interRep.getLength(); i++) {
            //Get the opcode and operand of the line statement
            if(interRep.hasInstruction(i) || interRep.hasDirective(i)) {

                String operand = interRep.getLine(i).getInstruction().getOperand().getOp();

                //Directive
                if (interRep.hasDirective(i)) {
                    mCode[i] = interRep.getLine(i).getDirective().getCode();
                }
                //If operand is a label or string
                else if (!interRep.getLine(i).getInstruction().getOperand().isNumeric() && !operand.equals("")) {
                    String label = interRep.getLine(i).getInstruction().getOperand().getOp();
                    int code = interRep.getLine(i).getInstruction().getMnemonic().getOpcode();

                    if (interRep.getLine(i).getInstruction().getMnemonic().getOpcode() >= 0xE0) {
                        for (int j = 0; j < interRep.getLength(); j++) {
                            if (interRep.getLine(j) != null) {
                                String currLabel = interRep.getLine(j).getLabel();
                                int currAddr = interRep.getAddr(j);
                                if (currLabel.equals(label)) {
                                    int startAddress = interRep.getAddr(i);
                                    int address = currAddr - startAddress;
                                    if (address < 0) {
                                        address += 256;
                                    }
                                    mCode[i] = String.format("%02X %02X", interRep.getLine(i).getInstruction().getMnemonic().getOpcode(), address);
                                }
                            }
                        }
                    } else {    //Find the address where the label is declared
                        for (int j = i + 1; j < interRep.getLength(); j++) {
                            if (interRep.getLine(j) != null) {
                                String currLabel = interRep.getLine(j).getLabel();
                                if (currLabel.equals(label)) {
                                    int address = interRep.getAddr(j);
                                    mCode[i] = String.format("%s %s", Integer.toHexString(code).toUpperCase(), String.format("%1$04X", address));
                                }
                            }
                        }
                    }
                } else {
                    if (interRep.getLine(i).getInstruction().getMnemonic().getOpcode() == -1) {
                        mCode[i] = "";
                    } else if (interRep.getLine(i).getInstruction().getMnemonic().getOpcode() >= 0xB0) {
                        mCode[i] = String.format("%02X %02X", interRep.getLine(i).getInstruction().getMnemonic().getOpcode(), Integer.parseInt(interRep.getLine(i).getInstruction().getOperand().getOp()));
                    } else {
                        mCode[i] = String.format("%02X", interRep.getLine(i).getInstruction().getMnemonic().getOpcode());
                    }
                }

            } else {
                mCode[i] = "";
            }
        }
    }

    //Generate an executable file
    public void generateExec(String c) {
        try {
            //Create output stream and empty file
            BufferedOutputStream bfos = new BufferedOutputStream(new FileOutputStream(fileName.concat(".bin")));

            //Write to file
            byte[] contentB = c.getBytes();
            for(byte b: contentB)
                bfos.write(b);

            bfos.close();
        } catch (Exception e) { e.getMessage(); }
    }
}
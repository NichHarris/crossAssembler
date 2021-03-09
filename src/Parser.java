import java.util.ArrayList;

//Parser - Performs analysis the syntax of tokens and generates the correct IR
public class Parser implements IParser {
    private IInterRep ir;
    private ISymbolTable symbolTable = new SymbolTable();

    //Parser constructor initializes IR using number of lines from Reader
    public Parser(IInterRep ir, String tkn, int lineNum, int colNum) {
        //Get Hex Code from Symbol Table
        int code = symbolTable.getCode(tkn);

        // Check if LineStatement at line number is null
        if()
        // Check If Element is Label
        // TODO: Check for operand, handle errors
        if(code == -1) {
            String lb = tkn;
        // Check If Element is Mnemonic
        } else {
            Mnemonic mn = new Mnemonic(tkn, code);

            //Immediate Addressing
            if(code > 0x1F && code < 0xB0) {
                //???
                Operand op = new Operand(tkn);
                Instruction in = new Instruction(mn, op);
            }
            //Relative Addressing
            else if (code >= 0xB0) {
                //???
                Operand op = new Operand(tkn);
                Instruction in = new Instruction(mn, op);
            //Stack-Inherent Addressing
            } else {
                Instruction in = new Instruction(mn);
            }
        }

        // Create a Comment
        if(tkn.startsWith(";"))
            Comment cm = new Comment(tkn);

        // TODO: Figure Out When End Line Reached?

        // Create LineStatement
        LineStatement ls = LineStatement(lb, in, cm);

        // Add LineStatement to InterRep
        InterRep.addLine(lineNum, ls);
    }

    public void parsing(){
        //Get Hex Code from Symbol Table
        int code = symbolTable.getCode(tkn);

        // Check if LineStatement at line number is null
        if()
            // Check If Element is Label
            // TODO: Check for operand, handle errors
            if(code == -1) {
                String lb = tkn;
                // Check If Element is Mnemonic
            } else {
                Mnemonic mn = new Mnemonic(tkn, code);

                //Immediate Addressing
                if(code > 0x1F && code < 0xB0) {
                    //???
                    Operand op = new Operand(tkn);
                    Instruction in = new Instruction(mn, op);
                }
                //Relative Addressing
                else if (code >= 0xB0) {
                    //???
                    Operand op = new Operand(tkn);
                    Instruction in = new Instruction(mn, op);
                    //Stack-Inherent Addressing
                } else {
                    Instruction in = new Instruction(mn);
                }
            }

        // Create a Comment
        if(tkn.startsWith(";"))
            Comment cm = new Comment(tkn);

        // TODO: Figure Out When End Line Reached?

        // Create LineStatement
        LineStatement ls = LineStatement(lb, in, cm);

        // Add LineStatement to InterRep
        InterRep.addLine(lineNum, ls);
    }
    }
/*
    //Parametrized constructor
    public Parser(IScanner scanner, IInterRep IR){
        //Get the list of tokens and comments from the scanner
        ArrayList<ArrayList<String>> tokensList = scanner.getTokens();
        String[] comments = scanner.getComments();


        private

        //Traverse through tokenList and perform syntax analysis
        for(int i = 0; i < IR.getLength(); i++){
            int numTokens = tokensList.get(i).size();
            switch(numTokens) {
                //Stack + Inherent Addressing Mode
                //Cases - Mnemonic or Label
                case(1):
                    try {
                        //Get hex code from symbol table indexed token
                        int code = symbolTable.getCode(tokensList.get(i).get(0));

                        //Check if no mnemonic is included
                        if(code == -1)
                            throw new Exception("Error: Mnemonic Not Found at line " + (i + 1));
                            //Check if hex code requires no operand
                        else if(code >= 0x00 && code <= 0x1F) {
                            //Add LineStatement to AssemblyUnit
                            IR.addLine(i,null, new Instruction(new Mnemonic(tokensList.get(i).get(0), code), new Operand(null)), comments[i]);
                        }
                        //Check if hex code requires operand
                        else { throw new Exception("Error: Missing an Operand"); }
                    } catch (Exception e) {
                        System.out.println(e);
                        System.exit(0);
                    }

                    //Other Case - Check if Element is Present in Label Table
                    break;

                //Immediate Addressing Mode
                //Cases - Mnemonic + Operand or Label + Mnemonic
                case(2):
                    //Check First Element in HashSet for Mnemonic
                    //If not, Add first Element to Label Table & Check Second Element

                    //System.out.println("Error: Mnemonic Missing/Not Found");
                    //System.out.println("Error: Missing an Operand");
                    //System.out.println("Error: Operand is Too Large");
                    //System.out.println("Error: Operand Not Allowed");
                    break;

                //Relative Addressing Mode
                //Cases - Label + Mnemonic + Operand
                case(3):
                    //Add First Element to Label List
                    //Check Second Element in HashSet for Mnemonic
                    //Check Third Element

                    //System.out.println("Error: Mnemonic Not Found");
                    //System.out.println("Error: Operand is Too Large");
                    //System.out.println("Error: Operand Not Allowed");
                    break;

                default:
                    System.out.println("Error: Exceeds Possible Number of Elements Per line" + (i + 1));
            }
        }
    }
 */
}

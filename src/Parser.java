import java.util.ArrayList;

//Parser - Performs analysis the syntax of tokens and generates the correct IR
public class Parser implements IParser {

    //Parametrized constructor
    public Parser(IScanner scanner, IInterRep IR){
        //Get the list of tokens and comments from the scanner
        ArrayList<String[]> tokensList = scanner.getTokens();
        String[] comments = scanner.getComments();

        //Traverse through tokenList and perform syntax analysis
        for(int i = 0; i < IR.getLength(); i++){
            int numTokens = tokensList.get(i).length;
            switch(numTokens) {
                //Stack + Inherent Addressing Mode
                case(1):
                    //System.out.println("Mnemonic || Label");
                    //Check in HashSet for Mnemonic
                    //If not, Add Element to Label Table
                    ISymbolTable symbolTable = new SymbolTable();

                    //Get hex code from symbol table indexed token
                    int code = symbolTable.getCode(tokensList.get(i)[0]);

                    //Check if no mnemonic is included
                    if(code == -1)
                        System.out.println("Error: Mnemonic Not Found");
                    //Check if hex code requires no operand
                    else if(code >= 0x00 && code <= 0x1F) {
                        //Add LineStatement to AssemblyUnit
                        IR.setLine(i,null, new Instruction(tokensList.get(i)[0], ""), comments[i]);
                        IR.setCode(i, code);
                    }
                    //Check if hex code requires operand
                    else if(code > 0x1F)
                            System.out.println("Error: Missing an Operand");
                    //Check if label exists in label table Else Add to Table

                    //toHexString
                    //System.out.print(Integer.toBinaryString(code) + " ");
                    break;
                //Immediate Addressing Mode
                case(2):
                    //System.out.println("Mnemonic + Operand || Label + Mnemonic");
                    //Check First Element in HashSet for Mnemonic
                    //If not, Add first Element to Label Table & Check Second Element


                    //System.out.println("Error: Mnemonic Missing/Not Found");
                    //System.out.println("Error: Missing an Operand");
                    //System.out.println("Error: Operand is Too Large");
                    //System.out.println("Error: Operand Not Allowed");
                    break;
                //Relative Addressing Mode
                case(3):
                    //System.out.println("Label + Mnemonic + Operand");
                    //Add First Element to Label List
                    //Check Second Element in HashSet for Mnemonic
                    //Check Third Element

                    //System.out.println("Error: Mnemonic Not Found");
                    //System.out.println("Error: Operand is Too Large");
                    //System.out.println("Error: Operand Not Allowed");
                    break;
                default:
                    System.out.println("Error: Exceeds Possible Number of Elements Per line");
            }
        }
    }
}

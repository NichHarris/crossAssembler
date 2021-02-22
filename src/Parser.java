import java.util.ArrayList;

//Parser - Performs analysis the syntax of tokens and generates the correct IR
public class Parser implements IParser {

    //Parametrized constructor
    public Parser(IScanner scanner, IInterRep IR){
        //Get the list of tokens and comments from the scanner
        ArrayList<String[]> tokensList = scanner.getTokens();
        String[] comments = scanner.getComments();

        ISymbolTable symbolTable = new SymbolTable();

        //Traverse through tokenList and perform syntax analysis
        for(int i = 0; i < IR.getLength(); i++){
            int numTokens = tokensList.get(i).length;
            switch(numTokens) {
                //Stack + Inherent Addressing Mode
                //Cases - Mnemonic or Label
                case(1):
                    try {
                        //Get hex code from symbol table indexed token
                        int code = symbolTable.getCode(tokensList.get(i)[0]);

                        //Check if no mnemonic is included
                        if(code == -1)
                            throw new Exception("Error: Mnemonic Not Found at line ");
                            //Check if hex code requires no operand
                        else if(code >= 0x00 && code <= 0x1F) {
                            //Add LineStatement to AssemblyUnit
                            IR.setLine(i, new Instruction(tokensList.get(i)[0], ""), comments[i]);
                            IR.setCode(i, code);
                        }
                        //Check if hex code requires operand
                        else { throw new Exception("Error: Missing an Operand"); }
                    } catch (Exception e) {
                        System.out.println(e);
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
                    System.out.println("Error: Exceeds Possible Number of Elements Per line" + i + 1);
            }
        }
    }
}

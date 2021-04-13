package test.java;

import main.interfaces.IPosition;
import main.interfaces.IToken;
import main.java.Position;
import main.java.Token;
import main.java.TokenType;

public class TokenTest {
    public static void main(String[] args) throws Exception {
        TextFileGenerator.textFileGenerator("TokenTest.txt");

        IPosition p1 = new Position(1, 2);   // (line, col)
        IPosition p2 = new Position(2, 4);
        IPosition p3 = new Position(3, 6);


        IToken t1 = new Token(p1, "halt", TokenType.Mnemonic);
        IToken t2 = new Token(p2, "pop",  TokenType.Mnemonic);
        IToken t3 = new Token(p3, "dup",  TokenType.Mnemonic);


        System.out.println("Test Token");
        System.out.println("[halt(1,2)=Mnemonic][pop(2,4)=Mnemonic][dup(3,6)=Mnemonic]");

        System.out.print(t1);
        System.out.print(t2);
        System.out.print(t3);
        System.out.println();


    }
}
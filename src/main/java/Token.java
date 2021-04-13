/*
    SOEN 341 - Cm Cross-Assembler Version 1.4 - Developed by Team 3.

    Nicholas Kawwas - 40124338
    Matthew Sklivas - 40095150
    Nicholas Harris - 40111093
    Georgia Bardaklis - 40096586
    Karine Chatta - 27894392
    Lina Tran - 40130446
    Vincent Beaulieu - 40062386
    Philippe Lee - 40131559
    Malek Jerbi - 40130983

    Token.java - (c) 2000-2021 by Michel de Champlain

 */


//Import necessary files and packages
package main.java;
import main.interfaces.IPosition;
import main.interfaces.IToken;

public class Token implements IToken {
    private IPosition  pos;
    private TokenType code;
    private String    name;

    public Token(IPosition p, String n, TokenType c) {
        pos = p;
        name = n;
        code = c;
    }

    //Getters - position, name and code
    public IPosition getPosition() { return pos; }
    public String getName() { return name; }
    public TokenType getCode() { return code; }

    //Output as string
    public String toString() { return "[" + getName() + pos + "=" + code + "]"; }
}

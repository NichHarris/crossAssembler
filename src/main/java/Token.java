package main.java;// Token.java - (c) 2000-2021 by Michel de Champlain

import main.interfaces.IPosition;
import main.interfaces.IToken;

public class Token implements IToken {
    private final IPosition  pos;
    private final TokenType code;
    private final String    name;

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

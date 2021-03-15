// Token.java - (c) 2000-2021 by Michel de Champlain

public class Token implements IToken {
    private Position  pos;
    private TokenType code;
    private String    name;

    Token(Position p, String n, TokenType c) {
        pos = p;
        name = n;
        code = c;
    }

    //Getters - Position, Name and Code
    public Position getPosition() { return pos; }
    public String getName() { return name; }
    public TokenType getCode() { return code; }

    public String toString() { return "[" + getName() + pos + "=" + code + "]"; }
}

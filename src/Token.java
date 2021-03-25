// Token.java - (c) 2000-2021 by Michel de Champlain

public class Token implements IToken {
    private Position  pos;
    private TokenType code;
    private String    name;

    public Token(Position p, String n, TokenType c) {
        pos = p;
        name = n;
        code = c;
    }

    //Getters - position, name and code
    public Position getPosition() { return pos; }
    public String getName() { return name; }
    public TokenType getCode() { return code; }

    //Output as string
    public String toString() { return "[" + getName() + pos + "=" + code + "]"; }
}

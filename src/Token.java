// Token.java - (c) 2000-2021 by Michel de Champlain

public class Token implements IToken {

    Token(Position pos, String name, TokenType code) {
        this.pos = pos;
        this.name = name;
        this.code = code;
    }

    public  Position  getPosition()  { return pos; }
    public  String    getName()      { return name; }
    public  TokenType getCode()      { return code; }
    public  String    toString()     { return "["+getName()+pos+"="+code+"]"; }

    private Position  pos;
    private TokenType code;
    private String    name;
}

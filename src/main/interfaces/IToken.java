package main.interfaces;// IToken.java - (c) 2000-2021 by Michel de Champlain

import main.java.Position;
import main.java.TokenType;

/**
 * A token has a position, a name, and a code (token type).
 */
public interface IToken {
    IPosition getPosition();
    String    getName();
    TokenType getCode();
}

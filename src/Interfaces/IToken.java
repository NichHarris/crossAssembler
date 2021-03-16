package Interfaces;// Interfaces.IToken.java - (c) 2000-2021 by Michel de Champlain

import main.Position;
import main.TokenType;

/**
 * A token has a position, a name, and a code (token type).
 */
public interface IToken {
    Position getPosition();
    String    getName();
    TokenType getCode();
}

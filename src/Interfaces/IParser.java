package Interfaces;

import Interfaces.IInterRep;

//Interface for Java.Parser class
public interface IParser {

    //Parse a token received from Java.Scanner
    void parseToken(IToken token);

    //Get the intermediate representation
    IInterRep getInterRep();

    //Second pass through Java.Parser to update machine code
    void secondPass();
}

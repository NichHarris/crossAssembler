package main.interfaces;

//Interface for Parser class
public interface IParser {

    //Parse a token received from Scanner
    void parseToken();

    //Parses to IR
    void parseToIR(IToken token);

    //Get the intermediate representation
    IInterRep getInterRep();
}

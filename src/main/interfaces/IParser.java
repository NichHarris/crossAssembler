package main.interfaces;

//Interface for Parser class
public interface IParser {

    //Parse a token received from Scanner
    void parseToken();

    //Get the intermediate representation
    IInterRep getInterRep();
}

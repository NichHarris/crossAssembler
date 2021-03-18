//Interface for Parser class
public interface IParser {

    //Parse a token received from Scanner
    void parseToken(IToken token);

    //Get the intermediate representation
    IInterRep getInterRep();

    //Print error recorded by ErrorReporter (if there are any)
    void reportErrors();
}

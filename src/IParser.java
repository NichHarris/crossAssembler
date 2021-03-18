//Interface for Parser class
public interface IParser {

    //Parse a token received from Scanner
    void parseToken(IToken token);

    //Get the intermediate representation
    IInterRep getInterRep();

    //Second pass through Parser to update machine code
    void secondPass();

    //Print error recorded by ErrorReporter (if there are any)
    void reportErrors();
}

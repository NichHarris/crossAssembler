public interface IErrorReporter {

    //Adds an error from Scanner to reportedErrors
    void addError(int errNum, int line, int col);

    //Adds an error from Parser to reportedErrors
    void addError(int errNum, IToken token);

    //Print the errors to console
    void reportErrors();
}

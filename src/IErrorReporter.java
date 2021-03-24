//Interface for ErrorReporter
public interface IErrorReporter {

    //Adds an error to the list of recorded errors
    void record(ErrorMsg error);

    //Print the errors to console
    void report();
}

public interface IDirective {
    //Get cstring representation char array
    String getCode();

    //Gets directive
    String getDir();

    //Get cString characters
    String getCString();

    void setCString(String name);
}

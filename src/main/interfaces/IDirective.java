package main.interfaces;

public interface IDirective {

    //Get string code - convert char to ASCII, add null character (00) at end
    String getCode();

    //Return directive variable
    String getDir();

    //Return cstring variable
    String getCString();

    //Set cstring variable
    void setCString(String name);
}

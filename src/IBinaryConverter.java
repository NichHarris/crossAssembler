public interface IBinaryConverter {

    //Check for overflow
    boolean isOverflow(int n, int size, boolean state);

    //Check for signed overflow
    boolean isSignedOverflow(int n, int size);

    //Check for unsigned overflow
    boolean isUnsignedOverflow(int n, int size);

    //Return the binary value of a string
    int getBinaryValue(String s);

    //Convert to binary
    String toBinary(int n, int size);

    //Get twos comp of a binary value
    String getTwosComplement(String b);


}

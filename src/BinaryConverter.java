public class BinaryConverter implements IBinaryConverter{

    //Check for overflow
    public boolean isOverflow(int n, int size, boolean state) {
        return (state ? isSignedOverflow(n, size) : isUnsignedOverflow(n, size));
    }

    //Check for signed overflow
    public boolean isSignedOverflow(int n, int size) {
        double range = Math.pow(2, size - 1);
        return (-1 * range > n) || (range - 1 < n);
    }

    //Check for unsigned overflow
    public boolean isUnsignedOverflow(int n, int size) {
        double range = Math.pow(2, size);
        return (0 >= n) || (range - 1 < n);
    }

    //Return the binary value of a string
    public int getBinaryValue(String s) {
        int len = s.length();
        int bin = 0, i = len;

        while(i-- > 0)
            bin += s.charAt(i) == '1' ? (Math.pow(2, len - 1 - i)) : 0;

        return bin;
    }

    //Convert to binary
    public String toBinary(int n, int size) {
        //Determine whether value is positive or negative
        boolean neg = n < 0 ? true : false;

        //Remove positive sign for binary conversion
        n = neg ? n * -1 : n;

        //Binary conversion
        String b = "";
        int i = 0;
        while(i++ < size) {
            b = n % 2 == 1 ? "1" + b : "0" + b;

            n /= 2;
        }

        return neg ? getTwosComplement(b) : b;

    }
    //Get twos comp of a binary value
    public String getTwosComplement(String b) {
        int i = 0;
        String s = "";

        // 1s Complement, Flipping all bits
        while(i < b.length())
            s += b.charAt(i++) == '1' ? '0' : '1';

        // 2s Complement, Add 1
        int carry = 1;
        boolean state;
        String t = "";

        while(i-- > 0) {
            state = s.charAt(i) == '1' && carry == 1;
            t = state ? '0' + t : (carry == 1 ? '1' + t : s.charAt(i) + t);
            carry = state ? 1 : 0;
        }

        return t;
    }
}

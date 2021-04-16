/*
    SOEN 341 - Cm Cross-Assembler Version 1.4 - Developed by Team 3.

    Nicholas Kawwas - 40124338
    Matthew Sklivas - 40095150
    Nicholas Harris - 40111093
    Georgia Bardaklis - 40096586
    Karine Chatta - 27894392
    Lina Tran - 40130446
    Vincent Beaulieu - 40062386
    Philippe Lee - 40131559
    Malek Jerbi - 40130983

 */


//Import necessary files and packages
package main.java;
import main.interfaces.IBinaryConverter;

public class BinaryConverter implements IBinaryConverter {
    //Check for overflow
    public boolean isOverflow(int shiftVal, int size, boolean state) {
        return (state ? isSignedOverflow(shiftVal, size) : isUnsignedOverflow(shiftVal, size));
    }

    //Check for signed overflow
    public boolean isSignedOverflow(int shiftVal, int size) {
        double range = Math.pow(2, size - 1);
        return (-1 * range > shiftVal) || (range - 1 < shiftVal);
    }

    //Check for unsigned overflow
    public boolean isUnsignedOverflow(int shiftVal, int size) {
        double range = Math.pow(2, size);
        return (0 > shiftVal) || (range - 1 < shiftVal);
    }

    //Return the binary value of a string
    public int getBinaryValue(String s) {
        int slen = s.length();
        int binary = 0, i = slen;

        while(i-- > 0)
            binary += s.charAt(i) == '1' ? (Math.pow(2, slen - 1 - i)) : 0;

        return binary;
    }

    //Convert to binary
    public String toBinary(int shiftVal, int size) {
        //Determine whether value is positive or negative
        boolean isNegative = shiftVal < 0;

        //Remove positive sign for binary conversion
        shiftVal = isNegative ? shiftVal * -1 : shiftVal;

        //Binary conversion
        String binary = "";
        int i = 0;
        while(i++ < size) {
            binary = shiftVal % 2 == 1 ? "1" + binary : "0" + binary;

            shiftVal /= 2;
        }

        return isNegative ? getTwosComplement(binary) : binary;
    }

    //Get twos comp of a binary value
    public String getTwosComplement(String binStr) {
        int i = 0;
        String str = "";

        // 1s complement, flipping all bits
        while(i < binStr.length())
            str += binStr.charAt(i++) == '1' ? '0' : '1';

        // 2s complement, add 1
        int carry = 1;
        boolean state;
        String result = "";

        while(i-- > 0) {
            state = str.charAt(i) == '1' && carry == 1;
            result = state ? '0' + result : (carry == 1 ? '1' + result : str.charAt(i) + result);
            carry = state ? 1 : 0;
        }

        return result;
    }
}

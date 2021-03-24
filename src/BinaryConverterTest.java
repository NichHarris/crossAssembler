public class BinaryConverterTest {
    public static void main(String args[]) throws Exception {
//        TestBinaryConverter("Test -BinaryConverter Class- getOverflow()" , "false", Boolean.toString(BinaryConverter.getOverflow(10, 5)));

//        TestBinaryConverter("Test -BinaryConverter Class- getBinaryValue(String s)", "10",Integer.toString(BinaryConverter.getBinaryValue(BinaryConverter.toBinary(10, 5))));
//
//        TestBinaryConverter("Test -BinaryConverter Class- toBinary()" , "01010", BinaryConverter.toBinary(10, 5));
//
//        TestBinaryConverter("Test -BinaryConverter Class- getTwosComplement(String b)" , "101", BinaryConverter.getTwosComplement("011"));
    }

    public static void TestBinaryConverter(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

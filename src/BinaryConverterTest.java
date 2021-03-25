public class BinaryConverterTest {
    public static void main(String []args) throws Exception {
        BinaryConverter converter = new BinaryConverter();
        boolean state = true;
        TestBinaryConverter("isOverflow with a signed binary number", "true", Boolean.toString(converter.isOverflow(4, 3, state)));

        state = false;
        TestBinaryConverter("isOverflow with a unsigned binary number", "false", Boolean.toString(converter.isOverflow(4, 3, state)));

        TestBinaryConverter("getBinaryValue()", "3", Integer.toString(converter.getBinaryValue("011")));

        TestBinaryConverter("toBinary()", "011", converter.toBinary(3,3));

        TestBinaryConverter("getTwosComplement()", "101",converter.getTwosComplement("011"));
    }

    public static void TestBinaryConverter(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test " +testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

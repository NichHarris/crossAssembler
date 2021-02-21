public class ListingTest {
    public static void main(String[] args) throws Exception {
       //Listing lst = new Listing("%1$-5s%2$-5s%3$-14s%4$-14s%5$-6s%6$-14s%7$-20s","0", "0000", "00", "", "halt", "", "" );

        //TestListing("Test getListing()", "'%1$-5s%2$-5s%3$-14s%4$-14s%5$-6s%6$-14s%7$-20s' '0' '0000' '00' '' 'halt' '' '', lst.getListing());

    }

    public static void TestListing(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

/*
import java.util.ArrayList;

public class ListingTest {
    public static void main(String[] args) throws Exception {
        Java.Reader fileContent = null;
        try {
            fileContent = new Java.Reader("testfile.asm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> assemblyUnit = fileContent.getAssemblyUnit();
        Java.InterRep IR = new Java.InterRep(assemblyUnit.size());

        Java.Scanner scanner = new Java.Scanner(assemblyUnit);
        Java.Parser parser = new Java.Parser(scanner, IR);
        Java.Listing listing = new Java.Listing(IR);

        String [] ls = listing.getListing();

        String expected = String.format("%1$-5s%2$-5s%3$-14s%4$-14s%5$-6s%6$-14s%7$-20s", "0", "0000", "00", "", "halt", "", "");
        TestListing("Test -Java.Listing Class-", expected, ls[1]);
    }

    public static void TestListing(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}
 */

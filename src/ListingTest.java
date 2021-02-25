import java.util.ArrayList;

public class ListingTest {
    public static void main(String[] args) throws Exception {
        Reader fileContent = null;
        try {
            fileContent = new Reader("testfile.asm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> assemblyUnit = fileContent.getAssemblyUnit();
        InterRep IR = new InterRep(assemblyUnit.size());

        Scanner scanner = new Scanner(assemblyUnit);
        Parser parser = new Parser(scanner, IR);
        Listing listing = new Listing(IR);

        String [] ls = listing.getListing();

        String expected = String.format("%1$-5s%2$-5s%3$-14s%4$-14s%5$-6s%6$-14s%7$-20s", "0", "0000", "00", "", "halt", "", "");
        TestListing("Test -Listing Class-", expected, ls[1]);
    }

    public static void TestListing(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println(testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

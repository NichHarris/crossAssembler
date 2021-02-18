package testing.Scanner;

// TODO: sort out package dependency issues
/*
import src.Assembler;

//Testing File Scanner Functionality
public class ScannerTest {
    public static void main(String[] args) throws Exception {
        //No File Provided
        String[] case1 = {};
        testScan(case1, -3, "No File Provided");

        //Too Many Options
        String[] case2 = {"-l", "-v", "-h", "some.asm"};
        testScan(case2, -2, "Too Many Options");

        //Invalid Option Used
        String[] case3 = {"-l", "-w"};
        testScan(case3, -1, "Invalid Option Used");

        //No Options Enabled
        String[] case4 = {"some.asm"};
        testScan(case4, 0, "No Options Enabled");

        //Help Enabled
        String[] case5 = {"-h", "some.asm"};
        testScan(case5, 1, "Help Enabled");

        //Listing Enabled
        String[] case6 = {"-l", "some.asm"};
        testScan(case6, 2, "Listing Enabled");

        //Verbose Enabled
        String[] case7 = {"-v", "some.asm"};
        testScan(case7, 3, "Verbose Enabled");

        //Listing and Verbose Enabled
        String[] case8 = {"-v", "-l", "some.asm"};
        testScan(case8, 4, "Listing and Verbose Enabled");

        //Invalid Option + Too Many Options + No File Found
        //Invalid Option + Too Many Options
        //Too Many Options + No File Found
        //Invalid Option + No File Found
    }

    public static void testScan(String[] ops, int expected, String caseTitle) throws Exception {
        System.out.println("Test Scanner - " + caseTitle);

        //Scanner Method
        int statusCode = Assembler.Scanner(ops);

        //Actual Contents of File
        int actualCode = expected;

        //Compare toPrint with
        System.out.println(statusCode);
        System.out.println(actualCode);
    }
}
 */
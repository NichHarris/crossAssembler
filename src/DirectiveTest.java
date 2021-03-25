public class DirectiveTest {
    public static void main(String[] args) throws Exception {
        Directive directive = new Directive();
        Directive directive2 = new Directive("directive", "cstring");


        //Testing Default Constructor and getter method
        directiveTester("Default Constructor","",directive.getDir() + directive.getCString());

        //Testing Parameterized Constructor
        directiveTester("Parameterized Constructor","directivec string",directive2.getDir() + " " + directive2.getCString());

        //Testing Setters and Getters
        directive.setCString("newCstring");
        directiveTester("Setter and getter","newCstring",directive.getDir() + directive.getCString());

        //Testing getCode
        directiveTester("getCode","6e 65 77 43 73 74 72 69 6e 67 00",directive.getDir() + directive.getCode());
    }

    public static void directiveTester(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test " + testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

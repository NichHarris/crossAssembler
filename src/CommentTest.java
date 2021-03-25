public class CommentTest {
    public static void main(String[] args) throws Exception {
        Comment comment = new Comment();
        Comment comment2 = new Comment("comment");

        //Testing Default Constructor
        commentTester("-Comment Class- Default constructor and getter method", "", comment.getCmt());

        //Testing Parameterized Constructor
        commentTester("-Comment Class- Parameterized constructor and getter method", "comment", comment2.getCmt());

        //Testing setComment and getComment
        comment.setComment("New Comment");
        commentTester("-Comment Class- setter and getter method", "New Comment", comment.getCmt());
    }

    public static void commentTester(String testCaseName, String expectedOutput, String methodOutput) throws Exception {
        System.out.println("Test " + testCaseName);
        // expected value
        System.out.println(expectedOutput);
        // actual output
        System.out.println(methodOutput);
    }
}

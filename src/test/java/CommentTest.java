package test.java;

import main.interfaces.IComment;
import main.java.Comment;

public class CommentTest {
    public static void main(String[] args) throws Exception {
        TextFileGenerator.textFileGenerator("CommentTest.txt");

        String cmtStr = "Hello World";

        IComment comment_1 = new Comment();
        IComment comment_2 = new Comment(cmtStr);

        //Testing Default Constructor and getComment
        commentTester("-Comment Class- Default constructor and getter method", "", comment_1.getCmt());

        //Testing Parameterized Constructor and getComment
        commentTester("-Comment Class- Parameterized constructor and getter method", cmtStr, comment_2.getCmt());

        //Testing Fail Report
        commentTester("-Comment Class- Parameterized constructor and getter method", "Hello World", comment_2.getCmt());
    }

    public static void commentTester(String testNb, String expected, String actual) throws Exception {
        System.out.println("Test " + testNb);
        try{
            assert expected.equals(actual): testNb+" Failed"; //throw Failed
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        // expected value
        System.out.println(expected);
        // actual output
        System.out.println(actual);
    }
}

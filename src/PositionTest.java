public class PositionTest {
    public static void main(String[] args) throws Exception {
        IPosition p1 = new Position(1, 2);   // (line, col)
        IPosition p2 = new Position(2, 4);
        IPosition p3 = new Position(3, 6);
        IPosition p4 = new Position(65535, 255);

        System.out.println("Test Position");
        System.out.println("p1[1,2]p2[2,4]p3[3,6]p4[65535,255]");

        System.out.print("p1["+p1.getLineNumber()+","+p1.getColumnNumber()+"]");
        System.out.print("p2["+p2.getLineNumber()+","+p2.getColumnNumber()+"]");
        System.out.print("p3["+p3.getLineNumber()+","+p3.getColumnNumber()+"]");
        System.out.print("p4["+p4.getLineNumber()+","+p4.getColumnNumber()+"]");
        System.out.println();
    }
}

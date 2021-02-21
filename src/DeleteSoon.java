public class DeleteSoon {
    public static void main(String[] args) throws Exception {

        Reader fileContent = null;
        try {
            fileContent = new Reader ("sample.asm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] assemblyUnit = fileContent.getAssemblyUnit();
        InterRep IR = new InterRep(assemblyUnit.length);

        //Parse statements into tokens and comments
        Scanner scanner = new Scanner(assemblyUnit);
        Parser parser = new Parser(scanner, IR);
    }
}

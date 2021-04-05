////Main class for the VM Cross Assembler
//public class Main {
//    //Entrypoint of program execution
//    public static void main(String[] args) throws Exception {
//        //Set options from CL
//        IOptions options = new Options();
//        options.setOptions(args);
//
//        //Don't run if help or banner option is enabled
//        if(!options.isBanner() && !options.isHelp() ) {
//            //Get file name
//            String fileName = options.getFile();
//
//            //Create instance of Assembler
//            Assembler assembler = new Assembler(fileName, options);
//
//            //Execute assembler
//            assembler.assemble();
//        }
//    }
//}
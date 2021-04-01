
    Hi everyone!

    I'll be coding with TestTeam for this sprint!
    I've reviewed the code and done some refactoring, hope you like the new organization.
    Below are some notes that I took regarding the code.


    The new General Structure:

            "scr/io/in/" is reserved for the input files (.asm)
            "scr/io/out/" is where the output files (.lst/.bin) are generated.
            "scr/test/files/" is the testing files (.txt)
            "scr/main/interfaces/" is for the interfaces
            "scr/main/java/" is for the base class
            "scr/test/java/" is for test class
    
    Main Refactoring Update:

    - Many setters are now deprecated.
    - addLine(int,String,IInstruction,String) is now deprecated.
    - Some Constructors are now deprecated.
    - Added source path as "pathname" in Reader.java
    - Added destination path as "pathname" in CodeGenerator.java
    - Fixed access modifier (public/protected/private)
    - Added AssemblerTest.java to quickly test the main code and generate the files using the new structure.

    **Refer to RefactoringOutlineDiagram for more detail.**


For the testing team:

    **Note**
    - Avoid using javac or java in the terminal, as it creates duplicate of class files
      You can run any test in Intellij by left-clicking in the menu, on any class.java that contain a main(...) method:
            left-click -> Run 'myClassTest.main()'
       
    - I've noticed that some tests are not actual testing, but "re-implementation" of the class methods.
            Eg. CodeGenTest.java
      This doesn't actually test much, so we need to reimplement those test correctly using the base class methods.

    - Suggestion to accomplish the testing:
        -use class inheritance to access protected methods
        -@Overwrite protected method (Eg.: pathname to = "src/test/files/")

    - Use Interfaces:
        Directive myDir = new Directive();  //Bad
        IDirective myDir = new Directive(); //Good


    I've reviewed all tests quickly to make sure everything compiles well.
    We will still need to go back over each test together, just to make sure they all work properly, and that we use the AUnit.
    
    See you soon!

    Vincent B.
    April 1st, 2021

    *****************************
    ** Testing Tips and tricks **
    *****************************

    Testing two datatype using the build-in java assert methods
    https://www.baeldung.com/java-assert

    Testing two Files such as expectedFile and actualFile.
    Can be done by generating the checksum of each and comparing the checksum results together.
    https://stackoverflow.com/questions/15441315/java-and-hash-algorithm-to-compare-files


 

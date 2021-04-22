@echo off
echo BATCH TESTING CODE
echo COMPILING ALL SRC FILES...
javac ./main/interfaces/*.java
javac ./main/java/*.java

echo COMPILING ALL TEST FILES...
cd ./test/java
javac *.java

cd ../../
echo RUNNING ALL TESTS...
java test.java.AssemblerTest > batchSample.txt
:=====
: REM java BinaryConverterTest >> batchSample.txt
: REM java CodeGenTest >> batchSample.txt
: REM java CommentTest >> batchSample.txt
: REM java DirectiveTest >> batchSample.txt
: REM java ErrorMsgTest >> batchSample.txt
: REM java ErrorReporterTest >> batchSample.txt
: REM java InstructionTest >> batchSample.txt
: REM java InterRepTest >> batchSample.txt
: REM java LineStatementTest >> batchSample.txt
: REM java ListingTest >> batchSample.txt
: REM java MnemonicTest >> batchSample.txt
: REM java OperandTest >> batchSample.txt
: REM java OptionsTest >> batchSample.txt
: REM java ParserTest >> batchSample.txt
: REM java PositionTest >> batchSample.txt
: REM java ReaderTest >> batchSample.txt
: REM java ScannerTest >> batchSample.txt
: REM java SymbolTableTest >> batchSample.txt
: REM java TextFileGenerator >> batchSample.txt
: REM java TokenTest >> batchSample.txt
:====

echo MODIFIED2 DONE TESTS.

cd src
: REM cd ../..
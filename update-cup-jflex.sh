jflex lexer.jflex 
mv Lexer.java src/hr/fer/ppj/lab/Lexer.java 
java -jar java-cup-11a.jar -parser Parser parser.cup
mv Parser.java src/hr/fer/ppj/lab/Parser.java 
mv sym.java src/hr/fer/ppj/lab/sym.java 


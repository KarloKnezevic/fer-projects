package hr.fer.ppj.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * Lexer
 */
%%

%class Lexer
%unicode
%line
%column
%unicode 

%{
  StringBuffer string = new StringBuffer();
  public List<String> constList = new ArrayList<String>();
  public List<String> idnList = new ArrayList<String>();

  String[] fixedLexems = {"break", "case", "char", "const", "continue", "default", "do", 
		  "double", "else", "exit", "float", "for", "if", "int", "long", 
		  "return", "short", "signed", "struct", "switch", "unsigned", "void", 
		  "while", "&&", ">", "<", "==", "<=", ">=", "!=", "&&", "||", "!", "+", 
		  "-", "*", "/", "%", "=", "}", "{", "]", "[", "(", ")", ":", ";", 
		  "\"", "'", ",", "."};
  
  public List<String> fixedList = new ArrayList<String>();
  
  private Token newToken(Token.Type type, String value) {
	  List<String> list = null;

	  if(type.equals(Token.Type.CONST)) {
		  list = constList;
	  } else if (type.equals(Token.Type.IDN)) {
		  list = idnList;
	  } else {
		  list = fixedList;
	  }
	  
	  int i = list.indexOf(value);
	  if(i==-1) {
		  list.add(value);
		  i=list.size();
	  }
	  return new Token(type, i);
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} 

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
CommentContent       = ( [^*] | \*+ [^/*] )*

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*

%state STRING

%%

/* keywords */
<YYINITIAL> "break" | 
"case" | 
"char" | 
"const" | 
"continue" | 
"default" | 
"do" | 
"double" | 
"else" | 
"exit" | 
"float" | 
"for" | 
"if" | 
"int" | 
"long" | 
"return" | 
"short" | 
"signed" | 
"struct" | 
"switch" | 
"unsigned" | 
"void" | 
"while" | 
"&&" | 
">" | 
"<" | 
"==" | 
"<=" | 
">=" | 
"!=" | 
"&&" | 
"||" | 
"!" | 
"+" | 
"-" | 
"*" | 
"/" | 
"%" | 
"=" | 
"}" | 
"{" | 
"]" | 
"[" | 
"(" | 
")" | 
":" | 
";" | 
"'" | 
"," | 
"." { return newToken(Token.Type.KEY, yytext()); }

<YYINITIAL> {
  /* identifiers */ 
  {Identifier}                   { return newToken(Token.Type.IDN, yytext()); }
 
  \"                             { string.setLength(0); yybegin(STRING); }

  /* comments */
  {Comment}                      { /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

<STRING> {
  \"                             { yybegin(YYINITIAL); 
                                   return newToken(Token.Type.CONST, 
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }

  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
}

/* error fallback */
.|\n                             { throw new Error("Illegal character <"+
                                                    yytext()+">"); }


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
%type Token
%function next_token

%{
  public enum ConstType {INT, FLOAT, CHAR, STRING};
  StringBuffer string = new StringBuffer();
  public List<String> constList = new ArrayList<String>(); /* Tablica konstanti */
  public List<ConstType> constTypeList = new ArrayList<ConstType>(); /* Tablica tipova konstanti */
  public List<String> idnList = new ArrayList<String>(); /* Tablica identifikatora */

  public static String[] fixedLexems = {"break", "case", "char", "const", "continue", "default", "do", 
		  "double", "else", "exit", "float", "for", "if", "int", "long", 
		  "return", "short", "signed", "struct", "switch", "unsigned", "void", 
		  "while", "&&", ">", "<", "==", "<=", ">=", "!=", "&&", "||", "!", "+", 
		  "-", "*", "/", "%", "=", "}", "{", "]", "[", "(", ")", ":", ";", 
		  "\"", "'", ",", "."};

  /* Tablica ostalih leksema npr. while, boolean, for itd. */
  public static List<String> fixedList = new ArrayList<String>(); 

  static {
	for(String s : fixedLexems) {
		fixedList.add(s);
	}
  }
  
  private Token newConst(Token.Type type, String value, ConstType constType) {
	  Token t = newToken(type, value);
	  constTypeList.add(t.getPointer(), constType);
	  return t;
  }
  
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
		  i=list.size()-1;
	  }
      Token token = new Token(type, i);
      token.setCol(yycolumn);
      token.setLine(yyline);
	  return token;
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

Identifier = [:letter:] [:jletterdigit:]* 

Type = "void" | "boolean" | "float" | "int" | "char" | "struct"
Flow = "switch" | "case" | "continue" | "default" | "do" | "else" | "for" | "if" | "break" | "while"
OtherKeywords = "#include" | "return"

Operators = "<" | ">" | "==" | "<=" | ">=" | "!=" | "&&" | "||" | "!" | "+" | "-" | "*" | "/" | "%" | "="
Special = "{" | "}" | "[" | "]" | "(" | ")" | ";" | "." | ","

Char = "'" ([^'] | \\') "'"
Integer = ("0" | [1-9][0-9]*)
Float = (0 | [1-9][0-9]*) "." [0-9]*

%state STRING

%%

/* kljucne rijeci */
<YYINITIAL> {Type} |
{Flow} |
{OtherKeywords} |
{Special} |
{Operators} { return newToken(Token.Type.KEY, yytext()); }

/* konstante */
<YYINITIAL> {
	{Char}  	{ return newConst(Token.Type.CONST, yytext(), ConstType.CHAR); }
	{Float} 	{ return newConst(Token.Type.CONST, yytext(), ConstType.FLOAT); }
	{Integer}  	{ return newConst(Token.Type.CONST, yytext(), ConstType.INT); }
}

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
                                   return newConst(Token.Type.CONST, string.toString(), ConstType.STRING); }
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


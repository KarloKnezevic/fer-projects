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
%type Token
%function next_token

%{
  StringBuffer string = new StringBuffer();
  
  public enum ConstType {INT, FLOAT, CHAR, STRING};
  public Table symbolTable;
  public List<Token> tokenList;

  public static String[] krosLexems = {"break", "case", "char", "const", "continue", "default", "do", 
		  "double", "else", "exit", "float", "for", "if", "int", "long", 
		  "return", "short", "signed", "struct", "switch", "unsigned", "void", 
		  "while", "&&", ">", "<", "==", "<=", ">=", "!=", "&&", "||", "!", "+", 
		  "-", "*", "/", "%", "=", "}", "{", "]", "[", "(", ")", ":", ";", 
		  "\"", "'", ",", "."};
  
  private Token newConst(Token.Type type, String value, ConstType constType) {
	  int i = symbolTable.addConstant(value, constType);
      Token token = new Token(type, i);
      token.setCol(yycolumn);
      token.setLine(yyline);
      tokenList.add(token);
	  return token;
  }
  
  private Token newToken(Token.Type type, String value) {
	  int i;
	  if(type.equals(Token.Type.CONST)) {
		  i = symbolTable.addConstant(value, null); // ovo se zapravo nece dogadjati
	  } else if (type.equals(Token.Type.IDN)) {
		  i = symbolTable.addIdentifier(value);
	  } else {
		  i = symbolTable.addKros(value);
	  }
      Token token = new Token(type, i);
      token.setCol(yycolumn);
      token.setLine(yyline);
      tokenList.add(token);
	  return token;
  }

  public Lexer(java.io.InputStream in, Table table, List<Token> tokenList) {
	  this(in);
	  table.initKros(Lexer.krosLexems);
	  symbolTable = table;
	  this.tokenList=tokenList;
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


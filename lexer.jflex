package hr.fer.ppj.lab;

import java.util.List;
import java_cup.runtime.*; 

/**
 * Lexer
 */
%%

%class Lexer
%unicode
%line
%column
/*%type Token*/
%function next_token
%cup

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
      Token token = new Token(type, i, Tools.getSymForConst(constType));
      token.setCol(yycolumn);
      token.setLine(yyline);
      token.constType = constType;
      tokenList.add(token);
      token.setSymbolValue(symbolTable.getConstValue(i)); // postavi atribut value od Symbol razreda
	  return token;
  }
  
  private Token newToken(Token.Type type, String value) {
	  int i;
	  Token token;
	  if(type.equals(Token.Type.CONST)) {
		  return newConst(type, value, null); // ovo se zapravo nece dogadjati
	  } else if (type.equals(Token.Type.IDN)) {
		  i = symbolTable.addIdentifier(value);
		  token = new Token(type, i, Tools.getSymForIdn());
		  token.setSymbolValue(symbolTable.getIdentifierValue(i)); // postavi atribut value od Symbol razreda
	  } else {
		  i = symbolTable.addKros(value);
		  token = new Token(type, i, Tools.getSym(value));
		  token.setSymbolValue(symbolTable.getKrosValue(i)); // postavi atribut value od Symbol razreda
	  }

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

  public int getCol() {
          return yycolumn;
  }

  public int getRow() {
          return yyline;
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

Type = "void" | "boolean" | "float" | "int" | "char" 
Flow = "continue" | "do" | "else" | "for" | "if" | "break" | "while"
OtherKeywords = "include" | "return"

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
.|\n                             { throw new Error("Nepostojeci znak <"+
                                                    yytext()+">"); }


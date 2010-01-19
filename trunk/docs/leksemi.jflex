LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

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

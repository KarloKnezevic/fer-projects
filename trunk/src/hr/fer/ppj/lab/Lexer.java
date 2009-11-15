/* The following code was generated by JFlex 1.4.3 on 15/11/09 21:46 */

package hr.fer.ppj.lab;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*; 

/**
 * Lexer
 */

class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\7\1\3\1\2\1\0\1\3\1\1\16\7\4\0\1\3\1\33"+
    "\1\45\1\32\1\7\1\37\1\35\1\41\1\37\1\37\1\5\1\37"+
    "\1\37\1\37\1\40\1\4\1\43\11\44\1\0\1\37\1\33\1\34"+
    "\1\33\2\0\32\6\1\37\1\42\1\37\1\0\1\7\1\0\1\17"+
    "\1\14\1\23\1\13\1\16\1\21\1\6\1\24\1\12\1\6\1\30"+
    "\1\15\1\6\1\20\1\11\2\6\1\25\1\26\1\22\1\27\1\10"+
    "\1\31\3\6\1\37\1\36\1\37\1\0\41\7\2\0\4\7\4\0"+
    "\1\6\2\0\1\7\7\0\1\6\4\0\1\6\5\0\27\6\1\0"+
    "\37\6\1\0\u013f\6\31\0\162\6\4\0\14\6\16\0\5\6\11\0"+
    "\1\6\21\0\130\7\5\0\23\7\12\0\1\6\13\0\1\6\1\0"+
    "\3\6\1\0\1\6\1\0\24\6\1\0\54\6\1\0\46\6\1\0"+
    "\5\6\4\0\202\6\1\0\4\7\3\0\105\6\1\0\46\6\2\0"+
    "\2\6\6\0\20\6\41\0\46\6\2\0\1\6\7\0\47\6\11\0"+
    "\21\7\1\0\27\7\1\0\3\7\1\0\1\7\1\0\2\7\1\0"+
    "\1\7\13\0\33\6\5\0\3\6\15\0\4\7\14\0\6\7\13\0"+
    "\32\6\5\0\13\6\16\7\7\0\12\7\4\0\2\6\1\7\143\6"+
    "\1\0\1\6\10\7\1\0\6\7\2\6\2\7\1\0\4\7\2\6"+
    "\12\7\3\6\2\0\1\6\17\0\1\7\1\6\1\7\36\6\33\7"+
    "\2\0\3\6\60\0\46\6\13\7\1\6\u014f\0\3\7\66\6\2\0"+
    "\1\7\1\6\20\7\2\0\1\6\4\7\3\0\12\6\2\7\2\0"+
    "\12\7\21\0\3\7\1\0\10\6\2\0\2\6\2\0\26\6\1\0"+
    "\7\6\1\0\1\6\3\0\4\6\2\0\1\7\1\6\7\7\2\0"+
    "\2\7\2\0\3\7\11\0\1\7\4\0\2\6\1\0\3\6\2\7"+
    "\2\0\12\7\2\6\2\7\15\0\3\7\1\0\6\6\4\0\2\6"+
    "\2\0\26\6\1\0\7\6\1\0\2\6\1\0\2\6\1\0\2\6"+
    "\2\0\1\7\1\0\5\7\4\0\2\7\2\0\3\7\13\0\4\6"+
    "\1\0\1\6\7\0\14\7\3\6\14\0\3\7\1\0\11\6\1\0"+
    "\3\6\1\0\26\6\1\0\7\6\1\0\2\6\1\0\5\6\2\0"+
    "\1\7\1\6\10\7\1\0\3\7\1\0\3\7\2\0\1\6\17\0"+
    "\2\6\2\7\2\0\12\7\1\0\1\7\17\0\3\7\1\0\10\6"+
    "\2\0\2\6\2\0\26\6\1\0\7\6\1\0\2\6\1\0\5\6"+
    "\2\0\1\7\1\6\6\7\3\0\2\7\2\0\3\7\10\0\2\7"+
    "\4\0\2\6\1\0\3\6\4\0\12\7\1\0\1\6\20\0\1\7"+
    "\1\6\1\0\6\6\3\0\3\6\1\0\4\6\3\0\2\6\1\0"+
    "\1\6\1\0\2\6\3\0\2\6\3\0\3\6\3\0\10\6\1\0"+
    "\3\6\4\0\5\7\3\0\3\7\1\0\4\7\11\0\1\7\17\0"+
    "\11\7\11\0\1\7\7\0\3\7\1\0\10\6\1\0\3\6\1\0"+
    "\27\6\1\0\12\6\1\0\5\6\4\0\7\7\1\0\3\7\1\0"+
    "\4\7\7\0\2\7\11\0\2\6\4\0\12\7\22\0\2\7\1\0"+
    "\10\6\1\0\3\6\1\0\27\6\1\0\12\6\1\0\5\6\2\0"+
    "\1\7\1\6\7\7\1\0\3\7\1\0\4\7\7\0\2\7\7\0"+
    "\1\6\1\0\2\6\4\0\12\7\22\0\2\7\1\0\10\6\1\0"+
    "\3\6\1\0\27\6\1\0\20\6\4\0\6\7\2\0\3\7\1\0"+
    "\4\7\11\0\1\7\10\0\2\6\4\0\12\7\22\0\2\7\1\0"+
    "\22\6\3\0\30\6\1\0\11\6\1\0\1\6\2\0\7\6\3\0"+
    "\1\7\4\0\6\7\1\0\1\7\1\0\10\7\22\0\2\7\15\0"+
    "\60\6\1\7\2\6\7\7\4\0\1\7\7\6\10\7\1\0\12\7"+
    "\47\0\2\6\1\0\1\6\2\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\4\6\1\0\7\6\1\0\3\6\1\0\1\6\1\0\1\6"+
    "\2\0\2\6\1\0\4\6\1\7\2\6\6\7\1\0\2\7\1\6"+
    "\2\0\5\6\1\0\1\6\1\0\6\7\2\0\12\7\2\0\2\6"+
    "\42\0\1\6\27\0\2\7\6\0\12\7\13\0\1\7\1\0\1\7"+
    "\1\0\1\7\4\0\2\7\10\6\1\0\42\6\6\0\24\7\1\0"+
    "\2\7\4\6\4\0\10\7\1\0\44\7\11\0\1\7\71\0\42\6"+
    "\1\0\5\6\1\0\2\6\1\0\7\7\3\0\4\7\6\0\12\7"+
    "\6\0\6\6\4\7\106\0\46\6\12\0\51\6\7\0\132\6\5\0"+
    "\104\6\5\0\122\6\6\0\7\6\1\0\77\6\1\0\1\6\1\0"+
    "\4\6\2\0\7\6\1\0\1\6\1\0\4\6\2\0\47\6\1\0"+
    "\1\6\1\0\4\6\2\0\37\6\1\0\1\6\1\0\4\6\2\0"+
    "\7\6\1\0\1\6\1\0\4\6\2\0\7\6\1\0\7\6\1\0"+
    "\27\6\1\0\37\6\1\0\1\6\1\0\4\6\2\0\7\6\1\0"+
    "\47\6\1\0\23\6\16\0\11\7\56\0\125\6\14\0\u026c\6\2\0"+
    "\10\6\12\0\32\6\5\0\113\6\3\0\3\7\17\0\15\6\1\0"+
    "\4\6\3\7\13\0\22\6\3\7\13\0\22\6\2\7\14\0\15\6"+
    "\1\0\3\6\1\0\2\7\14\0\64\6\40\7\3\0\1\6\3\0"+
    "\1\7\1\6\1\7\2\0\12\7\41\0\3\7\2\0\12\7\6\0"+
    "\130\6\10\0\51\6\1\7\126\0\35\6\3\0\14\7\4\0\14\7"+
    "\12\0\12\7\36\6\2\0\5\6\u038b\0\154\6\224\0\234\6\4\0"+
    "\132\6\6\0\26\6\2\0\6\6\2\0\46\6\2\0\6\6\2\0"+
    "\10\6\1\0\1\6\1\0\1\6\1\0\1\6\1\0\37\6\2\0"+
    "\65\6\1\0\7\6\1\0\1\6\3\0\3\6\1\0\7\6\3\0"+
    "\4\6\2\0\6\6\4\0\15\6\5\0\3\6\1\0\7\6\17\0"+
    "\4\7\32\0\5\7\20\0\2\7\23\0\1\7\13\0\4\7\6\0"+
    "\6\7\1\0\1\6\15\0\1\6\40\0\22\7\36\0\15\7\4\0"+
    "\1\7\3\0\6\7\27\0\1\6\4\0\1\6\2\0\12\6\1\0"+
    "\1\6\3\0\5\6\6\0\1\6\1\0\1\6\1\0\1\6\1\0"+
    "\4\6\1\0\3\6\1\0\7\6\3\0\3\6\5\0\5\6\26\0"+
    "\44\7\u0e81\0\2\6\1\7\31\0\17\7\1\0\5\6\2\0\3\7"+
    "\2\6\4\0\126\6\2\0\2\7\2\0\3\6\1\0\132\6\1\7"+
    "\4\6\5\0\50\6\4\0\136\6\21\0\30\6\70\0\20\6\u0200\0"+
    "\u19b6\6\112\0\u51a6\6\132\0\u048d\6\u0773\0\u2ba4\6\u215c\0\u012e\6\2\0"+
    "\73\6\225\0\7\6\14\0\5\6\5\0\1\6\1\7\12\6\1\0"+
    "\15\6\1\0\5\6\1\0\1\6\1\0\2\6\1\0\2\6\1\0"+
    "\154\6\41\0\u016b\6\22\0\100\6\2\0\66\6\50\0\14\6\1\7"+
    "\3\0\20\7\20\0\4\7\17\0\2\7\30\0\3\7\31\0\1\7"+
    "\6\0\5\6\1\0\207\6\2\0\1\7\4\0\1\7\13\0\12\7"+
    "\7\0\32\6\4\0\1\7\1\0\32\6\12\0\1\7\131\6\3\0"+
    "\6\6\2\0\6\6\2\0\6\6\2\0\3\6\3\0\2\7\3\0"+
    "\2\7\22\0\3\7\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\2\3\13\4\1\1\1\3\3\1"+
    "\2\5\1\6\1\7\1\3\11\7\1\3\2\7\1\10"+
    "\1\11\2\0\2\4\1\3\12\4\3\0\1\12\7\7"+
    "\1\13\1\14\1\15\1\16\2\0\11\4\1\0\2\17"+
    "\6\7\1\0\5\4\1\0\4\7\3\4\1\0\3\7"+
    "\1\4\1\0\2\7\1\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\46\0\114\0\162\0\114\0\230\0\114\0\276"+
    "\0\344\0\u010a\0\u0130\0\u0156\0\u017c\0\u01a2\0\u01c8\0\u01ee"+
    "\0\u0214\0\u023a\0\u0260\0\u0286\0\u02ac\0\u02d2\0\u02f8\0\u031e"+
    "\0\u0344\0\114\0\u036a\0\u036a\0\u0390\0\u03b6\0\u03dc\0\u0402"+
    "\0\u0428\0\u044e\0\u0474\0\u049a\0\u04c0\0\u04e6\0\u050c\0\u0532"+
    "\0\u0558\0\114\0\u057e\0\u05a4\0\u05ca\0\u05f0\0\276\0\u0616"+
    "\0\u063c\0\u0662\0\u0688\0\u06ae\0\u06d4\0\u06fa\0\u0720\0\u0746"+
    "\0\u076c\0\u0792\0\u07b8\0\u07de\0\u0804\0\u082a\0\u0850\0\u0876"+
    "\0\u089c\0\u08c2\0\u08e8\0\u090e\0\114\0\114\0\114\0\114"+
    "\0\u0934\0\u095a\0\u0980\0\u09a6\0\u09cc\0\u09f2\0\u0a18\0\u0a3e"+
    "\0\u0a64\0\u0a8a\0\u0ab0\0\u0ad6\0\114\0\u07b8\0\u0afc\0\u0b22"+
    "\0\u0b48\0\u0b6e\0\u0b94\0\u0bba\0\u0be0\0\u0c06\0\u0c2c\0\u0c52"+
    "\0\u0c78\0\u0c9e\0\u0cc4\0\u0cea\0\u0d10\0\u0d36\0\u0d5c\0\u0d82"+
    "\0\u0da8\0\u0dce\0\u0df4\0\u0e1a\0\u0e40\0\u0e66\0\u0e8c\0\u0eb2"+
    "\0\u0ed8\0\u0efe\0\u0f24";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\2\5\1\6\1\7\1\10\1\3\1\11"+
    "\1\10\1\12\1\13\1\14\1\10\1\15\2\10\1\16"+
    "\1\10\1\17\1\10\1\20\1\21\2\10\1\22\1\23"+
    "\2\24\1\25\1\26\2\7\1\27\1\3\1\30\1\31"+
    "\1\32\1\33\2\3\1\33\2\34\4\33\1\35\1\36"+
    "\1\37\1\33\1\40\2\33\1\41\1\33\1\42\1\33"+
    "\1\43\3\33\1\44\1\45\2\46\1\47\1\50\2\34"+
    "\1\33\1\51\2\33\1\52\50\0\1\5\47\0\1\53"+
    "\1\54\46\0\24\10\11\0\2\10\7\0\3\10\1\55"+
    "\20\10\11\0\2\10\7\0\12\10\1\56\1\57\10\10"+
    "\11\0\2\10\7\0\3\10\1\57\20\10\11\0\2\10"+
    "\7\0\3\10\1\60\13\10\1\61\4\10\11\0\2\10"+
    "\7\0\7\10\1\62\14\10\11\0\2\10\7\0\3\10"+
    "\1\63\3\10\1\64\14\10\11\0\2\10\7\0\3\10"+
    "\1\65\12\10\1\66\5\10\11\0\2\10\7\0\10\10"+
    "\1\67\13\10\11\0\2\10\7\0\14\10\1\70\7\10"+
    "\11\0\2\10\7\0\16\10\1\71\5\10\11\0\2\10"+
    "\13\0\1\72\67\0\1\7\46\0\1\7\46\0\1\7"+
    "\7\0\41\73\1\0\1\74\3\73\40\0\1\75\45\0"+
    "\1\75\2\0\2\31\1\0\1\33\2\0\37\33\1\0"+
    "\2\33\1\0\1\33\2\0\16\33\1\34\20\33\1\0"+
    "\2\33\1\0\1\33\2\0\6\33\1\34\30\33\1\0"+
    "\2\33\1\0\1\33\2\0\22\33\1\76\14\33\1\0"+
    "\2\33\1\0\1\33\2\0\12\33\1\77\24\33\1\0"+
    "\2\33\1\0\1\33\2\0\6\33\1\100\30\33\1\0"+
    "\2\33\1\0\1\33\2\0\6\33\1\101\30\33\1\0"+
    "\2\33\1\0\1\33\2\0\13\33\1\102\23\33\1\0"+
    "\2\33\1\0\1\33\2\0\21\33\1\103\15\33\1\0"+
    "\2\33\1\0\1\33\2\0\7\33\1\104\27\33\1\0"+
    "\2\33\1\0\1\33\2\0\31\33\1\34\5\33\1\0"+
    "\2\33\1\0\1\33\2\0\32\33\1\34\4\33\1\0"+
    "\2\33\1\0\1\33\2\0\33\33\1\34\3\33\1\0"+
    "\2\33\21\0\1\105\1\0\1\106\2\0\1\107\17\0"+
    "\1\110\1\53\1\4\1\5\43\53\5\111\1\112\40\111"+
    "\6\0\4\10\1\113\17\10\11\0\2\10\7\0\14\10"+
    "\1\57\7\10\11\0\2\10\7\0\3\10\1\114\20\10"+
    "\11\0\2\10\7\0\10\10\1\115\13\10\11\0\2\10"+
    "\7\0\20\10\1\116\3\10\11\0\2\10\7\0\17\10"+
    "\1\57\4\10\11\0\2\10\7\0\3\10\1\117\20\10"+
    "\11\0\2\10\7\0\12\10\1\120\11\10\11\0\2\10"+
    "\7\0\11\10\1\63\12\10\11\0\2\10\7\0\14\10"+
    "\1\121\7\10\11\0\2\10\7\0\17\10\1\122\4\10"+
    "\11\0\2\10\7\0\4\10\1\123\17\10\11\0\2\10"+
    "\21\0\1\124\66\0\1\125\45\0\1\126\47\0\2\75"+
    "\1\0\1\33\2\0\13\33\1\127\23\33\1\0\2\33"+
    "\1\0\1\33\2\0\23\33\1\130\13\33\1\0\2\33"+
    "\1\0\1\33\2\0\22\33\1\34\14\33\1\0\2\33"+
    "\1\0\1\33\2\0\15\33\1\131\21\33\1\0\2\33"+
    "\1\0\1\33\2\0\17\33\1\132\17\33\1\0\2\33"+
    "\1\0\1\33\2\0\7\33\1\133\27\33\1\0\2\33"+
    "\1\0\1\33\2\0\15\33\1\134\21\33\1\0\2\33"+
    "\1\0\5\111\1\135\40\111\4\0\1\5\1\112\46\0"+
    "\5\10\1\57\16\10\11\0\2\10\7\0\7\10\1\136"+
    "\14\10\11\0\2\10\7\0\11\10\1\137\12\10\11\0"+
    "\2\10\7\0\10\10\1\57\13\10\11\0\2\10\7\0"+
    "\11\10\1\56\12\10\11\0\2\10\7\0\14\10\1\140"+
    "\7\10\11\0\2\10\7\0\21\10\1\141\2\10\11\0"+
    "\2\10\7\0\21\10\1\142\2\10\11\0\2\10\7\0"+
    "\7\10\1\116\14\10\11\0\2\10\24\0\1\143\22\0"+
    "\1\33\2\0\14\33\1\144\22\33\1\0\2\33\1\0"+
    "\1\33\2\0\13\33\1\34\23\33\1\0\2\33\1\0"+
    "\1\33\2\0\17\33\1\145\17\33\1\0\2\33\1\0"+
    "\1\33\2\0\24\33\1\146\12\33\1\0\2\33\1\0"+
    "\1\33\2\0\12\33\1\130\24\33\1\0\2\33\1\0"+
    "\1\33\2\0\20\33\1\147\16\33\1\0\2\33\1\0"+
    "\4\111\1\5\1\135\40\111\6\0\10\10\1\150\13\10"+
    "\11\0\2\10\7\0\22\10\1\57\1\10\11\0\2\10"+
    "\7\0\4\10\1\151\17\10\11\0\2\10\7\0\17\10"+
    "\1\152\4\10\11\0\2\10\7\0\15\10\1\56\6\10"+
    "\11\0\2\10\16\0\1\153\30\0\1\33\2\0\25\33"+
    "\1\34\11\33\1\0\2\33\1\0\1\33\2\0\7\33"+
    "\1\154\27\33\1\0\2\33\1\0\1\33\2\0\22\33"+
    "\1\155\14\33\1\0\2\33\1\0\1\33\2\0\12\33"+
    "\1\156\24\33\1\0\2\33\7\0\11\10\1\152\12\10"+
    "\11\0\2\10\7\0\12\10\1\157\11\10\11\0\2\10"+
    "\7\0\12\10\1\57\11\10\11\0\2\10\30\0\1\160"+
    "\16\0\1\33\2\0\15\33\1\161\21\33\1\0\2\33"+
    "\1\0\1\33\2\0\15\33\1\34\21\33\1\0\2\33"+
    "\1\0\1\33\2\0\24\33\1\162\12\33\1\0\2\33"+
    "\7\0\21\10\1\116\2\10\11\0\2\10\14\0\1\163"+
    "\32\0\1\33\2\0\24\33\1\130\12\33\1\0\2\33"+
    "\1\0\1\33\2\0\10\33\1\130\26\33\1\0\2\33"+
    "\17\0\1\7\27\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3914];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\1\1\1\11\1\1\1\11\22\1\1\11"+
    "\17\1\1\11\2\0\15\1\3\0\10\1\4\11\2\0"+
    "\11\1\1\0\1\11\7\1\1\0\5\1\1\0\7\1"+
    "\1\0\4\1\1\0\2\1\1\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
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



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 1768) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 12: 
          { string.append('\t');
          }
        case 16: break;
        case 10: 
          { return newConst(Token.Type.CONST, yytext(), ConstType.FLOAT);
          }
        case 17: break;
        case 1: 
          { throw new Error("Illegal character <"+
                                                    yytext()+">");
          }
        case 18: break;
        case 15: 
          { return newConst(Token.Type.CONST, yytext(), ConstType.CHAR);
          }
        case 19: break;
        case 5: 
          { return newConst(Token.Type.CONST, yytext(), ConstType.INT);
          }
        case 20: break;
        case 4: 
          { return newToken(Token.Type.IDN, yytext());
          }
        case 21: break;
        case 2: 
          { /* ignore */
          }
        case 22: break;
        case 8: 
          { string.append('\\');
          }
        case 23: break;
        case 14: 
          { string.append('\"');
          }
        case 24: break;
        case 9: 
          { yybegin(YYINITIAL); 
                                   return newConst(Token.Type.CONST, string.toString(), ConstType.STRING);
          }
        case 25: break;
        case 13: 
          { string.append('\r');
          }
        case 26: break;
        case 11: 
          { string.append('\n');
          }
        case 27: break;
        case 3: 
          { return newToken(Token.Type.KEY, yytext());
          }
        case 28: break;
        case 7: 
          { string.append( yytext() );
          }
        case 29: break;
        case 6: 
          { string.setLength(0); yybegin(STRING);
          }
        case 30: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}

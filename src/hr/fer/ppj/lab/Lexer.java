package hr.fer.ppj.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * Lexer
 */

class Lexer {

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
    "\1\44\1\32\1\7\1\37\1\35\1\40\1\37\1\37\1\5\1\37"+
    "\1\37\1\37\1\37\1\4\1\43\11\42\1\0\1\37\1\33\1\34"+
    "\1\33\2\0\32\6\1\37\1\41\1\37\1\0\1\7\1\0\1\17"+
    "\1\14\1\23\1\13\1\16\1\21\1\6\1\24\1\12\1\6\1\31"+
    "\1\15\1\6\1\20\1\11\2\6\1\25\1\26\1\22\1\27\1\10"+
    "\1\30\3\6\1\37\1\36\1\37\1\0\41\7\2\0\4\7\4\0"+
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
    "\1\5\1\1\1\6\1\7\1\3\12\7\1\3\2\7"+
    "\1\10\1\5\1\7\1\11\2\0\2\4\1\3\14\4"+
    "\3\0\1\5\11\7\1\12\1\13\1\14\1\15\1\5"+
    "\2\0\13\4\1\0\2\5\10\7\1\0\7\4\1\0"+
    "\6\7\5\4\1\0\5\7\1\4\1\0\3\7\1\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[136];
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
    "\0\0\0\45\0\112\0\157\0\112\0\224\0\112\0\271"+
    "\0\336\0\u0103\0\u0128\0\u014d\0\u0172\0\u0197\0\u01bc\0\u01e1"+
    "\0\u0206\0\u022b\0\u0250\0\u0275\0\u029a\0\u02bf\0\u02e4\0\u0309"+
    "\0\u032e\0\112\0\u0353\0\u0353\0\u0378\0\u039d\0\u03c2\0\u03e7"+
    "\0\u040c\0\u0431\0\u0456\0\u047b\0\u04a0\0\u04c5\0\u04ea\0\u050f"+
    "\0\u0534\0\u0559\0\u057e\0\u05a3\0\112\0\u05c8\0\u05ed\0\u0612"+
    "\0\u0637\0\271\0\u065c\0\u0681\0\u06a6\0\u06cb\0\u06f0\0\u0715"+
    "\0\u073a\0\u075f\0\u0784\0\u07a9\0\u07ce\0\u07f3\0\u0818\0\u083d"+
    "\0\u0862\0\u0887\0\u08ac\0\u08d1\0\u08f6\0\u091b\0\u0940\0\u0965"+
    "\0\u098a\0\u09af\0\u09d4\0\112\0\112\0\112\0\112\0\u09f9"+
    "\0\u0a1e\0\u0a43\0\u0a68\0\u0a8d\0\u0ab2\0\u0ad7\0\u0afc\0\u0b21"+
    "\0\u0b46\0\u0b6b\0\u0b90\0\u0bb5\0\u0bda\0\u0bff\0\112\0\u083d"+
    "\0\u0c24\0\u0c49\0\u0c6e\0\u0c93\0\u0cb8\0\u0cdd\0\u0d02\0\u0d27"+
    "\0\u0d4c\0\u0d71\0\u0d96\0\u0dbb\0\u0de0\0\u0e05\0\u0e2a\0\u0e4f"+
    "\0\u0e74\0\u0e99\0\u0ebe\0\u0ee3\0\u0f08\0\u0f2d\0\u0f52\0\u0f77"+
    "\0\u0f9c\0\u0fc1\0\u0fe6\0\u100b\0\u1030\0\u1055\0\u107a\0\u109f"+
    "\0\u10c4\0\u10e9\0\u110e\0\u1133\0\u1158\0\u117d\0\u11a2\0\u11c7";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[136];
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
    "\1\10\1\17\1\10\1\20\1\21\1\10\1\22\1\10"+
    "\1\23\2\24\1\25\1\26\1\7\1\27\1\3\1\30"+
    "\1\31\1\32\1\33\2\3\1\33\2\34\4\33\1\35"+
    "\1\36\1\37\1\33\1\40\2\33\1\41\1\33\1\42"+
    "\1\33\1\43\1\44\1\33\1\45\1\33\1\46\2\47"+
    "\1\50\1\51\1\34\1\33\1\52\1\53\1\54\1\55"+
    "\47\0\1\5\46\0\1\56\1\57\45\0\24\10\10\0"+
    "\2\10\7\0\3\10\1\60\20\10\10\0\2\10\7\0"+
    "\12\10\1\61\1\62\10\10\10\0\2\10\7\0\3\10"+
    "\1\62\4\10\1\63\13\10\10\0\2\10\7\0\3\10"+
    "\1\64\13\10\1\65\4\10\10\0\2\10\7\0\7\10"+
    "\1\66\14\10\10\0\2\10\7\0\3\10\1\67\3\10"+
    "\1\70\14\10\10\0\2\10\7\0\3\10\1\71\5\10"+
    "\1\66\4\10\1\72\5\10\10\0\2\10\7\0\10\10"+
    "\1\73\13\10\10\0\2\10\7\0\14\10\1\74\5\10"+
    "\1\75\1\10\10\0\2\10\7\0\16\10\1\76\5\10"+
    "\10\0\2\10\13\0\1\77\66\0\1\7\45\0\1\7"+
    "\45\0\1\7\6\0\40\100\1\0\1\101\3\100\2\102"+
    "\1\0\37\102\2\30\3\102\1\0\42\102\1\33\2\0"+
    "\36\33\1\0\2\33\1\0\1\33\2\0\16\33\1\34"+
    "\17\33\1\0\2\33\1\0\1\33\2\0\6\33\1\34"+
    "\4\33\1\103\22\33\1\0\2\33\1\0\1\33\2\0"+
    "\22\33\1\104\13\33\1\0\2\33\1\0\1\33\2\0"+
    "\12\33\1\105\23\33\1\0\2\33\1\0\1\33\2\0"+
    "\6\33\1\106\27\33\1\0\2\33\1\0\1\33\2\0"+
    "\6\33\1\107\5\33\1\105\21\33\1\0\2\33\1\0"+
    "\1\33\2\0\13\33\1\110\22\33\1\0\2\33\1\0"+
    "\1\33\2\0\25\33\1\111\10\33\1\0\2\33\1\0"+
    "\1\33\2\0\21\33\1\112\14\33\1\0\2\33\1\0"+
    "\1\33\2\0\7\33\1\113\26\33\1\0\2\33\1\0"+
    "\1\33\2\0\31\33\1\34\4\33\1\0\2\33\1\0"+
    "\1\33\2\0\32\33\1\34\3\33\1\0\2\33\1\0"+
    "\1\33\2\0\33\33\1\34\2\33\1\0\2\33\21\0"+
    "\1\114\1\0\1\115\2\0\1\116\16\0\1\117\1\120"+
    "\1\102\1\0\36\120\1\102\2\53\1\102\1\120\1\102"+
    "\1\0\36\120\1\102\2\120\1\102\1\56\1\4\1\5"+
    "\42\56\5\121\1\122\37\121\6\0\4\10\1\123\17\10"+
    "\10\0\2\10\7\0\14\10\1\62\7\10\10\0\2\10"+
    "\7\0\13\10\1\124\10\10\10\0\2\10\7\0\3\10"+
    "\1\125\20\10\10\0\2\10\7\0\10\10\1\126\13\10"+
    "\10\0\2\10\7\0\20\10\1\127\3\10\10\0\2\10"+
    "\7\0\17\10\1\62\4\10\10\0\2\10\7\0\3\10"+
    "\1\130\20\10\10\0\2\10\7\0\12\10\1\131\11\10"+
    "\10\0\2\10\7\0\11\10\1\67\12\10\10\0\2\10"+
    "\7\0\14\10\1\132\7\10\10\0\2\10\7\0\17\10"+
    "\1\133\4\10\10\0\2\10\7\0\4\10\1\134\17\10"+
    "\10\0\2\10\7\0\4\10\1\135\17\10\10\0\2\10"+
    "\21\0\1\136\64\0\1\137\44\0\1\140\46\0\2\102"+
    "\1\0\1\33\2\0\16\33\1\141\17\33\1\0\2\33"+
    "\1\0\1\33\2\0\13\33\1\142\22\33\1\0\2\33"+
    "\1\0\1\33\2\0\23\33\1\143\12\33\1\0\2\33"+
    "\1\0\1\33\2\0\22\33\1\34\13\33\1\0\2\33"+
    "\1\0\1\33\2\0\15\33\1\144\20\33\1\0\2\33"+
    "\1\0\1\33\2\0\17\33\1\145\16\33\1\0\2\33"+
    "\1\0\1\33\2\0\7\33\1\146\26\33\1\0\2\33"+
    "\1\0\1\33\2\0\7\33\1\147\26\33\1\0\2\33"+
    "\1\0\1\33\2\0\15\33\1\150\20\33\1\0\2\33"+
    "\1\0\1\33\2\0\36\33\1\0\2\120\1\0\5\121"+
    "\1\151\37\121\4\0\1\5\1\122\45\0\5\10\1\62"+
    "\16\10\10\0\2\10\7\0\11\10\1\152\12\10\10\0"+
    "\2\10\7\0\7\10\1\153\14\10\10\0\2\10\7\0"+
    "\11\10\1\154\12\10\10\0\2\10\7\0\10\10\1\62"+
    "\13\10\10\0\2\10\7\0\11\10\1\61\12\10\10\0"+
    "\2\10\7\0\14\10\1\155\7\10\10\0\2\10\7\0"+
    "\21\10\1\156\2\10\10\0\2\10\7\0\21\10\1\157"+
    "\2\10\10\0\2\10\7\0\14\10\1\160\7\10\10\0"+
    "\2\10\7\0\7\10\1\127\14\10\10\0\2\10\24\0"+
    "\1\161\21\0\1\33\2\0\14\33\1\162\21\33\1\0"+
    "\2\33\1\0\1\33\2\0\14\33\1\163\21\33\1\0"+
    "\2\33\1\0\1\33\2\0\13\33\1\34\22\33\1\0"+
    "\2\33\1\0\1\33\2\0\17\33\1\164\16\33\1\0"+
    "\2\33\1\0\1\33\2\0\24\33\1\165\11\33\1\0"+
    "\2\33\1\0\1\33\2\0\17\33\1\166\16\33\1\0"+
    "\2\33\1\0\1\33\2\0\12\33\1\143\23\33\1\0"+
    "\2\33\1\0\1\33\2\0\20\33\1\167\15\33\1\0"+
    "\2\33\1\0\4\121\1\5\1\151\37\121\6\0\21\10"+
    "\1\170\2\10\10\0\2\10\7\0\10\10\1\171\13\10"+
    "\10\0\2\10\7\0\23\10\1\62\10\0\2\10\7\0"+
    "\4\10\1\172\17\10\10\0\2\10\7\0\17\10\1\173"+
    "\4\10\10\0\2\10\7\0\15\10\1\61\6\10\10\0"+
    "\2\10\7\0\15\10\1\174\6\10\10\0\2\10\16\0"+
    "\1\175\27\0\1\33\2\0\24\33\1\176\11\33\1\0"+
    "\2\33\1\0\1\33\2\0\26\33\1\34\7\33\1\0"+
    "\2\33\1\0\1\33\2\0\7\33\1\177\26\33\1\0"+
    "\2\33\1\0\1\33\2\0\22\33\1\200\13\33\1\0"+
    "\2\33\1\0\1\33\2\0\20\33\1\201\15\33\1\0"+
    "\2\33\1\0\1\33\2\0\12\33\1\202\23\33\1\0"+
    "\2\33\7\0\7\10\1\61\14\10\10\0\2\10\7\0"+
    "\11\10\1\173\12\10\10\0\2\10\7\0\12\10\1\203"+
    "\11\10\10\0\2\10\7\0\12\10\1\62\11\10\10\0"+
    "\2\10\7\0\16\10\1\62\5\10\10\0\2\10\30\0"+
    "\1\204\15\0\1\33\2\0\12\33\1\205\23\33\1\0"+
    "\2\33\1\0\1\33\2\0\15\33\1\206\20\33\1\0"+
    "\2\33\1\0\1\33\2\0\15\33\1\34\20\33\1\0"+
    "\2\33\1\0\1\33\2\0\21\33\1\34\14\33\1\0"+
    "\2\33\1\0\1\33\2\0\24\33\1\207\11\33\1\0"+
    "\2\33\7\0\21\10\1\127\2\10\10\0\2\10\14\0"+
    "\1\210\31\0\1\33\2\0\17\33\1\34\16\33\1\0"+
    "\2\33\1\0\1\33\2\0\24\33\1\143\11\33\1\0"+
    "\2\33\1\0\1\33\2\0\10\33\1\143\25\33\1\0"+
    "\2\33\17\0\1\7\26\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4588];
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
    "\22\1\1\11\2\0\17\1\3\0\12\1\4\11\1\1"+
    "\2\0\13\1\1\0\1\11\11\1\1\0\7\1\1\0"+
    "\13\1\1\0\6\1\1\0\3\1\1\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[136];
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
  public List<String> constList = new ArrayList<String>();
  public List<String> idnList = new ArrayList<String>();

  public static String[] fixedLexems = {"break", "case", "char", "const", "continue", "default", "do", 
		  "double", "else", "exit", "float", "for", "if", "int", "long", 
		  "return", "short", "signed", "struct", "switch", "unsigned", "void", 
		  "while", "&&", ">", "<", "==", "<=", ">=", "!=", "&&", "||", "!", "+", 
		  "-", "*", "/", "%", "=", "}", "{", "]", "[", "(", ")", ":", ";", 
		  "\"", "'", ",", "."};

  public static List<String> fixedList = new ArrayList<String>();

  static {
	for(String s : fixedLexems) {
		fixedList.add(s);
	}
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
		  i=list.size();
	  }
          Token token = new Token(type, i);
          token.setCol(yycolumn);
          token.setLine(yyline);
	  return new Token(type, i);
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
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Token next_token() throws java.io.IOException {
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
        case 11: 
          { string.append('\t');
          }
        case 14: break;
        case 1: 
          { throw new Error("Illegal character <"+
                                                    yytext()+">");
          }
        case 15: break;
        case 4: 
          { return newToken(Token.Type.IDN, yytext());
          }
        case 16: break;
        case 2: 
          { /* ignore */
          }
        case 17: break;
        case 8: 
          { string.append('\\');
          }
        case 18: break;
        case 13: 
          { string.append('\"');
          }
        case 19: break;
        case 12: 
          { string.append('\r');
          }
        case 20: break;
        case 10: 
          { string.append('\n');
          }
        case 21: break;
        case 3: 
          { return newToken(Token.Type.KEY, yytext());
          }
        case 22: break;
        case 7: 
          { string.append( yytext() );
          }
        case 23: break;
        case 6: 
          { string.setLength(0); yybegin(STRING);
          }
        case 24: break;
        case 5: 
          { return newToken(Token.Type.CONST, yytext());
          }
        case 25: break;
        case 9: 
          { yybegin(YYINITIAL); 
                                   return newToken(Token.Type.CONST, 
                                   string.toString());
          }
        case 26: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
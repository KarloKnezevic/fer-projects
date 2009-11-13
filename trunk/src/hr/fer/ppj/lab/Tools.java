package hr.fer.ppj.lab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.TableModel;

public class Tools {

	public static TableModel getTableModel(List<Token> listaTokena, Table table) {
		String[][] matrica = new String[listaTokena.size()][5];
		int br=0;
		for(Token t : listaTokena) {
			matrica[br][0] = t.getType().toString(); // tip
			matrica[br][1] = String.valueOf(t.getPointer()); // pointer
			matrica[br][3] = String.valueOf(t.getLine()); // line
			matrica[br][4] = String.valueOf(t.getCol()); // column
			
			// tip
			if(t.getType() == Token.Type.CONST) {
				matrica[br][2] = table.constList.get(t.getPointer()) + " ("
				         + table.constTypeList.get(t.getPointer()).toString() + ")";
			} else if (t.getType() == Token.Type.IDN) {
				matrica[br][2] = table.idnList.get(t.getPointer());
			} else {
				matrica[br][2] = table.krosList.get(t.getPointer());
			}

			br++;
		}
		
		TableModel model = new javax.swing.table.DefaultTableModel(matrica,
            new String [] {
                "Tip", "Pointer", "Vrijednost", "Line", "Column"
        });
		
		return model;
	}
	
	public static Map<String, Integer> symMap = new HashMap<String, Integer>();
	static {
		int[] symList = {
				sym.PLUS, sym.MINUS, sym.MUL, sym.DIV, sym.MOD, sym.AND, sym.OR, sym.ASSINGMENT,
				sym.EQUAL, sym.LESS, sym.GREATER, sym.LEQ, sym.GEQ, sym.NOTEQ, sym.NOT,
				sym.INT, sym.CHAR, sym.FLOAT, sym.BOOLEAN, sym.STRUCT, sym.VOID,
				sym.IF, sym.ELSE,
				sym.DO, sym.WHILE, sym.FOR, sym.BREAK, sym.CONTINUE,
				sym.ZAREZ, sym.TOCKA, sym.TOCKAZAREZ,
				sym.OBLA_D, sym.OBLA_L, sym.UGLATA_D, sym.UGLATA_L, sym.VITICASTA_D, sym.VITICASTA_L
				};
		String[] valueList = {
				"+", "-", "*", "/", "%", "&&", "||", "=",
				"==", "<", ">", "<=", ">=", "!=", "!",
				"int", "char", "float", "boolean", "struct", "void",
				"if", "else",
				"do", "while", "for", "break", "continue",
				",", ".", ";",
				"(", ")", "[", "]", "{", "}"
				};
		for(int i=0; i<valueList.length; i++) {
			symMap.put(valueList[i], symList[i]);
		}
	}
	
	public static int getSym(String value) {
		return symMap.get(value);
	}
	
	public static int getSymForConst(Lexer.ConstType type) {
		if(type == Lexer.ConstType.CHAR) {
			return sym.CONST_CHAR;
		}
		if(type == Lexer.ConstType.FLOAT) {
			return sym.CONST_FLOAT;
		}
		if(type == Lexer.ConstType.INT) {
			return sym.CONST_INT;
		}
		if(type == Lexer.ConstType.STRING) {
			return sym.CONST_STR;
		}
		return -1;
	}
	
	public static int getSymForIdn() {
		return sym.IDN;
	}
}

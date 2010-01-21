package hr.fer.ppj.lab;

import java_cup.runtime.*;

public class Token extends Symbol {

	public enum Type {KEY, IDN, CONST};
	
	
	private Type type;
	private int pointer;
	private int line;
	private int col;
	public Lexer.ConstType constType = null;
	
	public Token(Type type, int pointer, int sym) {
		super(sym);
		this.type = type;
		this.pointer = pointer;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	@Override
	public String toString() {
		return "(" + type.toString() + ", " + String.valueOf(pointer) + ")"; 
	}
	
	public void setSymbolValue(String value) {
		TreeNode node = new TreeNode(value, new TreeNode[]{});
		node.token = this;
		this.value = node;
	}
	
	public Token(Type type, int pointer) {
		super(0); // !
		this.type = type;
		this.pointer = pointer;
	}
	
	public char getCharType() {
		if(constType==null) return '0';
		if(Lexer.ConstType.CHAR == constType) return 'c';
		if(Lexer.ConstType.INT == constType) return 'i';
		if(Lexer.ConstType.FLOAT == constType) return 'f';
		return '0';
	}
	
}

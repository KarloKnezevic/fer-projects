package hr.fer.ppj.lab;

public class Token {

	public enum Type {KEY, IDN, CONST};
	
	private Type type;
	private int pointer;
	private int line;
	private int col;
	
	public Token(Type type, int pointer) {
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
	
}

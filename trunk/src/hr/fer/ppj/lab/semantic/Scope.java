package hr.fer.ppj.lab.semantic;

import hr.fer.ppj.lab.TreeNode;

import java.util.HashMap;

public class Scope {
	public enum Type {BOOLEAN, INT, FLOAT, CHAR, VOID};
	
	public HashMap<String, Type> typeMap = new HashMap<String, Type>();
	public HashMap<String, Integer> idMap = new HashMap<String, Integer>();
	public String name;
	public TreeNode scopeEnd;
	
	public Scope(TreeNode scopeEnd, String name) {
		super();
		this.name = name;
		this.scopeEnd = scopeEnd;
	}

	public boolean hasVariable(String var) {
		return typeMap.containsKey(var);
	}
	
	public void addVariable(String name, Type type, int brojac) {
		typeMap.put(name, type);
		idMap.put(name, brojac);
	}
	
	public int getVariableId(String var) {
		return idMap.get(var);
	}
	
	public String toString() {
		return "Scope " + name + ": " + typeMap.keySet().toString();
		
	}
	
	public char getCharType(String var) {
		Type type = typeMap.get(var);
		if(type==null) return '0';
		if(Type.BOOLEAN == type) return 'b';
		if(Type.INT == type) return 'i';
		if(Type.CHAR == type) return 'c';
		if(Type.FLOAT == type) return 'f';
		return '0';
	}
}

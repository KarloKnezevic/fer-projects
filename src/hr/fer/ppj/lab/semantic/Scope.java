package hr.fer.ppj.lab.semantic;

import hr.fer.ppj.lab.TreeNode;

import java.util.HashMap;

public class Scope {
	public enum Type {BOOLEAN, INT, FLOAT, CHAR, VOID};
	
	public HashMap<String, Type> typeMap = new HashMap<String, Type>();
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
	
	public void addVariable(String name, Type type) {
		typeMap.put(name, type);
	}
	
	public String toString() {
		return "Scope " + name + ": " + typeMap.keySet().toString();
		
	}
}

package hr.fer.ppj.lab.semantic;

import hr.fer.ppj.lab.TreeNode;

public class InformationExtractor {

	/**
	 * @param node tipa "var_dec", "arg_def"
	 * @return 
	 */
	public static String getVariableName(TreeNode node) {
		if(node.nodeValue.equals("var_dec")) {
			return node.getChild(0).getChild(1).nodeValue;
		}
		if(node.nodeValue.equals("argdef")) {
			return node.getChild(1).nodeValue;
		}
		return null;
	}
	
	/**
	 * @param node tipa "var_dec"
	 * @return tip varijable
	 */
	public static Scope.Type getVariableType(TreeNode node) {
		String value=null;
		if(node.nodeValue.equals("var_dec")) {
			value = node.getChild(0).getChild(0).getChild(0).nodeValue;
		}
		if(node.nodeValue.equals("argdef")) {
			value = node.getChild(0).getChild(0).nodeValue;
		}
		if(value==null) return null;
		if(value.equals("int")) return Scope.Type.INT;
		if(value.equals("float")) return Scope.Type.FLOAT;
		if(value.equals("char")) return Scope.Type.CHAR;
		if(value.equals("boolean")) return Scope.Type.BOOLEAN;
		return null;
	}
	
	public static String getVariableTypeString(TreeNode node) {
		String value=null;
		if(node.nodeValue.equals("var_dec")) {
			value = node.getChild(0).getChild(0).getChild(0).nodeValue;
		}
		if(node.nodeValue.equals("argdef")) {
			value = node.getChild(0).getChild(0).nodeValue;
		}
		if(value==null) return null;
		return value;
	}
	
	/**
	 * @param node tipa "function"
	 * @return tip vrijednosti koju vraca funkcija
	 */
	public static Scope.Type getFunctionReturnType(TreeNode node) {
		if(node.nodeValue.equals("function")) {
			String value = node.getChild(0).getChild(0).getChild(0).nodeValue;
			if(value.equals("int")) return Scope.Type.INT;
			if(value.equals("float")) return Scope.Type.FLOAT;
			if(value.equals("char")) return Scope.Type.CHAR;
			if(value.equals("boolean")) return Scope.Type.BOOLEAN;
			if(value.equals("void")) return Scope.Type.VOID;
		}
		return null;
	}
	
	public static String getFunctionReturnTypeString(TreeNode node) {
		if(node.nodeValue.equals("function")) {
			return node.getChild(0).getChild(0).getChild(0).nodeValue;
		}
		return null;
	}
	
	/**
	 * @param node tipa "function"
	 * @return ime funkcije
	 */
	public static String getFunctionName(TreeNode node) {
		if(node.nodeValue.equals("function")) {
			return node.getChild(0).getChild(1).nodeValue;
		}
		return null;
	}
	
	/**
	 * @param node tipa "function"
	 * @return TreeNode do kojeg vrijedi scope funkcije
	 */
	public static TreeNode getFunctionScopeEndNode(TreeNode node) {
		if(node.nodeValue.equals("function")) {
			return node.getChild(1).getChild(2);
		}
		return null;
	}
	
	/**
	 * @param node tipa "function"
	 * @return TreeNode gdje su zapisani argumenti funkcije ili null ako ih nema
	 */
	public static TreeNode getFunctionArgDefList(TreeNode node) {
		if(node.nodeValue.equals("function")) {
			TreeNode n = node.getChild(0).getChild(3);
			if(!n.nodeValue.equals("argdef_list")) return null;
			return n;
		}
		return null;
	}
	
	public static TreeNode getCodeblockNode(TreeNode node) {
		int i=0;
		if(node.nodeValue.equals("while_loop") || node.nodeValue.equals("for_loop") || node.nodeValue.equals("if_cond")) {
			i = 4;
		} else if(node.nodeValue.equals("do_while_loop")) {
			i = 1;
		}else if(node.nodeValue.equals("function")){
			i = 1;
		} else {
			return null;
		}
		TreeNode n = node.getChild(i);
		if(!n.nodeValue.equals("code_block")) return null;
		return n;
	}
}

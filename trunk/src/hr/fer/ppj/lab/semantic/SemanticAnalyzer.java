package hr.fer.ppj.lab.semantic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import hr.fer.ppj.lab.Token;
import hr.fer.ppj.lab.TreeNode;

public class SemanticAnalyzer {
	
	public Stack<TreeNode> traversal = new Stack<TreeNode>();
	public Stack<Scope> scopes = new Stack<Scope>();
	public HashSet<Scope> allScopes = new HashSet<Scope>();
	public HashSet<TreeNode> scopedCodeBlocks = new HashSet<TreeNode>();
	public HashSet<String> functionNames = new HashSet<String>();
	public ArrayList<FunctionDescriptor> functions = new ArrayList<FunctionDescriptor>();
	
	private int line_br = 0;
	private int var_br = 1;
	
	public StringBuilder bytecode = new StringBuilder();

	public SemanticAnalyzer(TreeNode start) throws SemanticError {
		traversal.push(start);
		Scope global = new Scope(null, "global");
		scopes.push(global);
		bytecode.append("public class Program extends java.lang.Object {\n");
	}
	
	public void analyze() throws SemanticError  {
		TreeNode node;
		while(!traversal.empty()) {
			node = traversal.pop();
			for(int i=node.children.length-1; i>=0; i--) traversal.push(node.getChild(i));
			
			
			if(node.nodeValue.equals("var_dec")) {
				newVariableDeclaration(node);
			}
			if(node.nodeValue.equals("function")) {
				var_br=1;
				String funName = InformationExtractor.getFunctionName(node);
				Scope.Type funType = InformationExtractor.getFunctionReturnType(node);
				TreeNode endNode = InformationExtractor.getFunctionScopeEndNode(node);
				FunctionDescriptor fun = new FunctionDescriptor(InformationExtractor.getFunctionName(node),
						                                        InformationExtractor.getFunctionReturnTypeString(node));
				Scope funScope = new Scope(endNode, funName);
				scopes.push(funScope);
				scopedCodeBlocks.add(InformationExtractor.getCodeblockNode(node));
				functionNames.add(funName);
				// dodaj sve argumente u scope
				TreeNode defargs = InformationExtractor.getFunctionArgDefList(node);
				while(defargs!=null) {
					String argName = InformationExtractor.getVariableName(defargs.getChild(0));
					Scope.Type argType = InformationExtractor.getVariableType(defargs.getChild(0));
					fun.addArgumentType(InformationExtractor.getVariableTypeString(defargs.getChild(0)));
					funScope.addVariable(argName, argType, var_br);
					var_br++;
					if(defargs.children.length==3) {
						defargs = defargs.getChild(2);
					} else {
						defargs = null;
					}
				}
				functions.add(fun);
				bytecode.append(fun.getBytecodeDefinition()).append("\n  Code:\n");
			}
			if(node.nodeValue.equals("for_loop") || node.nodeValue.equals("while_loop") 
			   || node.nodeValue.equals("do_while_loop") || node.nodeValue.equals("if_cond")) {
				TreeNode codeBlock = InformationExtractor.getCodeblockNode(node);
				Scope scope = new Scope(codeBlock.getChild(2), scopes.peek().name + "." + node.nodeValue);
				scopes.push(scope);
				scopedCodeBlocks.add(codeBlock);
			}
			if(node.nodeValue.equals("code_block") && !scopedCodeBlocks.contains(node)) {
				Scope scope = new Scope(node.getChild(2), scopes.peek().name + "." + node.nodeValue);
				scopes.push(scope);
			}
			
			// ako je dosao do identifikatora provjeri je li sve u redu
			if(node.token!=null && node.token.getType().equals(Token.Type.IDN)) {
				if(searchForVariable(node.nodeValue)==null && !functionNameExists(node.nodeValue)) {
					throw new SemanticError(node, "Varijabla " + node.nodeValue + " nije deklarirana!");
				}
			}
			
			if(node.nodeValue.equals("code_stm")) {
				if(node.getChild(0).nodeValue.equals("expr")) generateExpCode(node.getChild(0));
			}
			
			if(node.equals(scopes.peek().scopeEnd)) allScopes.add(scopes.pop());
		}
		
		bytecode.append("}");
		allScopes.add(scopes.pop()); // dodaj global scope
	}
	
	public void newVariableDeclaration(TreeNode node) throws SemanticError {
		String name = InformationExtractor.getVariableName(node);
		Scope.Type type = InformationExtractor.getVariableType(node);
		if(searchForVariable(name) != null) throw new SemanticError(node, "Varijabla " + name + " je već deklarirana!");
		if(functionNameExists(name)) throw new SemanticError(node, "Naziv " + name + " se već koristi!");
		scopes.peek().addVariable(name, type, var_br);
		if(node.getChild(2).nodeValue.equals("expr")) {
			generateExpCode(node.getChild(2));
			addCodeLine("istore_"+var_br);
		}
		var_br++;
	}
	
	public Scope searchForVariable(String name) {
		for(int i=scopes.size()-1; i>=0; i--) { 
			if(scopes.get(i).hasVariable(name)) return scopes.get(i);
		}
		return null;
	}
	
	public boolean functionNameExists(String name) {
		return functionNames.contains(name);
	}
	
	public void generateExpCode(TreeNode node) {
		if(node.children.length>1) {
			generateExpCode(node.getChild(2));
			if(node.getChild(0).nodeValue.equals("expr")) {
				generateExpCode(node.getChild(0));
			} else if(node.getChild(0).nodeValue.equals("constant")) {
				addCodeLine("iconst_"+node.getChild(0).getChild(0).nodeValue);
			} else {
				String name = node.getChild(0).nodeValue;
				Scope varScope = searchForVariable(name);
				if(node.getChild(1).nodeValue.equals("=")) {
					if(varScope!=null) {
						addCodeLine("istore_"+varScope.getVariableId(name));
					}
					return;
				} else {
					if(varScope!=null) {
						addCodeLine("iload_"+varScope.getVariableId(name));
					}
				}
			}
			if(node.getChild(1).nodeValue.equals("+")) {
				addCodeLine("iadd");
			} else if(node.getChild(1).nodeValue.equals("*")) {
				addCodeLine("imul");
			} else if(node.getChild(1).nodeValue.equals("-")) {
				addCodeLine("isub");
			}else if(node.getChild(1).nodeValue.equals("/")) {
				addCodeLine("idiv");
			}
		} else {
			if(node.getChild(0).nodeValue.equals("constant")) {
				addCodeLine("iconst_"+node.getChild(0).getChild(0).nodeValue);
			} else if(node.getChild(0).nodeValue.equals("expr")) {
				generateExpCode(node.getChild(0));
			} else {
				String name = node.getChild(0).nodeValue;
				Scope varScope = searchForVariable(name);
				if(varScope!=null) {
					addCodeLine("iload_"+varScope.getVariableId(name));
				}
			}
		}
	}
	
	public void addCodeLine(String line) {
		bytecode.append("   "+line+"\n");
	}
}

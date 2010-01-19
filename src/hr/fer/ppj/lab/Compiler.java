package hr.fer.ppj.lab;

import hr.fer.ppj.lab.semantic.SemanticAnalyzer;
import hr.fer.ppj.lab.semantic.SemanticError;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import java_cup.runtime.Symbol;

public class Compiler {
	
	public List<Token> listaTokena;
	public Table symbolTable;
	public Symbol start;
	public SemanticAnalyzer semanticAnalyzer;
	public List<String> errorMsgs;
	public Lexer lexer;
	public Parser parser;
	
	public void compile(InputStream in) throws Exception {
		errorMsgs = new LinkedList<String>();
		symbolTable = new Table();
		listaTokena = new LinkedList<Token>();
		
		lexer = new Lexer(in, symbolTable, listaTokena);
		parser = new Parser(lexer);
		start = parser.parse();
		if(start == null) throw new Exception("Parse greška");
		semanticAnalyzer = new SemanticAnalyzer((TreeNode)start.value);
		
		while(true) {
			try {
				semanticAnalyzer.analyze();
				break;
			} catch (SemanticError e) {
				errorMsgs.add(e.msg);
				continue;
			}
		}
	}

}

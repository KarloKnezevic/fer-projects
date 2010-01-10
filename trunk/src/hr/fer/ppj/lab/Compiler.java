package hr.fer.ppj.lab;

import hr.fer.ppj.lab.semantic.Scope;
import hr.fer.ppj.lab.semantic.SemanticAnalyzer;
import hr.fer.ppj.lab.semantic.SemanticError;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import java_cup.runtime.Symbol;

import javax.swing.table.TableModel;

public class Compiler {
	
	public static TableModel getTableModel(File inputFile) throws Exception {
		InputStream in = new FileInputStream(inputFile);
		
		Table symbolTable = new Table();
		List<Token> listaTokena = new LinkedList<Token>();
		
		Lexer lexer = new Lexer(in, symbolTable, listaTokena);

		Parser parser = new Parser(lexer);
		
		Symbol start = parser.parse();
		
		Tools.visualizeParseTree(start);
		
		SemanticAnalyzer sema = new SemanticAnalyzer((TreeNode)start.value);
		while(true) {
			try {
				sema.analyze();
				break;
			} catch (SemanticError e) {
				System.out.println(e.msg);
				continue;
			}
		}
			
		for(Scope s : sema.allScopes) System.out.println(s);
		
		return Tools.getTableModel(listaTokena, symbolTable);

	}

}

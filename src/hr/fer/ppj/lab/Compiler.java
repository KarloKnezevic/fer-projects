package hr.fer.ppj.lab;

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
		
		parser sanka = new parser(lexer);
		
		Symbol start = sanka.parse();
		
		return Tools.getTableModel(listaTokena, symbolTable);

	}

}

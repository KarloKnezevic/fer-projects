package hr.fer.ppj.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.TableModel;

public class Compiler {
	
	public static TableModel getTableModel(File inputFile) throws IOException {
		InputStream in = new FileInputStream(inputFile);
		
		Table symbolTable = new Table();
		List<Token> listaTokena = new LinkedList<Token>();
		
		Lexer lexer = new Lexer(in, symbolTable, listaTokena);
		
		while(true) {
			Token t = lexer.next_token();
			if(t==null) break;	
		}
		
		return Tools.getTableModel(listaTokena, symbolTable);

	}

}

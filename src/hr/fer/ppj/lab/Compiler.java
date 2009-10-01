package hr.fer.ppj.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Compiler {
	public enum Keywords {INT, BOOLEAN}; 
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(new File("test.c"));
		Lexer lexer = new Lexer(in);
		while(true) {
			Token t = lexer.next_token();
			if(t==null) break;
			System.out.print(t);
		}
		ppjUI gui = new ppjUI();
		gui.setVisible(true);
		
	}

}

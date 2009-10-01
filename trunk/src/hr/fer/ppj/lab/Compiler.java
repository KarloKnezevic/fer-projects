package hr.fer.ppj.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

public class Compiler {
	public enum Keywords {INT, BOOLEAN}; 
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(new File("test.c"));
		Lexer lexer = new Lexer(in);
		List<String[]> lista = new ArrayList<String[]>();
		while(true) {
			Token t = lexer.next_token();
			if(t==null) break;
			lista.add(new String[]{t.getType().toString(), String.valueOf(t.getPointer()), "Test", String.valueOf(t.getLine()), String.valueOf(t.getCol())});
		}
		String[][] matrica = new String[lista.size()][5];
		int br=0;
		for(String[] red : lista) {
			matrica[br] = red;
			br++;
		}
		ppjUI gui = new ppjUI();
		TableModel model = new javax.swing.table.DefaultTableModel(matrica,
            new String [] {
                "Token", "Pointer", "Vrijednost", "Line", "Con"
        });
		gui.Table.setModel(model);
		gui.setVisible(true);
		
	}

}

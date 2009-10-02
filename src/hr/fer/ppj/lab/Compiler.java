package hr.fer.ppj.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

public class Compiler {
	
	public static TableModel getTableModel(File inputFile) throws IOException {
		InputStream in = new FileInputStream(inputFile);
		Lexer lexer = new Lexer(in);
		List<String[]> listaRedova = new ArrayList<String[]>();
		while(true) {
			
			Token t = lexer.next_token();
			if(t==null) break;
			
			String[] red = new String[]{t.getType().toString(), String.valueOf(t.getPointer()), 
					               null, String.valueOf(t.getLine()), 
					               String.valueOf(t.getCol())};
			if(t.getType() == Token.Type.CONST) {
				red[2] = lexer.constList.get(t.getPointer()) + " ("
				         + lexer.constTypeList.get(t.getPointer()).toString() + ")";
			} else if (t.getType() == Token.Type.IDN) {
				red[2] = lexer.idnList.get(t.getPointer());
			} else {
				red[2] = Lexer.fixedList.get(t.getPointer());
			}
			listaRedova.add(red);		
		}
		
		String[][] matrica = new String[listaRedova.size()][5];
		int br=0;
		for(String[] red : listaRedova) {
			matrica[br] = red;
			br++;
		}
		TableModel model = new javax.swing.table.DefaultTableModel(matrica,
            new String [] {
                "Token", "Pointer", "Vrijednost", "Line", "Column"
        });
		return model;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File testFile = new File("test.c");
		ppjUI gui = new ppjUI();
		gui.Table.setModel(getTableModel(testFile));
		gui.setVisible(true);
	}

}

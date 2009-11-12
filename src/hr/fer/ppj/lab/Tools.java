package hr.fer.ppj.lab;

import java.util.List;

import javax.swing.table.TableModel;

public class Tools {

	public static TableModel getTableModel(List<Token> listaTokena, Table table) {
		String[][] matrica = new String[listaTokena.size()][5];
		int br=0;
		for(Token t : listaTokena) {
			matrica[br][0] = t.getType().toString(); // tip
			matrica[br][1] = String.valueOf(t.getPointer()); // pointer
			matrica[br][3] = String.valueOf(t.getLine()); // line
			matrica[br][4] = String.valueOf(t.getCol()); // column
			
			// tip
			if(t.getType() == Token.Type.CONST) {
				matrica[br][2] = table.constList.get(t.getPointer()) + " ("
				         + table.constTypeList.get(t.getPointer()).toString() + ")";
			} else if (t.getType() == Token.Type.IDN) {
				matrica[br][2] = table.idnList.get(t.getPointer());
			} else {
				matrica[br][2] = table.krosList.get(t.getPointer());
			}

			br++;
		}
		
		TableModel model = new javax.swing.table.DefaultTableModel(matrica,
            new String [] {
                "Tip", "Pointer", "Vrijednost", "Line", "Column"
        });
		
		return model;
	}
}

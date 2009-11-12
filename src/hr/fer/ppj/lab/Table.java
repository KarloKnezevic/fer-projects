package hr.fer.ppj.lab;

import java.util.ArrayList;
import java.util.List;

public class Table {

	public List<String> constList = new ArrayList<String>(); /* Tablica konstanti */
	public List<Lexer.ConstType> constTypeList = new ArrayList<Lexer.ConstType>(); /* Tablica tipova konstanti */
	public List<String> idnList = new ArrayList<String>(); /* Tablica identifikatora */
	public List<String> krosList = new ArrayList<String>(); /* Tablica kroseva */
	
	public void initKros(String[] krosevi) {
		for(String s : krosevi) {
			krosList.add(s);
		}
	}
	
	public int addConstant(String constant, Lexer.ConstType type) {
		int i = constList.indexOf(constant);
		if(i==-1) {
			constList.add(constant);
			constTypeList.add(type);
			i=constList.size()-1;
		}
		return i;
	}
	
	public int addIdentifier(String identifier) {
		int i = idnList.indexOf(identifier);
		if(i==-1) {
			idnList.add(identifier);
			i=idnList.size()-1;
		}
		return i;
	}
	
	public int addKros(String kros) {
		int i = krosList.indexOf(kros);
		if(i==-1) {
			krosList.add(kros);
			i=krosList.size()-1;
		}
		return i;
	}
}

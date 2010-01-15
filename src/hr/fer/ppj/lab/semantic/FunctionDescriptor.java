package hr.fer.ppj.lab.semantic;

import java.util.ArrayList;
import java.util.List;

public class FunctionDescriptor {
	public String name;
	public String returnType;
	public List<String> argumentTypes;
	public FunctionDescriptor(String name, String returnType) {
		super();
		this.name = name;
		this.returnType = returnType;
		argumentTypes = new ArrayList<String>();
	}
	
	public void addArgumentType(String type) {
		argumentTypes.add(type);
	}
	
	public String getBytecodeDefinition() {
		StringBuilder sb = new StringBuilder();
		sb.append(returnType).append(" ").append(name).append("(");
		for(String arg : argumentTypes) {
			sb.append(arg).append(",");
		}
		if(sb.charAt(sb.length()-1)==',') sb.deleteCharAt(sb.length()-1);
		sb.append(");");
		return sb.toString();
	}
}

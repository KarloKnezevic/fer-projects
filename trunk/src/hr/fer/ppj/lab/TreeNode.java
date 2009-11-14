package hr.fer.ppj.lab;

public class TreeNode {
	
	public String nodeValue;
	public Object[] children;
	
	public TreeNode (String value, Object[] children) {
		nodeValue = value;
		this.children = children;
	}
	
	public String toString() {
		return nodeValue;
	}
}

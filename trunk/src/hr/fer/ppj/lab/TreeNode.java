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
	
	public TreeNode getChild(int i) {
		return (TreeNode)children[i];
	}
	
	public boolean hasChildren() {
		if(children == null || children.length == 0) return false;
		return true;
	}
}

package hr.fer.ppj.lab.semantic;

import hr.fer.ppj.lab.TreeNode;

public class SemanticError extends Exception {

	private static final long serialVersionUID = 1L;

	public TreeNode node;
	public String msg;

	public SemanticError(TreeNode node, String msg) {
		super();
		this.msg = msg;
		this.node = node;
	}
	
}

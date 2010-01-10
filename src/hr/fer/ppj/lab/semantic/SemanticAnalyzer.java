package hr.fer.ppj.lab.semantic;

import java.util.Stack;

import hr.fer.ppj.lab.TreeNode;

public class SemanticAnalyzer {

	public void analyze(TreeNode start) {
		TreeNode node, temp;
		Stack<TreeNode> traversal = new Stack<TreeNode>();
		traversal.add(start);
		
		while(!traversal.empty()) {
			node = traversal.pop();
			for(int i=node.children.length-1; i>=0; i--) {
				if(node.children[i] instanceof TreeNode) {
					temp = (TreeNode)node.children[i];
					traversal.push(temp);
					if(temp.nodeValue.equals("func_def")) System.out.print(temp.children[1]);
				}
			}
		}
	}
}

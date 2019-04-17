package pers.cs.util;

public class BuildTreeTest {

	public static void main(String[] args) {
		int[] sequence = {1,2,0,3,0,0,4,0,0};
		TreeNode root = TreeUtils.buildTree(sequence);
		TreeUtils.preOrder(root);
	}

}

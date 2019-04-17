package pers.cs.util;

public abstract class TreeUtils {
	public static int currentIndex = 0;
	
	/**
	 * 先序序列建树，对于空节点用0表示
	 * @param sequence
	 * @return
	 */
	public static TreeNode buildTree(int[] sequence) {
		TreeUtils.currentIndex = 0;
		return build(sequence);
	}

	private static TreeNode build(int[] sequence) {
		if(currentIndex == sequence.length)
			return null;
		if(sequence[currentIndex] == 0)
			return null;
		TreeNode treeNode = new TreeNode(sequence[currentIndex]);
		currentIndex++;
		treeNode.left = build(sequence);
		currentIndex++;
		treeNode.right = build(sequence);
		return treeNode;
	}
	
	public static void preOrder(TreeNode root) {
		if(root == null)
			return;
		System.out.print(root.val + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
}

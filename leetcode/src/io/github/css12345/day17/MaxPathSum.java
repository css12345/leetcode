package io.github.css12345.day17;

import static org.junit.Assert.assertEquals;
/**
 * leetcode 124.二叉树中的最大路径和<br>
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * @author cs
 *
 */
public class MaxPathSum {

	public static void main(String[] args) {
		TreeNode treeNode1 = new TreeNode(-10);
		TreeNode treeNode2 = new TreeNode(9);
		TreeNode treeNode3 = new TreeNode(20);
		TreeNode treeNode4 = new TreeNode(15);
		TreeNode treeNode5 = new TreeNode(7);
		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode3.left = treeNode4;
		treeNode3.right = treeNode5;
		
		assertEquals(42, new MaxPathSum().maxPathSum(treeNode1));
	}

	private int maxPathSum = Integer.MIN_VALUE;
	
	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		maxGain(root);
		return maxPathSum;
	}

	private int maxGain(TreeNode root) {
		if (root.left == null && root.right == null) {
			if (root.val > maxPathSum)
				maxPathSum = root.val;
			return root.val;
		}
		
		int leftMaxGain = 0, rightMaxGain = 0;
		if (root.left != null) {
			leftMaxGain = maxGain(root.left);
			leftMaxGain = Math.max(leftMaxGain, 0);
		}
		if (root.right != null) {
			rightMaxGain = maxGain(root.right);
			rightMaxGain = Math.max(rightMaxGain, 0);
		}
		
		int maxValue = root.val + leftMaxGain + rightMaxGain;
		if (maxValue > maxPathSum)
			maxPathSum = maxValue;
		return root.val + Math.max(leftMaxGain, rightMaxGain);
	}
}

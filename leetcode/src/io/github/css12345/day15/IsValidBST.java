package io.github.css12345.day15;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 面试题 04.05.合法二叉搜索树<br>
 * https://leetcode-cn.com/problems/legal-binary-search-tree-lcci/
 * @author cs
 *
 */
public class IsValidBST {

	public static void main(String[] args) {
		TreeNode treeNode1 = new TreeNode(2);
		TreeNode treeNode2 = new TreeNode(1);
		TreeNode treeNode3 = new TreeNode(3);
		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		assertEquals(true, new IsValidBST().isValidBST(treeNode1));

		TreeNode treeNode4 = new TreeNode(5);
		TreeNode treeNode5 = new TreeNode(1);
		TreeNode treeNode6 = new TreeNode(4);
		TreeNode treeNode7 = new TreeNode(3);
		TreeNode treeNode8 = new TreeNode(6);
		treeNode4.left = treeNode5;
		treeNode4.right = treeNode6;
		treeNode6.left = treeNode7;
		treeNode6.right = treeNode8;
		assertEquals(false, new IsValidBST().isValidBST(treeNode4));
	}

	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;
		List<Integer> inOrderList = new ArrayList<>();
		inOrder(root, inOrderList);
		
		for (int i = 0; i < inOrderList.size() - 1; i++)
			if (inOrderList.get(i) >= inOrderList.get(i + 1))
				return false;
		return true;
	}

	private void inOrder(TreeNode root, List<Integer> inOrderList) {
		if (root.left != null)
			inOrder(root.left, inOrderList);
		inOrderList.add(root.val);
		if (root.right != null)
			inOrder(root.right, inOrderList);
	}
}

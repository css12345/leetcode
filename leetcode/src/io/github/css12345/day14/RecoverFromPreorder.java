package io.github.css12345.day14;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 1028.从先序遍历还原二叉树<br>
 * https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/
 * @author cs
 *
 */
public class RecoverFromPreorder {

	public static void main(String[] args) {
		TreeNode treeNode1 = new RecoverFromPreorder().recoverFromPreorder("1-2--4---8----10---9--5-3--6---7");
		preOrder(treeNode1);        //1 2 4 8 10 9 5 3 6 7
		System.out.println();
		inOrder(treeNode1);         //10 8 4 9 2 5 1 7 6 3
		System.out.println();
		
		TreeNode treeNode2 = new RecoverFromPreorder().recoverFromPreorder("1-2--4---8---9----10-3--5--6---7");
		preOrder(treeNode2);        //1 2 4 8 9 10 3 5 6 7
		System.out.println();
		inOrder(treeNode2);         //8 4 10 9 2 1 5 3 7 6
		System.out.println();
	}

	private static void preOrder(TreeNode root) {
		System.out.print(root.val + " ");
		if (root.left != null)
			preOrder(root.left);
		if (root.right != null)
			preOrder(root.right);
	}
	
	private static void inOrder(TreeNode root) {
		if (root.left != null)
			inOrder(root.left);
		System.out.print(root.val + " ");
		if (root.right != null)
			inOrder(root.right);
	}
	
	private int currentIndex = 0;

	public TreeNode recoverFromPreorder(String s) {
		List<Integer> values = new ArrayList<>();
		List<Integer> deeps = new ArrayList<>();
		//以-开头
		int startPos = 0;
		while (startPos < s.length()) {
			//数字起始位置，比如---93为9的位置
			int startPosOfNum = startPos;
			while (startPosOfNum < s.length() && s.charAt(startPosOfNum) == '-')
				startPosOfNum++;
			//数字结束位置，比如---93为3的位置
			int endPosOfNum = startPosOfNum + 1;
			while (endPosOfNum < s.length() && s.charAt(endPosOfNum) != '-')
				endPosOfNum++;
			
			//value为s.substring(startPosOfNum, endPosOfNum)
			int value = Integer.parseInt(s.substring(startPosOfNum, endPosOfNum));
			values.add(value);
			//deep为startPosOfNum - startPos
			int deep = startPosOfNum - startPos;
			deeps.add(deep);
			
			startPos = endPosOfNum;
		}
		
		currentIndex = 0;
		return recoverFromPreorder(values, deeps);
	}

	private TreeNode recoverFromPreorder(List<Integer> values, List<Integer> deeps) {
		int copyOfCurrentIndex = currentIndex;
		TreeNode treeNode = new TreeNode(values.get(currentIndex));
		//如果下一项不存在或者下一项的深度不等于当前深度加一，说明该节点为叶子节点
		if (currentIndex + 1 >= values.size() || deeps.get(currentIndex + 1) != deeps.get(copyOfCurrentIndex) + 1)
			return treeNode;
		
		currentIndex++;
		treeNode.left = recoverFromPreorder(values, deeps);
		
		//这里还需要判断右孩子是否存在（可能左孩子存在，右孩子不存在）
		if (currentIndex + 1 >= values.size() || deeps.get(currentIndex + 1) != deeps.get(copyOfCurrentIndex) + 1)
			return treeNode;
		
		currentIndex++;
		treeNode.right = recoverFromPreorder(values, deeps);
		return treeNode;
	}
}

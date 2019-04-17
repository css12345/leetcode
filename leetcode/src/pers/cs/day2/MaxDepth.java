package pers.cs.day2;

import java.util.LinkedList;
import java.util.Queue;

import pers.cs.util.TreeNode;
import pers.cs.util.TreeUtils;

//leetcode 104
public class MaxDepth {

	public static void main(String[] args) {
		int[] sequence = { 3, 9, 0, 0, 20, 15, 0, 4, 0, 0, 7, 0, 0 };
		TreeNode root = TreeUtils.buildTree(sequence);
		TreeUtils.preOrder(root);
		System.out.println();
		System.out.println(new MaxDepth().maxDepth(root));
	}

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		// int maxDepth = bfs(root);
		dfs(root, 1);
		return maxDepth;
	}
	
	private int maxDepth = 0;

	/**
	 * 针对每个叶子节点进行比较
	 * @param root
	 * @param level
	 * @return
	 */
	private void dfs(TreeNode root, int level) {
		if(root.left == null && root.right == null) 
			maxDepth = Math.max(maxDepth, level);
		else {
			if(root.left != null)
				dfs(root.left, level + 1);
			if(root.right != null)
				dfs(root.right, level + 1);
		}
	}

	/**
	 * 思路是找最后一个节点，这个节点的深度就是最大深度
	 * 
	 * @param root
	 * @return
	 */
	private int bfs(TreeNode root) {
		Queue<TreeNodeInformation> queue = new LinkedList<>();
		queue.offer(new TreeNodeInformation(root, 1));
		while (!queue.isEmpty()) {
			TreeNodeInformation information = queue.poll();
			TreeNode treeNode = information.treeNode;
			int level = information.level;

			if (treeNode.left != null)
				queue.add(new TreeNodeInformation(treeNode.left, level + 1));
			if (treeNode.right != null)
				queue.add(new TreeNodeInformation(treeNode.right, level + 1));

			if (queue.isEmpty())
				return level;
		}
		return 0;
	}

	static class TreeNodeInformation {
		TreeNode treeNode;
		int level;

		public TreeNodeInformation(TreeNode treeNode, int level) {
			this.treeNode = treeNode;
			this.level = level;
		}

	}
}

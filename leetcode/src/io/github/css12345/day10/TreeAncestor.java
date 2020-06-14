package io.github.css12345.day10;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 5188.树节点的第 K个祖先
 * @author cs
 *
 */
public class TreeAncestor {

	public static void main(String[] args) {
		TreeAncestor treeAncestor = new TreeAncestor(7, new int[] {-1, 0, 0, 1, 1, 2, 2});

		assertEquals(1, treeAncestor.getKthAncestor(3, 1));  // 返回 1 ，它是 3 的父节点
		assertEquals(0, treeAncestor.getKthAncestor(5, 2));  // 返回 0 ，它是 5 的祖父节点
		assertEquals(-1, treeAncestor.getKthAncestor(6, 3));  // 返回 -1 因为不存在满足要求的祖先节点
		
		
		treeAncestor = new TreeAncestor(5, new int[] {-1, 0, 0, 0, 3});

		assertEquals(-1, treeAncestor.getKthAncestor(1, 5));
		assertEquals(-1, treeAncestor.getKthAncestor(3, 2));
		assertEquals(-1, treeAncestor.getKthAncestor(0, 1));
		assertEquals(0, treeAncestor.getKthAncestor(3, 1));
		assertEquals(-1, treeAncestor.getKthAncestor(3, 5));
	}

	private List<List<Integer>> edges = new ArrayList<>();
	private int[] levels;
	//dp[i][j]表示node的第2^j个祖先节点
	private int[][] dp;
	
	public TreeAncestor(int n, int[] parent) {
		for (int i = 0; i < n; i++)
			edges.add(new ArrayList<>());
		for (int i = 1; i < n; i++) 
			edges.get(parent[i]).add(i);
		
		this.levels = new int[n];
		levels[0] = 1;
		dfs(0);
		int maxLevel = levels[0];
		for (int i = 1; i < n; i++)
			maxLevel = Math.max(maxLevel, levels[i]);
		int column = (int) (Math.log10(maxLevel - 1) / Math.log10(2)) + 1;
		this.dp = new int[n][column];
		
		for (int i = 0; i < n; i++) {
			this.dp[i][0] = parent[i];
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < column; j++) {
				dp[i][j] = dp[i][j - 1] == -1 ? -1 : dp[dp[i][j - 1]][j - 1];
			}
		}
	}

	private void dfs(int nodeIndex) {
		for (int childIndex : edges.get(nodeIndex)) {
			levels[childIndex] = levels[nodeIndex] + 1;
			dfs(childIndex);
		}
	}

	public int getKthAncestor(int node, int k) {
		for (int i = 0; k > 0 && i < dp[0].length; i++) {
			if ((1 & k) == 1) {
				node = dp[node][i];
				if (node == -1)
					return -1;
			}
			k = k / 2;
		}
		if (k > 0)
			return -1;
		return node;
	}
}

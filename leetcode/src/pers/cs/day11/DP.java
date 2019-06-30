package pers.cs.day11;

/**
 * 不带压缩的动态规划
 * https://leetcode-cn.com/submissions/detail/21720866/
 * @author cs
 *
 */
public class DP {
	// best[i][j]表示从(0,0)到(i,j)的最短距离
	private int[][] best;
	private int M, N;

	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
			return 0;

		M = grid.length;
		N = grid[0].length;
		best = new int[M][N];

		best[0][0] = grid[0][0];
		for (int i = 1; i < N; i++)
			best[0][i] = best[0][i - 1] + grid[0][i];
		for (int i = 1; i < M; i++)
			best[i][0] = best[i - 1][0] + grid[i][0];

		for (int i = 1; i < M; i++)
			for (int j = 1; j < N; j++)
				best[i][j] = Math.min(best[i - 1][j], best[i][j - 1]) + grid[i][j];

		return best[M - 1][N - 1];
	}
}

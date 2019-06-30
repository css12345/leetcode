package pers.cs.day11;

/**
 * 带压缩的动态规划
 * https://leetcode-cn.com/submissions/detail/21721795/
 * @author cs
 *
 */
public class CompressedDp {

	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
			return 0;

		int M = grid.length;
		int N = grid[0].length;
		int[] best = new int[Math.min(M, N)];
		best[0] = grid[0][0];

		if (M > N) {
			for (int i = 1; i < N; i++)
				best[i] = best[i - 1] + grid[0][i];
			for (int i = 1; i < M; i++) {
				best[0] += grid[i][0];
				for (int j = 1; j < N; j++)
					best[j] = Math.min(best[j], best[j - 1]) + grid[i][j];
			}
		} else {
			for (int i = 1; i < M; i++)
				best[i] = best[i - 1] + grid[i][0];
			for (int i = 1; i < N; i++) {
				best[0] += grid[0][i];
				for (int j = 1; j < M; j++)
					best[j] = Math.min(best[j], best[j - 1]) + grid[j][i];
			}
		}

		return best[Math.min(M, N) - 1];
	}
	
	public static void main(String[] args) {
		int[][] grid = {{1,2,5},{3,2,1}};
		System.out.println(new CompressedDp().minPathSum(grid));
	}
}

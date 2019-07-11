package pers.cs.day22;

import static org.junit.Assert.assertEquals;

public class CalculateMinimumHP {

	public static void main(String[] args) {
		int[][] dungeon = {{-2,-3,3},{-5,-10,1},{0,30,-5}};
		assertEquals(7, new CalculateMinimumHP().calculateMinimumHP(dungeon));
	}

	public int calculateMinimumHP(int[][] dungeon) {
		if(dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0)
			return 0;
		return minHP2(dungeon);
	}

	/**
	 * 带状态压缩
	 * https://leetcode-cn.com/submissions/detail/22666914/
	 */
	private int minHP2(int[][] dungeon) {
		int m = dungeon.length;
		int n = dungeon[0].length;
		int[] dp;
		if(n > m) {
			dp = new int[m];
			int lastElement = dungeon[m - 1][n - 1];
			dp[m - 1] = lastElement < 0 ? 1 - lastElement : 1;
			
			for(int i = m - 2;i >= 0;i--)
				dp[i] = Math.max(dp[i + 1] - dungeon[i][n - 1], 1);
			
			for(int i = n - 2;i >= 0;i--) {
				dp[m - 1] = Math.max(dp[m - 1] - dungeon[m - 1][i], 1);
				for(int j = m - 2;j >= 0;j--) {
					int down = Math.max(dp[j + 1] - dungeon[j][i], 1);
					int right = Math.max(dp[j] - dungeon[j][i], 1);
					dp[j] = Math.min(down, right);
				}
			}
		} else {
			dp = new int[n];
			int lastElement = dungeon[m - 1][n - 1];
			dp[n - 1] = lastElement < 0 ? 1 - lastElement : 1;
			
			for(int i = n - 2;i >= 0;i--)
				dp[i] = Math.max(dp[i + 1] - dungeon[m - 1][i], 1);
			
			for(int i = m - 2;i >= 0;i--) {
				dp[n - 1] = Math.max(dp[n - 1] - dungeon[i][n - 1], 1);
				for(int j = n - 2;j >= 0;j--) {
					int down = Math.max(dp[j] - dungeon[i][j], 1);
					int right = Math.max(dp[j + 1] - dungeon[i][j], 1);
					dp[j] = Math.min(down, right);
				}
			}
		}
		return dp[0];
	}

	/**
	 * 不带状态压缩
	 * https://leetcode-cn.com/submissions/detail/22663594/
	 */
	private int minHP1(int[][] dungeon) {
		int m = dungeon.length;
		int n = dungeon[0].length;
		int[][] dp = new int[m][n];
		int lastElement = dungeon[m - 1][n - 1];
		dp[m - 1][n - 1] = lastElement < 0 ? 1 - lastElement : 1;
		
		for(int i = n - 2;i >= 0;i--)
			dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - dungeon[m - 1][i], 1);
		
		for(int i = m - 2;i >= 0;i--) {
			dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
			for(int j = n - 2;j >= 0;j--) {
				int down = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
				int right = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
				dp[i][j] = Math.min(down, right);
			}
		}
		return dp[0][0];	
	}
}

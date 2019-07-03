package pers.cs.day14;

import static org.junit.Assert.assertEquals;

public class DP {

	public static void main(String[] args) {
		int[][] testDatas = { { 1, 2, 5 }, { 2 } };
		int[] testAmounts = { 5, 3 };
		int[] excepts = { 4, 0 };

		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(excepts[i], new Brute().change(testAmounts[i], testDatas[i]));
		}
	}

	public int change(int amount, int[] coins) {
		if (amount == 0)
			return 1;
		if (amount < 0 || coins == null || coins.length == 0)
			return 0;

		// return dpWithoutCompress(coins,amount);

		return dpWithCompress(coins, amount);
	}

	/**
	 * 带状态压缩的dp
	 * https://leetcode-cn.com/submissions/detail/21994605/
	 */
	private int dpWithCompress(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		for (int i = 0; i * coins[0] < dp.length; i++)
			dp[i * coins[0]] = 1;

		for (int i = 1; i < coins.length; i++)
			for (int j = 1; j < dp.length; j++)
				if(j >= coins[i])
					dp[j] += dp[j - coins[i]];
		return dp[amount];
	}

	/**
	 * 不带状态压缩的dp 
	 * https://leetcode-cn.com/submissions/detail/21994192/
	 */
	private int dpWithoutCompress(int[] coins, int amount) {
		int[][] dp = new int[coins.length][amount + 1];
		for (int i = 0; i < dp.length; i++)
			dp[i][0] = 1;
		for (int i = 1; i * coins[0] < dp[0].length; i++)
			dp[0][i * coins[0]] = 1;

		for (int i = 1; i < dp.length; i++)
			for (int j = 1; j < dp[i].length; j++)
				if (j < coins[i])
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
		return dp[coins.length - 1][amount];
	}
}

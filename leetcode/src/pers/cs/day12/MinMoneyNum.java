package pers.cs.day12;

import static org.junit.Assert.assertEquals;

public class MinMoneyNum {

	public static void main(String[] args) {
		int[][] testDatas = { { 5, 2, 3 }, { 5, 2, 3 }, { 3, 5 }, { 5, 3, 4, 1 } };
		int[] aims = { 20, 0, 2, 7 };
		int[] excepteds = { 4, 0, -1, 2 };
		for (int i = 0; i < testDatas.length; i++) {
			// int actual = new MinMoneyNum().minMoneyNum(testDatas[i], aims[i]);
			int actual = new MinMoneyNum().minMoneyNumWithCompress(testDatas[i], aims[i]);
			assertEquals(excepteds[i], actual);
		}
	}

	/**
	 * 不带路径压缩
	 *https://leetcode-cn.com/submissions/detail/21984892/
	 */
	public int minMoneyNum(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim <= 0)
			return 0;
		int[][] dp = new int[arr.length][aim + 1];
		for (int i = 1; i <= aim; i++) {
			if (i % arr[0] == 0)
				dp[0][i] = i / arr[0];
			else
				dp[0][i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				if (j < arr[i])
					dp[i][j] = dp[i - 1][j];
				else {
					if (dp[i][j - arr[i]] == Integer.MAX_VALUE)
						dp[i][j] = dp[i - 1][j];
					else
						dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - arr[i]] + 1);
				}
			}
		}

		if (dp[arr.length - 1][aim] == Integer.MAX_VALUE)
			return -1;
		return dp[arr.length - 1][aim];
	}

	/**
	 * 带路径压缩
	 * https://leetcode-cn.com/submissions/detail/21985048/
	 */
	public int minMoneyNumWithCompress(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount <= 0)
			return 0;
		int[] dp = new int[amount + 1];
		for (int i = 1; i <= amount; i++) {
			if (i % coins[0] == 0)
				dp[i] = i / coins[0];
			else
				dp[i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < coins.length; i++) {
			for (int j = 1; j <= amount; j++) {
				if (j < coins[i])
					continue;
				else {
					if (dp[j - coins[i]] == Integer.MAX_VALUE)
						continue;
					else
						dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
				}
			}
		}

		if (dp[amount] == Integer.MAX_VALUE)
			return -1;
		return dp[amount];
	}
}

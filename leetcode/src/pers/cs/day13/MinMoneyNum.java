package pers.cs.day13;

import static org.junit.Assert.assertEquals;

public class MinMoneyNum {

	public static void main(String[] args) {
		int[][] testDatas = { { 5, 2, 3 }, { 5, 2, 5, 3 }, { 5, 2, 5, 3 }, { 5, 3, 4, 1 } };
		int[] aims = { 20, 10, 0, 7 };
		int[] excepteds = { -1, 2, 0, 2 };
		for (int i = 0; i < testDatas.length; i++) {
			// int actual = new MinMoneyNum().minMoneyNum(testDatas[i], aims[i]);
			int actual = new MinMoneyNum().minMoneyNumWithCompress(testDatas[i], aims[i]);
			assertEquals(excepteds[i], actual);
		}
	}

	/**
	 * 不带路径压缩
	 *
	 */
	public int minMoneyNum(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim <= 0)
			return 0;
		int[][] dp = new int[arr.length][aim + 1];
		for (int i = 1; i <= aim; i++) {
			if (i == arr[0])
				dp[0][i] = 1;
			else
				dp[0][i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				if (j < arr[i])
					dp[i][j] = dp[i - 1][j];
				else {
					if (dp[i - 1][j - arr[i]] == Integer.MAX_VALUE)
						dp[i][j] = dp[i - 1][j];
					else
						dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - arr[i]] + 1);
				}
			}
		}

		if (dp[arr.length - 1][aim] == Integer.MAX_VALUE)
			return -1;
		return dp[arr.length - 1][aim];
	}

	/**
	 * 带路径压缩
	 */
	public int minMoneyNumWithCompress(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim <= 0)
			return 0;
		int[] dp = new int[aim + 1];
		for (int i = 1; i <= aim; i++) {
			if (i == arr[0])
				dp[i] = 1;
			else
				dp[i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = aim;j >= 1;j--) {
				if (j < arr[i])
					continue;
				else {
					if (dp[j - arr[i]] == Integer.MAX_VALUE)
						continue;
					else
						dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
				}
			}
		}

		if (dp[aim] == Integer.MAX_VALUE)
			return -1;
		return dp[aim];
	}
}

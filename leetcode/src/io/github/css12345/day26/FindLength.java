package io.github.css12345.day26;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 718.最长重复子数组<br>
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 * @author cs
 *
 */
public class FindLength {

	public static void main(String[] args) {
		assertEquals(3, new FindLength().findLength(new int[] { 1, 2, 3, 2, 1 }, new int[] { 3, 2, 1, 4, 7 }));
	}

	public int findLength(int[] A, int[] B) {
		// dp[i][j]表示以A[i - 1]和B[j - 1]结尾的最长公共子数组的长度
		int[][] dp = new int[A.length + 1][B.length + 1];
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if (A[i - 1] == B[j - 1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = 0;
			}
		}

		int result = 0;
		for (int i = 0; i < dp.length; i++)
			for (int j = 0; j < dp[i].length; j++)
				result = Math.max(result, dp[i][j]);
		return result;
	}
}

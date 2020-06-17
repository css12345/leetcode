package io.github.css12345.day13;

import static org.junit.Assert.assertEquals;
/**
 * leetcode 1014.最佳观光组合<br>
 * https://leetcode-cn.com/problems/best-sightseeing-pair/
 * @author cs
 *
 */
public class MaxScoreSightseeingPair {

	public static void main(String[] args) {
		assertEquals(11, new MaxScoreSightseeingPair().maxScoreSightseeingPair(new int[] { 8, 1, 5, 2, 6 }));
	}

	public int maxScoreSightseeingPair(int[] A) {
		//dp[i]表示选择A[i]作为第二个元素时的最高分
		int[] dp = new int[A.length];
		dp[1] = A[0] + A[1] - 1;
		for (int i = 2; i < A.length; i++) {
			//选择dp[i-1]中的第一个元素作为第一个元素，A[i]作为第二个元素（这种情况排除了A[i-1]作为第一个元素，因为dp[i-1]中A[i-1]是作为第二个元素的）；
			//选择A[i-1]作为第一个元素，A[i]作为第二个元素
			dp[i] = Math.max(dp[i - 1] - A[i - 1] + A[i] - 1, A[i - 1] + A[i] - 1);
		}
		
		int maxScore = dp[1];
		for (int i = 2; i < dp.length; i++)
			maxScore = Math.max(maxScore, dp[i]);
		return maxScore;
	}
}

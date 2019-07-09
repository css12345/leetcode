package pers.cs.day20;

import static org.junit.Assert.assertEquals;

public class MinDistance {
	public static void main(String[] args) {
		assertEquals(3, new MinDistance().minDistance("horse", "ros"));
		assertEquals(5, new MinDistance().minDistance("intention", "execution"));
		
		assertEquals(2, new MinDistance().minDistance1("abc", "adc", 5, 3, 2));
		assertEquals(2, new MinDistance().minDistance2("abc", "adc", 5, 3, 2));
		
		assertEquals(8, new MinDistance().minDistance1("abc", "adc", 5, 3, 100));
		assertEquals(8, new MinDistance().minDistance2("abc", "adc", 5, 3, 100));
		
		assertEquals(0, new MinDistance().minDistance1("abc", "abc", 5, 3, 2));
		assertEquals(0, new MinDistance().minDistance2("abc", "abc", 5, 3, 2));
		
		assertEquals(8, new MinDistance().minDistance1("ab12cd3", "abcdf", 5, 3, 2));
		assertEquals(8, new MinDistance().minDistance2("ab12cd3", "abcdf", 5, 3, 2));
	}

	public int minDistance(String word1, String word2) {
		if(word1 == null || word2 == null)
			return 0;
		// return minDistance1(word1,word2,1,1,1);
		return minDistance2(word1,word2,1,1,1);
	}

	/**
	 *   带压缩的动态规划
	 * https://leetcode-cn.com/submissions/detail/22457918/
	 */
	private int minDistance2(String word1, String word2, int ic, int dc, int rc) {
		if(word1.length() < word2.length()) {
			String tmpString = word1;
			word1 = word2;
			word2 = tmpString;
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		
		int[] dp = new int[word2.length() + 1];
		for(int i = 1;i <= word2.length();i++)
			dp[i] = ic * i;
		for(int i = 1; i <= word1.length();i++) {
			int lastUpLeft = dp[0];
			dp[0] += dc;
			for(int j = 1;j <= word2.length();j++) {
				int minValue = Math.min(dp[j] + dc, dp[j - 1] + ic);
				if(word1.charAt(i - 1) == word2.charAt(j - 1)) 
					minValue = Math.min(minValue, lastUpLeft);
				else
					minValue = Math.min(minValue, lastUpLeft + rc);
				lastUpLeft = dp[j];
				dp[j] = minValue;
			}
		}
		return dp[word2.length()];
	}

	/**
	 *   不带压缩的动态规划
	 * https://leetcode-cn.com/submissions/detail/22456675/
	 */
	private int minDistance1(String word1, String word2, int ic, int dc, int rc) {
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		for(int i = 1;i <= word2.length();i++)
			dp[0][i] = i * ic;
		for(int i = 1;i <= word1.length();i++) 
			dp[i][0] = i * dc;
		for(int i = 1;i <= word1.length();i++) {
			for(int j = 1;j <= word2.length();j++) {
				int minValue = Math.min(dp[i - 1][j] + dc, dp[i][j - 1] + ic);
				if(word1.charAt(i - 1) == word2.charAt(j - 1))
					minValue = Math.min(minValue, dp[i - 1][j - 1]);
				else 
					minValue = Math.min(minValue, dp[i - 1][j - 1] + rc);
				dp[i][j] = minValue;
			}
		}
		return dp[word1.length()][word2.length()];
	}
}

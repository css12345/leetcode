package pers.cs.day21;

import static org.junit.Assert.assertEquals;

public class IsInterleave {
	public static void main(String[] args) {
		assertEquals(true, new IsInterleave().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		assertEquals(false, new IsInterleave().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
		assertEquals(false, new IsInterleave().isInterleave("a", "", "c"));
	}

	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null)
			return false;
		return isInterleave2(s1, s2, s3);
	}

	/**
	 * 不带状态压缩
	 * https://leetcode-cn.com/submissions/detail/22565415/
	 */
	private boolean isInterleave1(String s1, String s2, String s3) {
		if(s1.length() + s2.length() != s3.length())
			return false;
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true;
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = s3.substring(0, i).equals(s1.substring(0, i));
		}
		for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = s3.substring(0, i).equals(s2.substring(0, i));
		}
		
		for (int i = 1; i < dp.length; i++) {
			for(int j = 1; j < dp[i].length; j++) {
				dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
							(dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
			}
		}
		return dp[s1.length()][s2.length()];
	}
	
	/**
	 * 带状态压缩
	 * https://leetcode-cn.com/submissions/detail/22566635/
	 */
	private boolean isInterleave2(String s1, String s2, String s3) {
		if(s1.length() + s2.length() != s3.length())
			return false;
		if (s1.length() < s2.length()) {
			String tmp = s1;
			s1 = s2;
			s2 = tmp;
		}
		
		boolean[] dp = new boolean[s2.length() + 1];
		dp[0] = true;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = s3.substring(0, i).equals(s2.substring(0, i));
		}
		
		for (int i = 1; i <= s1.length(); i++) {
			dp[0] = s3.substring(0, i).equals(s1.substring(0, i));
			for(int j = 1; j < dp.length; j++) {
				dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
							(dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
			}
		}
		return dp[s2.length()];
	}
}

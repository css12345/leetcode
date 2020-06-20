package io.github.css12345.day16;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 10.正则表达式匹配<br>
 * https://leetcode-cn.com/problems/regular-expression-matching/
 * @author cs
 *
 */
public class IsMatch {

	public static void main(String[] args) {
		assertEquals(true, new IsMatch().isMatch("abccaabc", "abc*a*.*"));
		assertEquals(false, new IsMatch().isMatch("aa", "a"));
		assertEquals(true, new IsMatch().isMatch("aa", "a*"));
		assertEquals(true, new IsMatch().isMatch("ab", ".*"));
		assertEquals(true, new IsMatch().isMatch("aab", "c*a*b*"));
		assertEquals(false, new IsMatch().isMatch("mississippi", "mis*is*p*."));
	}

	public boolean isMatch(String s, String p) {
		// dp[i][j]表示s.substring(0, i)和p.substring(0, j)的匹配结果；i=0时s为空串，j=0时p为空串。
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		// 第0列其他行由于默认值是false，不需要再初始化
		// 初始化第0行其他列
		for (int i = 1; i < dp[0].length; i++) {
			if (p.charAt(i - 1) == '*') {
				// *号前的字符出现0次或1次，因为此时是第0行，对应s的子串为空，不存在大于1次的情况
				dp[0][i] = dp[0][i - 2] || dp[0][i - 1];
			} else {
				// 出现a-z或.时，不可能和空串匹配上
				dp[0][i] = false;
			}
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if (p.charAt(j - 1) == '*') {
					// 分为*号前的字符出现0次、1次和大于1次的情况，大于1次即检查dp[i-1][j]和s的第i个字符与p的第j - 1个字符是否相等
					dp[i][j] = dp[i][j - 2] || dp[i][j - 1]
							|| (dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
				} else if (p.charAt(j - 1) == '.') {
					//p的第j个字符为.，这里不用检查s的第i个字符和p的第j个字符是否相等，直接看两个前一项的比较结果
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					//p的第j个字符为a-z，需要检查s的第i个字符和p的第j个字符是否相等及dp[i-1][j-1]
					dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1)) && dp[i - 1][j - 1];
				}
			}
		}
		return dp[s.length()][p.length()];
	}
}

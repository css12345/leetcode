package io.github.css12345.day29;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 32.最长有效括号
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * @author cs
 *
 */
public class LongestValidParentheses {

	public static void main(String[] args) {
		assertEquals(2, new LongestValidParentheses().longestValidParentheses("(()"));
		assertEquals(4, new LongestValidParentheses().longestValidParentheses(")()())"));
	}

	public int longestValidParentheses(String s) {
		if (s == null || s.equals("") || s.length() == 1)
			return 0;
		//dp[i]表示以第i个字符结尾的括号的有效长度
		int[] dp = new int[s.length()];
		if (s.charAt(1) == ')' && s.charAt(0) == '(')
			dp[1] = 2;
		for (int i = 2; i < dp.length; i++) {
			if (s.charAt(i) == '(')
				continue;
			if (s.charAt(i - 1) == '(') {
				dp[i] = dp[i - 2] + 2;
			} else {
				if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + 2;
					if (i - dp[i - 1] - 2 >= 0)
						dp[i] += dp[i - dp[i - 1] - 2];
				}
			}
		}
		int result = 0;
		for (int i = 0; i < dp.length; i++)
			result = Math.max(result, dp[i]);
		return result;
    }
}

package io.github.css12345.day30;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 44.通配符匹配<br>
 * https://leetcode-cn.com/problems/wildcard-matching/
 * @author cs
 *
 */
public class IsMatch {

	public static void main(String[] args) {
		assertEquals(false, new IsMatch().isMatch("aa", "a"));
		assertEquals(true, new IsMatch().isMatch("aa", "*"));
		assertEquals(false, new IsMatch().isMatch("cb", "?a"));
		assertEquals(true, new IsMatch().isMatch("adceb", "*a*b"));
		assertEquals(false, new IsMatch().isMatch("acdcb", "a*c?b"));
	}

	public boolean isMatch(String s, String p) {
        //dp[i][j]表示s.substring(0, i)和p.substring(0, j)是否匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 1];
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '*') {
                    boolean hasTrue = false;
                    for (int k = i; k >= 0; k--)
                        if (dp[k][j - 1]) {
                            hasTrue = true;
                            break;
                        }
                    dp[i][j] = hasTrue;
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}

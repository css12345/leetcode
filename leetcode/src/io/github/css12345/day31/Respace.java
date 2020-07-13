package io.github.css12345.day31;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 17.13. 恢复空格<br>
 * https://leetcode-cn.com/problems/re-space-lcci/submissions/
 * @author cs
 *
 */
public class Respace {

	public static void main(String[] args) {
		assertEquals(7, new Respace().respace(new String[] { "looked", "just", "like", "her", "brother" },
				"jesslookedjustliketimherbrother"));
		assertEquals(0, new Respace().respace(new String[] { "s", "ss", "sa" }, "ssa"));
		assertEquals(7, new Respace().respace(
				new String[] { "sssjjs", "hschjf", "hhh", "fhjchfcfshhfjhs", "sfh", "jsf", "cjschjfscscscsfjcjfcfcfh",
						"hccccjjfchcffjjshccsjscsc", "chcfjcsshjj", "jh", "h", "f", "s", "jcshs", "jfjssjhsscfc" },
				"sssjjssfshscfjjshsjjsjchffffs"));
	}

	
	public int respace(String[] dictionary, String sentence) {
		Set<String> set = new HashSet<>(new ArrayList<>(Arrays.asList(dictionary)));
		int[] dp = new int[sentence.length() + 1];
		for (int i = 1; i <= sentence.length(); i++) {
			dp[i] = dp[i - 1] + 1;
			for (int j = 0; j < i; j++) {
				if (set.contains(sentence.substring(j, i)))
					dp[i] = Math.min(dp[i], dp[j]);
			}
		}
		return dp[sentence.length()];
	}
}

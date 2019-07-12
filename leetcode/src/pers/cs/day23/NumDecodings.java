package pers.cs.day23;

import static org.junit.Assert.assertEquals;

public class NumDecodings {

	public static void main(String[] args) {
		String[] numStrings = {"12","226","1111","01","10"};
		int[] expecteds = {2, 3, 5, 0, 1};
		NumDecodings numDecodings = new NumDecodings();
		for (int i = 0;i < numStrings.length;i++)
			assertEquals(expecteds[i], numDecodings.numDecodings(numStrings[i]));
	}

	public int numDecodings(String s) {
		if (s == null || s.equals(""))
			return 0;
		return process2(s);
	}

	/**
	 *     非递归，类似斐波那契
	 * https://leetcode-cn.com/submissions/detail/22744948/
	 */
	private int process2(String s) {
		int cur = s.charAt(s.length() - 1) == '0' ? 0 : 1;
		int next = 1;
		for (int i = s.length() - 2;i >= 0;i--) {
			int tmp = cur;
			if (s.charAt(i) == '0')
				cur = 0;
			else {
				if ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') <= 26)
					cur += next;
			}
			next = tmp;
		}
		return cur;
	}

	/**
	 *      递归解法
	 * https://leetcode-cn.com/submissions/detail/22744521/
	 */
	private int process1(String s, int i) {
		if (i == s.length()) 
			return 1;
		if (s.charAt(i) == '0') 
			return 0;
		int result = process1(s, i + 1);
		if (i + 1 < s.length() && (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') <= 26)
			result += process1(s, i + 2);
		return result;
	}
}

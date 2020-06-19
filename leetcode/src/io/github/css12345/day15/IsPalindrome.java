package io.github.css12345.day15;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 125.验证回文串<br>
 * https://leetcode-cn.com/problems/valid-palindrome/
 * @author cs
 *
 */
public class IsPalindrome {

	public static void main(String[] args) {
		String[] testDatas = { "A man, a plan, a canal: Panama", "race a car", "" };
		boolean[] testResults = { true, false, true };
		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(testResults[i], new IsPalindrome().isPalindrome(testDatas[i]));
		}
	}

	public boolean isPalindrome(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i)) || Character.isLowerCase(s.charAt(i)))
				stringBuilder.append(s.charAt(i));
			else if (Character.isUpperCase(s.charAt(i)))
				stringBuilder.append(Character.toLowerCase(s.charAt(i)));
		}
		
		String handledString = stringBuilder.toString();
		for (int i = 0, j = handledString.length() - 1; i < j; i++, j--) {
			if (handledString.charAt(i) != handledString.charAt(j))
				return false;
		}
		return true;
	}
}

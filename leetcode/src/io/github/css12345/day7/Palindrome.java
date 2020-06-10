package io.github.css12345.day7;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 9.回文数<br>
 * https://leetcode-cn.com/problems/palindrome-number/
 * @author cs
 *
 */
public class Palindrome {

	public static void main(String[] args) {
		assertEquals(true, new Palindrome().isPalindrome(121));
		assertEquals(false, new Palindrome().isPalindrome(-121));
		assertEquals(false, new Palindrome().isPalindrome(10));
	}

	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		if (x == 0)
			return true;
		if (x % 10 == 0)
			return false;
		
		int halfReverseX = 0;
		while (halfReverseX < x) {
			halfReverseX = halfReverseX * 10 + x % 10;
			x = x / 10;
		}
		
		if (x == halfReverseX)
			return true;
		halfReverseX /= 10;
		return halfReverseX == x;
    }
}

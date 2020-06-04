package io.github.css12345.day1;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 1415 长度为n的开心字符串中字典序第 k小的字符串<br>
 * https://leetcode-cn.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
 * 
 * @author cs
 *
 */
public class HappyString {

	public static void main(String[] args) {
		int[][] testDatas = { { 1, 3 }, { 1, 4 }, { 3, 9 }, { 2, 7 }, { 10, 100 } };
		String[] testResults = { "c", "", "cab", "", "abacbabacb" };
		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(testResults[i], new HappyString().getHappyString(testDatas[i][0], testDatas[i][1]));
		}
	}

	private static final char[] CHARACTERS = { 'a', 'b', 'c' };

	public String getHappyString(int n, int k) {
		int[] indexOfDigits = new int[n];
		int count = 0;
		while (true) {
			String lenNStr = generateALenNString(indexOfDigits);
			if (lenNStr == null)
				return "";
			if (isHappyString(lenNStr))
				count++;
			if (count == k)
				return lenNStr;
		}
	}

	private String generateALenNString(int[] indexOfDigits) {
		for (int i = indexOfDigits.length - 1; i > 0; i--) {
			if (indexOfDigits[i] < CHARACTERS.length)
				continue;
			
			//进位，如003->010，222->300
			indexOfDigits[i] -= CHARACTERS.length;
			indexOfDigits[i - 1] += 1;
		}
		//说明所有情况都已生成
		if (indexOfDigits[0] >= CHARACTERS.length)
			return null;
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < indexOfDigits.length; i++) {
			stringBuilder.append(CHARACTERS[indexOfDigits[i]]);
		}
		indexOfDigits[indexOfDigits.length - 1]++;
		return stringBuilder.toString();
	}

	public boolean isHappyString(String string) {
		for (int i = 0; i < string.length() - 1; i++) {
			boolean isValidCharacter = false;
			for (char c : CHARACTERS) {
				if (c == string.charAt(i)) {
					isValidCharacter = true;
					break;
				}
			}

			if (!isValidCharacter)
				return false;
			if (string.charAt(i) == string.charAt(i + 1))
				return false;
		}
		return true;
	}
}

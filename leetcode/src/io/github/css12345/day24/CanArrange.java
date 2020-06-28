package io.github.css12345.day24;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 5449.检查数组对是否可以被 k 整除<br>
 * https://leetcode-cn.com/problems/check-if-array-pairs-are-divisible-by-k/
 * @author cs
 *
 */
public class CanArrange {

	public static void main(String[] args) {
		int[][] testArrays = { { 1, 2, 3, 4, 5, 10, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6 }, { 1, 2, 3, 4, 5, 6 },
				{ -10, 10 }, { -1, 1, -2, 2, -3, 3, -4, 4 }, { -4, -7, 5, 2, 9, 1, 10, 4, -8, -3 },
				{ 75, 5, -5, 75, -2, -3, 88, 10, 10, 87 } };
		int[] testKs = { 5, 7, 10, 2, 3, 3, 85 };
		boolean[] testResults = { true, true, false, true, true, true, true };
		for (int i = 0; i < testArrays.length; i++)
			assertEquals(testResults[i], new CanArrange().canArrange(testArrays[i], testKs[i]));
	}

	public boolean canArrange(int[] arr, int k) {
		int[] remainders = new int[k];
		for (int element : arr) {
			int remainder = element % k;
			if (remainder < 0)
				remainder = remainder + k;
			remainders[remainder]++;
		}
		
		if (remainders[0] % 2 != 0)
			return false;
		if (k % 2 == 0 && remainders[k / 2] % 2 != 0)
			return false;
		
		int limit = k % 2 == 0 ? k / 2 - 1 : k / 2;
		for (int i = 1; i <= limit; i++)
			if (remainders[i] != remainders[k - i])
				return false;
		return true;
	}
}
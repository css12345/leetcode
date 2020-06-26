package io.github.css12345.day22;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 剑指 Offer 56-II.数组中数字出现的次数 II
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 * @author cs
 *
 */
public class SingleNumber {

	public static void main(String[] args) {
		int[][] testDatas = { { 3, 4, 3, 3 }, { 9, 1, 7, 9, 7, 9, 7 } };
		int[] testResults = { 4, 1 };
		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(testResults[i], new SingleNumber().singleNumber(testDatas[i]));
		}
	}

	public int singleNumber(int[] nums) {
		int result = 0;
		for (int i = 1; i <= 31; i++) {
			int sum = 0;
			for (int num :nums) {
				sum += num >> (i - 1) & 1;
			}
			sum %= 3;
			result += sum * (1 << (i - 1));
		}
		return result;
	}
}

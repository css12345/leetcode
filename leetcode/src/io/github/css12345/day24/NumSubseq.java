package io.github.css12345.day24;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * leetcode 5450.满足条件的子序列数目<br>
 * https://leetcode-cn.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
 * @author cs
 *
 */
public class NumSubseq {

	public static void main(String[] args) {
		int[][] testArrays = { { 3, 5, 6, 7 }, { 3, 3, 6, 8 }, { 2, 3, 3, 4, 6, 7 }, { 5, 2, 4, 1, 7, 6, 8 }, { 1 },
				{ 14, 4, 6, 6, 20, 8, 5, 6, 8, 12, 6, 10, 14, 9, 17, 16, 9, 7, 14, 11, 14, 15, 13, 11, 10, 18, 13, 17,
						17, 14, 17, 7, 9, 5, 10, 13, 8, 5, 18, 20, 7, 5, 5, 15, 19, 14 } };

		int[] testTargets = { 9, 10, 12, 16, 1, 22 };
		int[] testResults = { 4, 6, 61, 127, 0, 272187084 };
		for (int i = 0; i < testArrays.length; i++) {
			assertEquals(testResults[i], new NumSubseq().numSubseq(testArrays[i], testTargets[i]));
		}
	}

	public int numSubseq(int[] nums, int target) {
		Arrays.sort(nums);
		int sum = 0;
		int mod = (int) (1e9 + 7);
		for (int i = 0; i < nums.length && nums[i] <= target; i++) {
			int value = target - nums[i];
			if (value < nums[i] || i == nums.length - 1) {
				if (nums[i] * 2 <= target) {
					sum++;
					sum %= mod;
				}
				continue;
			}
			int index = binarySearch(nums, value, i + 1, nums.length - 1);
			if (index < 0)
				continue;
			int count = index - i;
			sum += quickPow(2, count, mod);
			sum %= mod;
		}
		return sum;
	}

	private long quickPow(int x, int n, int mod) {
		if (n == 0)
			return 1;
		if (n == 1)
			return x % mod;
		long result = quickPow(x, n / 2, mod);
		result *= result;
		result %= mod;
		if (n % 2 == 1) {
			result *= x;
			result %= mod;
		}
		return result;
	}

	/**
	 * 在数组nums的[left,right]范围查找小于等于value的最后一个元素下标
	 * 
	 * @return left或right范围有问题返回-1,其他情况返回nums的[left,right]范围小于等于value的最后一个元素下标
	 */
	private int binarySearch(int[] nums, int value, int left, int right) {
		if (left > right || left < 0 || right >= nums.length)
			return -1;
		int i = left, j = right;
		while (i <= j) {
			int mid = (i + j) / 2;
			if (nums[mid] <= value)
				i = mid + 1;
			else
				j = mid - 1;
		}
		return j;
	}
}

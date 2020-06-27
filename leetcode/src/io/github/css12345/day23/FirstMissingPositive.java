package io.github.css12345.day23;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 41.缺失的第一个正数<br>
 * https://leetcode-cn.com/problems/first-missing-positive/
 * @author cs
 *
 */
public class FirstMissingPositive {

	public static void main(String[] args) {
		int[][] testDatas = { { 1, 2, 0 }, { 3, 4, -1, 1 }, { 7, 8, 9, 11, 12 }, { 1 }, { 1, 1 } };
		int[] testResults = { 3, 2, 1, 2, 2 };
		for (int i = 0; i < testDatas.length; i++)
			assertEquals(testResults[i], new FirstMissingPositive().firstMissingPositive(testDatas[i]));
	}

	public int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length == 0)
			return 1;

		for (int i = 0; i < nums.length;) {
			if (nums[i] < 1 || nums[i] > nums.length || nums[i] == i + 1)
				i++;
			else {
				// 交换元素，如果交换后的位置已经没问题了，直接i+1
				if (nums[nums[i] - 1] == nums[i])
					i++;
				else {
					int temp = nums[nums[i] - 1];
					nums[nums[i] - 1] = nums[i];
					nums[i] = temp;
				}
			}
		}

		for (int i = 0; i < nums.length; i++)
			if (nums[i] != i + 1)
				return i + 1;
		return nums.length + 1;
	}
}

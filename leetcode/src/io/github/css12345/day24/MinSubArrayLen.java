package io.github.css12345.day24;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * leetcode 209.长度最小的子数组<br>
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * @author cs
 *
 */
public class MinSubArrayLen {

	public static void main(String[] args) {
		int s1 = 7;
		int[] nums1 = {2,3,1,2,4,3};
		assertEquals(2, new MinSubArrayLen().minSubArrayLen(s1, nums1));
		int s2 = 11;
		int[] nums2 = {1,2,3,4,5};
		assertEquals(3, new MinSubArrayLen().minSubArrayLen(s2, nums2));
	}

	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		
		int[] sum = new int[nums.length];
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++)
			sum[i] = sum[i - 1] + nums[i];
		
		int index = Arrays.binarySearch(sum, s);
		if (index < 0)
			index = -(index + 1);
		if (index == sum.length)
			return 0;
		
		int minLen = index + 1;
		for (int i = 1; i < nums.length; i++) {
			int minValue = sum[i - 1] + s;
			index = Arrays.binarySearch(sum, minValue);
			if (index < 0)
				index = -(index + 1);
			if (index == sum.length)
				continue;
			
			minLen = Math.min(minLen, index - i + 1);
		}
		return minLen;
    }
}

package io.github.css12345.day20;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * leetcode 16.最接近的三数之和<br>
 * https://leetcode-cn.com/problems/3sum-closest/
 * @author cs
 *
 */
public class ThreeSumClosest {

	public static void main(String[] args) {
		assertEquals(2, new ThreeSumClosest().threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
	}

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int threeSum = nums[0] + nums[1] + nums[2];
		int closestValue = Math.abs(target - threeSum);
		for (int i = 0; i < nums.length - 1; i++) {
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				if (nums[i] + nums[left] + nums[right] - target == 0)
					return nums[i] + nums[left] + nums[right];
				else if (nums[i] + nums[left] + nums[right] - target < 0) {
					if (target - (nums[i] + nums[left] + nums[right]) < closestValue) {
						threeSum = nums[i] + nums[left] + nums[right];
						closestValue = target - threeSum;
					}
					left++;
				}
				else {
					if ((nums[i] + nums[left] + nums[right]) - target < closestValue) {
						threeSum = nums[i] + nums[left] + nums[right];
						closestValue = threeSum - target;
					}
					right--;
				}
			}
			
		}
		return threeSum;
	}
}

package pers.cs.day26;

import static org.junit.Assert.assertEquals;

public class Jump {

	public static void main(String[] args) {
		int[] nums = {2,3,1,1,4};
		assertEquals(2, new Jump().jump(nums));
	}

	public int jump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		return solve2(nums);
	}

	/**
	 * https://leetcode-cn.com/submissions/detail/23090860/
	 *
	 */
	private int solve2(int[] nums) {
		int jump = 0,cur = 0,next = 0;
		for (int i = 0;i < nums.length;i++) {
			if (cur < i) {
				cur = next;
				jump++;
			}
			next = Math.max(next, i + nums[i]);
		}
		return jump;
	}

	/**
	 * https://leetcode-cn.com/submissions/detail/23090618/
	 */
	private int solve1(int[] nums) {
		int[] dp = new int[nums.length];
		for (int i = nums.length - 2;i >= 0;i--) {
			int minValue = dp[i + 1];
			for (int j = 2;j <= nums[i] && i + j < nums.length;j++) {
				minValue = Math.min(minValue, dp[i + j]);
			}
			dp[i] = minValue + 1;
		}
		return dp[0];	
	}
}

package pers.cs.day15;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[][] testDatas = {{2,1,5,3,6,4,8,9,7},{10,9,2,5,3,7,101,18},{2,2}};
		int[] results = {5,4,1};
		int[][] resultDatas = {{1,3,4,8,9},{2,3,7,101},{2}};
		
		for(int i = 0;i < testDatas.length;i++) {
			LongestIncreasingSubsequence subquence = new LongestIncreasingSubsequence();
			assertEquals(results[i], subquence.lengthOfLIS(testDatas[i]));
			assertArrayEquals(resultDatas[i], subquence.getDatasFromDP(testDatas[i],subquence.solution2(testDatas[i])));
		}
	}

	private int[] getDatasFromDP(int[] nums, int[] dp) {
		int maxValuePos = 0,maxLen = dp[0];
		for(int i = 1;i < dp.length;i++)
			if(dp[i] > maxLen) {
				maxLen = dp[i];
				maxValuePos = i;
			}
		
		int[] sequence = new int[maxLen];
		sequence[--maxLen] = nums[maxValuePos];
		for(int i = maxValuePos - 1;maxLen > 0;i--) {
			if(nums[i] < nums[maxValuePos] && dp[i] == dp[maxValuePos] - 1) {
				sequence[--maxLen] = nums[i];
				maxValuePos = i;
			}
		}
		return sequence;
	}

	public int lengthOfLIS(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		int[] dp = solution2(nums);
		int result = dp[0];
		for(int i = 1;i < dp.length;i++) {
			result = Math.max(result, dp[i]);
		}
		return result;
	}

	/**
	 * O(N2)解法
	 * https://leetcode-cn.com/submissions/detail/22071055/
	 */
	private int[] solution1(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = 1;
		for(int i = 1;i < nums.length;i++) {
			int maxValue = 0;
			for(int j = 0;j < i;j++)
				if(nums[j] < nums[i] && dp[j] > maxValue) {
					maxValue = dp[j];
				}
			dp[i] = maxValue + 1;
		}
		return dp;	
	}
	
	/**
	 * O(NlogN)解法
	 * https://leetcode-cn.com/submissions/detail/22073310/
	 */
	private int[] solution2(int[] nums) {
		int[] dp = new int[nums.length];
		int[] ends = new int[nums.length];
		ends[0] = nums[0];
		int right = 0;
		dp[0] = 1;
		for(int i = 1;i < nums.length;i++) {
			//二分查找大于等于nums[i]的值，返回其位置，没有返回-1
			int pos = halfSearch(ends,0,right,nums[i]);
			if(pos == -1) {
				ends[++right] = nums[i];
				dp[i] = right + 1;
			}
			else {
				ends[pos] = nums[i];
				dp[i] = pos + 1;
			}
		}
		return dp;	
	}

	/**
	 * 在ends数组中的left-right范围查找大于等于nums[i]的值，返回其位置，没有返回-1
	 */
	private int halfSearch(int[] ends, int left, int right, int searchedElement) {
		int copyOfRight = right;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(searchedElement > ends[mid])
				left = mid + 1;
			else 
				right = mid - 1;
		}
		if(left > copyOfRight || ends[left] < searchedElement)
			return -1;
		return left;
	}
}

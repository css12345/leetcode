package io.github.css12345.day9;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 1330.翻转子数组得到最大的数组值<br>
 * https://leetcode-cn.com/problems/reverse-subarray-to-maximize-array-value/
 * @author cs
 *
 */
public class MaxValueAfterReverse {

	public static void main(String[] args) {
		int[][] testDatas = { { 2, 3, 1, 5, 4 }, { 2, 4, 9, 24, 2, 1, 10 } };
		int[] testResults = { 10, 68 };
		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(testResults[i], new MaxValueAfterReverse().maxValueAfterReverse(testDatas[i]));
		}
	}

	public int maxValueAfterReverse(int[] nums) {
		if (nums.length == 1) {
			return 0;
		}
		
		int initValue = 0;
		for (int i = 0; i < nums.length - 1; i++) 
			initValue += Math.abs(nums[i] - nums[i + 1]);
		
		int maxChangedValue = 0;
//      暴力枚举超时
//		for (int reverseLen = 2; reverseLen <= nums.length - 1; reverseLen++) {
//			for (int startPos = 0; startPos <= nums.length - reverseLen; startPos++) {
//				int endPos = startPos + reverseLen - 1;
//				int changedValue = 0;
//				if (startPos >= 1)
//					changedValue += Math.abs(nums[startPos - 1] - nums[endPos]) - Math.abs(nums[startPos - 1] - nums[startPos]);
//				if (endPos <= nums.length - 2)
//					changedValue += Math.abs(nums[startPos] - nums[endPos + 1]) - Math.abs(nums[endPos] - nums[endPos + 1]);
//				maxChangedValue = Math.max(maxChangedValue, changedValue);
//			}
//		}
		
		//参考题解https://leetcode-cn.com/problems/reverse-subarray-to-maximize-array-value/solution/mei-ju-jue-dui-zhi-zhan-kai-hou-de-fu-hao-on-by-lu/
		//首先处理包含两个端点的情况
		for (int endPos = 1; endPos <= nums.length - 2; endPos++) {
			int changedValue = Math.abs(nums[0] - nums[endPos + 1]) - Math.abs(nums[endPos] - nums[endPos + 1]);
			maxChangedValue = Math.max(maxChangedValue, changedValue);
		}
		for (int startPos = nums.length - 2; startPos >= 1; startPos--) {
			int changedValue = Math.abs(nums[startPos - 1] - nums[nums.length - 1]) - Math.abs(nums[startPos - 1] - nums[startPos]);
			maxChangedValue = Math.max(maxChangedValue, changedValue);
		}
		
		int[][] thresholds = {{1,1},{1,-1},{-1,1},{-1,-1}};
		for (int[] threshold : thresholds) {
			int maxGJ = Integer.MIN_VALUE;
			for (int i = 1; i <= nums.length - 1; i++) {
				if (i > 1)
					maxChangedValue = Math.max(maxChangedValue, threshold[0] * nums[i] + threshold[1] * nums[i - 1] - Math.abs(nums[i] - nums[i - 1]) + maxGJ);
				maxGJ = Math.max(maxGJ, -threshold[0] * nums[i] - threshold[1] * nums[i - 1] - Math.abs(nums[i] - nums[i - 1]));
			}
		}
		return initValue + maxChangedValue;
    }
}

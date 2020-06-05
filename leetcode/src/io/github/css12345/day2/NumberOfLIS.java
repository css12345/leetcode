package io.github.css12345.day2;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 673.最长递增子序列的个数<br>
 * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
 * @author cs
 *
 */
public class NumberOfLIS {

	public static void main(String[] args) {
		int[][] testDatas = { { 2, 1, 5, 3, 6, 4, 8, 9, 7 }, { 5, 3, 2, 4, 4, 7, 6 }, { 1, 3, 5, 4, 7 },
				{ 2, 2, 2, 2, 2 }, { 5, 3, 2, 4, 7, 6 } };
		int[] testResults = { 6, 8, 2, 5, 4 };
		for (int i = 0; i < testDatas.length; i++)
			assertEquals(testResults[i], new NumberOfLIS().findNumberOfLIS(testDatas[i]));
	}

	public int findNumberOfLIS(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		
		PosInformation[] posInformations = new PosInformation[nums.length];
		for (int i = 0; i < posInformations.length; i++)
			posInformations[i] = new PosInformation();

		posInformations[0].length = 1;
		posInformations[0].possibles = 1;
		for (int i = 1; i < nums.length; i++) {
			// 从posInformation数组查找0-i-1范围值比nums[i]小且dp值最大元素
			int maxValue = 0;
			for (int j = 0; j <= i - 1; j++) {
				if (nums[j] < nums[i])
					maxValue = Math.max(maxValue, posInformations[j].length);
			}

			posInformations[i].length = maxValue + 1;
			if (posInformations[i].length == 1)
				posInformations[i].possibles = 1;
			else {
				for (int j = 0; j <= i - 1; j++) {
					if (nums[j] < nums[i] && posInformations[j].length == maxValue) {
						posInformations[i].possibles = posInformations[i].possibles + posInformations[j].possibles;
					}
				}
			}
		}

		int lisLength = posInformations[0].length;
		for (int i = 1; i < posInformations.length; i++) {
			lisLength = Math.max(lisLength, posInformations[i].length);
		}
		
		//查找所有等于lisLength的，计算所有可能
		int count = 0;
		for (int i = 0; i < posInformations.length; i++) {
			if (posInformations[i].length == lisLength) {
				count += posInformations[i].possibles;
			}
		}
		return count;
	}

	private static class PosInformation {
		//以该位置结尾的最长上升子序列长度
		int length;
		//以该位置结尾的最长上升子序列有多少种可能
		int possibles = 0;
	}

}

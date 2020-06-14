package io.github.css12345.day10;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * leetcode 1300.转变数组后最接近目标值的数组和<br>
 * https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/
 * @author cs
 *
 */
public class FindBestValue {

	public static void main(String[] args) {
		int[][] testArrays = { { 4, 9, 10 }, { 2, 3, 5 }, { 60864, 25176, 27249, 21296, 20204 }, { 2, 3, 4, 5 } };
		int[] testTargets = { 10, 10, 56803, 13 };
		int[] testResults = { 3, 5, 11361, 4 };

		for (int i = 0; i < testArrays.length; i++) {
			assertEquals(testResults[i], new FindBestValue().findBestValue(testArrays[i], testTargets[i]));
		}
	}

	public int findBestValue(int[] arr, int target) {
		Arrays.sort(arr);
		int sum = 0;
		for (int i = 0; i < arr.length; i++)
			sum += arr[i];
		if (sum <= target)
			return arr[arr.length - 1];

		int elementNum = arr.length, startPos = 0;

		while (true) {
			int averageValue = target / elementNum;
			if (averageValue * elementNum < target) {
				int value1 = target - averageValue * elementNum;
				int value2 = (averageValue + 1) * elementNum - target;
				if (value2 < value1)
					averageValue++;
			}

			if (arr[startPos] > averageValue)
				return averageValue;

			while (arr[startPos] <= averageValue) {
				target -= arr[startPos];
				elementNum--;
				startPos++;
			}
		}
	}
}

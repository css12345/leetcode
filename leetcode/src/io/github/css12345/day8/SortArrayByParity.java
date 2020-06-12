package io.github.css12345.day8;

/**
 * leetcode 905.按奇偶排序数组<br>
 * https://leetcode-cn.com/problems/sort-array-by-parity/
 * @author cs
 *
 */
public class SortArrayByParity {

	public static void main(String[] args) {
		int[] testData = new int[] { 3, 1, 2, 4 };
		int[] testResult = new SortArrayByParity().sortArrayByParity(testData);
		int firstOddIndex = 0;
		for (int i = 0; i < testResult.length; i++)
			if (testResult[i] % 2 == 1) {
				firstOddIndex = i;
				break;
			}
		for (int i = 0; i < firstOddIndex; i++)
			if (testResult[i] % 2 == 1)
				throw new IllegalStateException("前一半为偶数，但第" + (i + 1) + "项为" + testResult[i]);
		for (int i = firstOddIndex; i < testResult.length; i++)
			if (testResult[i] % 2 == 0)
				throw new IllegalStateException("后一半为奇数，但第" + (i + 1) + "项为" + testResult[i]);
	}

	public int[] sortArrayByParity(int[] A) {
		int left = 0, right = A.length - 1;
		while (left < right) {
			while (A[left] % 2 == 0 && left < A.length - 1)
				left++;
			while (A[right] % 2 == 1 && right > 0)
				right--;
			
			if (left < right) {
				int temp = A[left];
				A[left] = A[right];
				A[right] = temp;
			}
		}
		return A;
	}
}

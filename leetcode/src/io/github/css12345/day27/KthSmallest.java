package io.github.css12345.day27;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 378.有序矩阵中第K小的元素<br>
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * @author cs
 */
public class KthSmallest {

	public static void main(String[] args) {
		assertEquals(13, new KthSmallest().kthSmallest(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
	}

	public int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0)
			return 0;
		List<Integer> list = new ArrayList<>(matrix.length * matrix.length);
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				list.add(matrix[i][j]);
		Collections.sort(list);
		return list.get(k - 1);
	}
}

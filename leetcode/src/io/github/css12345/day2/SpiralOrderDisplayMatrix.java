package io.github.css12345.day2;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题29. 顺时针打印矩阵<br>
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 * @author cs
 *
 */
public class SpiralOrderDisplayMatrix {

	public static void main(String[] args) {
		int[][] testData1 = {{1,2,3},{4,5,6},{7,8,9}};
		int[] testResult1 = {1,2,3,6,9,8,7,4,5};
		assertArrayEquals(testResult1, new SpiralOrderDisplayMatrix().spiralOrder(testData1));
		
		int[][] testData2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		int[] testResult2 = {1,2,3,4,8,12,11,10,9,5,6,7};
		assertArrayEquals(testResult2, new SpiralOrderDisplayMatrix().spiralOrder(testData2));
	}

	public int[] spiralOrder(int[][] matrix) {
		if (matrix.length == 0)
			return new int[0];
		
		List<Integer> spiralOrderDatas = new ArrayList<>(matrix.length * matrix[0].length);
		
		displayAsSpiralOrder(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, spiralOrderDatas);
		
		int[] result = new int[spiralOrderDatas.size()];
		for (int i = 0; i < spiralOrderDatas.size(); i++) {
			result[i] = spiralOrderDatas.get(i);
		}
		return result;
    }

	private void displayAsSpiralOrder(int[][] matrix, int startRow, int startColumn, int endRow, int endColumn, List<Integer> spiralOrderDatas) {
		if (startRow > endRow || startColumn > endColumn)
			return;
		
		int notDisplayedRow = endRow - startRow + 1;
		int notDisplayedColumn = endColumn - startColumn + 1;
		
		//只剩1行1列
		if (notDisplayedRow == 1 && notDisplayedColumn == 1) {
			spiralOrderDatas.add(matrix[startRow][startRow]);
			return;
		}
		
		//只剩1行，将startColumn-endColumn所有元素添加至列表
		if (notDisplayedRow == 1) {
			for (int i = startColumn; i <= endColumn; i++) {
				spiralOrderDatas.add(matrix[startRow][i]);
			}
			return;
		}
		
		//只剩1列，将startRow-endRow所有元素添加至列表
		if (notDisplayedColumn == 1) {
			for (int i = startRow; i <= endRow; i++) {
				spiralOrderDatas.add(matrix[i][startColumn]);
			}
			return;
		}
		
		//添加startRow的startColumn-endColumn
		for (int i = startColumn; i <= endColumn; i++) {
			spiralOrderDatas.add(matrix[startRow][i]);
		}
		
		//添加endColumn的startRow+1-endRow
		for (int i = startRow + 1; i <= endRow; i++) {
			spiralOrderDatas.add(matrix[i][endColumn]);
		}
		
		//添加endRow的endColumn-1-startColumn
		for (int i = endColumn - 1; i >= startColumn; i--) {
			spiralOrderDatas.add(matrix[endRow][i]);
		}
		
		//添加startColumn的endRow-1-startRow+1
		for (int i = endRow - 1; i >= startRow + 1; i--) {
			spiralOrderDatas.add(matrix[i][startColumn]);
		}
		
		startRow++;
		endRow--;
		startColumn++;
		endColumn--;
		displayAsSpiralOrder(matrix, startRow, startColumn, endRow, endColumn, spiralOrderDatas);
	}
}

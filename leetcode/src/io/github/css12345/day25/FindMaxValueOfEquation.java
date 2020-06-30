package io.github.css12345.day25;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 1499.满足不等式的最大值
 * https://leetcode-cn.com/problems/max-value-of-equation/
 * @author cs
 *
 */
public class FindMaxValueOfEquation {

	public static void main(String[] args) {
		int[][] points1 = { { 1, 3 }, { 2, 0 }, { 5, 10 }, { 6, -10 } };
		assertEquals(4, new FindMaxValueOfEquation().findMaxValueOfEquation(points1, 1));

		int[][] points2 = { { 0, 0 }, { 3, 0 }, { 9, 2 } };
		assertEquals(3, new FindMaxValueOfEquation().findMaxValueOfEquation(points2, 3));
	}

	private static class SegmentTreeNode {
		private SegmentTreeNode leftChild, rightChild;
		private int left, right, maxValue;

		public SegmentTreeNode(int value, int left, int right) {
			this.maxValue = value;
			this.left = left;
			this.right = right;
		}

		public static SegmentTreeNode build(int[] values, int leftRange, int rightRange) {
			if (leftRange == rightRange) {
				SegmentTreeNode node = new SegmentTreeNode(values[leftRange], leftRange, rightRange);
				return node;
			}

			int mid = (leftRange + rightRange) / 2;
			SegmentTreeNode leftChildNode = build(values, leftRange, mid);
			SegmentTreeNode rightChildNode = build(values, mid + 1, rightRange);
			SegmentTreeNode node = new SegmentTreeNode(Math.max(leftChildNode.maxValue, rightChildNode.maxValue),
					leftRange, rightRange);
			node.leftChild = leftChildNode;
			node.rightChild = rightChildNode;
			return node;
		}

		public int query(int left, int right) {
			return query(this, left, right);
		}

		private int query(SegmentTreeNode segmentTreeNode, int left, int right) {
			if (left <= segmentTreeNode.left && segmentTreeNode.right <= right)
				return segmentTreeNode.maxValue;

			if (segmentTreeNode.right < left || segmentTreeNode.left > right)
				return Integer.MIN_VALUE;
			return Math.max(query(segmentTreeNode.leftChild, left, right),
					query(segmentTreeNode.rightChild, left, right));
		}
	}

	public int findMaxValueOfEquation(int[][] points, int k) {
		int[] ySubX = new int[points.length];
		for (int i = 0; i < ySubX.length; i++)
			ySubX[i] = points[i][1] - points[i][0];

		SegmentTreeNode segmentTree = SegmentTreeNode.build(ySubX, 0, ySubX.length - 1);
		int result = Integer.MIN_VALUE;
		for (int j = points.length - 1; j >= 0; j--) {
			int index = binarySearch(points, points[j][0] - k, 0, j - 1);
			if (index < 0 || index > j - 1)
				continue;
			int maxYSubX = segmentTree.query(index, j - 1);
			result = Math.max(result, points[j][0] + points[j][1] + maxYSubX);
		}
		return result;
	}

	// 查找大于等于value的第一个元素
	private int binarySearch(int[][] points, int value, int left, int right) {
		if (left > right || left < 0 || right >= points.length)
			return -1;
		int i = left, j = right;
		while (i <= j) {
			int mid = (i + j) / 2;
			if (points[mid][0] < value)
				i = mid + 1;
			else
				j = mid - 1;
		}
		return i;
	}
}

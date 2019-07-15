package pers.cs.day25;

import static org.junit.Assert.assertEquals;

public class CardGame {
	public static void main(String[] args) {
		int[][] testDatas = { { 1, 2, 100, 4 }, { 1, 100, 2 } };
		int[] result = { 101, 100 };
		for (int i = 0; i < testDatas.length; i++)
			assertEquals(result[i], new CardGame().cardGame(testDatas[i], testDatas[i].length));
	}

	public int cardGame(int[] A, int n) {
		if (A == null || A.length == 0 || n == 0 || A.length != n)
			return 0;
//		return win1(A);
		return win2(A);
	}
	
	
	/**
	 * 动态规划
	 * https://www.nowcoder.com/profile/6394872/codeBookDetail?submissionId=50832361
	 */
	private int win2(int[] a) {
		int[][] f = new int[a.length][a.length],s = new int[a.length][a.length];
		for (int j = 0;j < a.length;j++) {
			f[j][j] = a[j];
			for (int i = j - 1;i >= 0;i--) {
				f[i][j] = Math.max(a[i] + s[i + 1][j], a[j] + s[i][j - 1]);
				s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
			}
		}
		return Math.max(f[0][a.length - 1], s[0][a.length - 1]);
	}

	/**
	 * 递归解法 超时，用记忆化搜索
	 * https://www.nowcoder.com/profile/6394872/codeBookDetail?submissionId=50831944
	 */
	private int win1(int[] a) {
		int[][] fMap = new int[a.length][a.length], sMap = new int[a.length][a.length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length; j++) {
				fMap[i][j] = -1;
				sMap[i][j] = -1;
			}
		return Math.max(f(a, 0, a.length - 1, fMap, sMap), s(a, 0, a.length - 1, fMap, sMap));
	}

	private int s(int[] a, int left, int right, int[][] fMap, int[][] sMap) {
		if (left == right)
			return 0;
		if (sMap[left][right] != -1)
			return sMap[left][right];
		
		int result1 = f(a, left + 1, right, fMap, sMap);
		int result2 = f(a, left, right - 1, fMap, sMap);
		sMap[left][right] = Math.min(result1, result2);
		return sMap[left][right];
	}

	private int f(int[] a, int left, int right, int[][] fMap, int[][] sMap) {
		if (left == right)
			return a[left];
		if (fMap[left][right] != -1)
			return fMap[left][right];
		
		int result1 = s(a, left + 1, right, fMap, sMap) + a[left];
		int result2 = s(a, left, right - 1, fMap, sMap) + a[right];
		fMap[left][right] = Math.max(result1, result2);
		return fMap[left][right];
	}
}

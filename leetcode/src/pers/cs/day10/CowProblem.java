package pers.cs.day10;

import java.util.Scanner;

public class CowProblem {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			int n = input.nextInt();
			if (n == 0)
				break;

//			System.out.println(countCows(n));
			System.out.println(countCowsWithQuickPow(n));
		}
		input.close();
	}

	/**
	 * O(log(n))解法,使用矩阵快速幂,详见https://vjudge.net/solution/20171258
	 * 
	 * @param n 第几年
	 * @return 牛的个数
	 */
	private static int countCowsWithQuickPow(int n) {
		if (n < 4)
			return n;
		int[][] base = { { 1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 } };
		int[] init = { 3, 2, 1 }; // [f(3),f(2),f(1)]

		base = quickPow(base, n - 3); // 快速幂求base^(n-3)

		int result = 0;
		for (int i = 0; i < init.length; i++) {
			result += init[i] * base[i][0];
		}
		return result;
	}

	/**
	 * 求base^n次方
	 */
	private static int[][] quickPow(int[][] base, int n) {
		int[][] result = new int[base.length][base[0].length];
		for (int i = 0; i < result.length; i++)
			result[i][i] = 1;
		if (n == 0)
			return result;
		int[][] mid = quickPow(base, n / 2);
		if (n % 2 == 0)
			return multiply(mid, mid);

		return multiply(base, multiply(mid, mid));
	}

	/**
	 * 矩阵乘法
	 */
	private static int[][] multiply(int[][] array1, int[][] array2) {
		int[][] result = new int[array1.length][array2[0].length];
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result[i].length; j++)
				for (int k = 0; k < array1[0].length; k++)
					result[i][j] += (array1[i][k] * array2[k][j]);
		return result;
	}

	/**
	 * O(n)解法,详见https://vjudge.net/solution/20171094
	 * 
	 * @param n 第几年
	 * @return 牛的个数
	 */
	private static int countCows(int n) {
		if (n < 4)
			return n;

		int fN1 = 3, fN2 = 2, fN3 = 1; // 分别对应f(n-1),f(n-2),f(n-3)
		for (int i = 4; i <= n; i++) {
			int tmp = fN1;
			fN1 = fN1 + fN3;
			fN3 = fN2;
			fN2 = tmp;
		}
		return fN1;
	}

}

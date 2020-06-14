package io.github.css12345.day10;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 5438.制作 m束花所需的最少天数<br>
 * https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/
 * @author cs
 *
 */
public class MinDays {

	public static void main(String[] args) {
		assertEquals(3, new MinDays().minDays(new int[] { 1, 10, 3, 10, 2 }, 3, 1));
		assertEquals(-1, new MinDays().minDays(new int[] { 1, 10, 3, 10, 2 }, 3, 2));
		assertEquals(12, new MinDays().minDays(new int[] { 7, 7, 7, 7, 12, 7, 7 }, 2, 3));
		assertEquals(1000000000, new MinDays().minDays(new int[] { 1000000000, 1000000000 }, 1, 1));
		assertEquals(9, new MinDays().minDays(new int[] { 1, 10, 2, 9, 3, 8, 4, 7, 5, 6 }, 4, 2));
		assertEquals(98, new MinDays()
				.minDays(new int[] { 62, 75, 98, 63, 47, 65, 51, 87, 22, 27, 73, 92, 76, 44, 13, 90, 100, 85 }, 2, 7));
	}

	public int minDays(int[] bloomDay, int m, int k) {
		if (m * k > bloomDay.length)
			return -1;

		int minDay = bloomDay[0], maxDay = bloomDay[0];
		for (int day : bloomDay) {
			minDay = Math.min(minDay, day);
			maxDay = Math.max(maxDay, day);
		}

		while (minDay <= maxDay) {
			int midDay = (minDay + maxDay) / 2;
			// 计算midDay时可以采摘多少花
			int num = 0;
			for (int i = 0, j = 0; i < bloomDay.length; i++) {
				if (bloomDay[i] > midDay) {
					j = 0;
					continue;
				}

				j++;
				if (j == k) {
					num++;
					j = 0;
				}
			}

			if (num < m)
				minDay = midDay + 1;
			else
				maxDay = midDay - 1;
		}
		return minDay;
	}
}

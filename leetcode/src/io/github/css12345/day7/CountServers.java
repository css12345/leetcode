package io.github.css12345.day7;

import static org.junit.Assert.assertEquals;

/**
 * leetcode 1267.统计参与通信的服务器<br>
 * https://leetcode-cn.com/problems/count-servers-that-communicate/
 * @author cs
 *
 */
public class CountServers {

	public static void main(String[] args) {
		int[][][] testDatas = { { { 1, 0 }, { 0, 1 } }, { { 1, 0 }, { 1, 1 } },
				{ { 1, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } } };
		int[] testResults = { 0, 3, 4 };

		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(testResults[i], new CountServers().countServers(testDatas[i]));
		}
	}

	public int countServers(int[][] grid) {
		//rowServers[i]表示grid第i行有多少个1
		int[] rowServers = new int[grid.length];
		
		//columnServers[i]表示grid第i列有多少个1
		int[] columnServers = new int[grid[0].length];
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					rowServers[i]++;
					columnServers[j]++;
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					if (rowServers[i] > 1 || columnServers[j] > 1)
						count++;
				}
			}
		}
		return count;
	}
}

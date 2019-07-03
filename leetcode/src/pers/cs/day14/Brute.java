package pers.cs.day14;

import static org.junit.Assert.assertEquals;

/**
 * 暴力解法超时 
 * https://leetcode-cn.com/submissions/detail/21991522/
 * 
 * @author cs
 *
 */
public class Brute {

	public static void main(String[] args) {
		int[][] testDatas = { { 1, 2, 5 }, { 2 } };
		int[] testAmounts = { 5, 3 };
		int[] excepts = { 4, 0 };

		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(excepts[i], new Brute().change(testAmounts[i], testDatas[i]));
		}
	}

	public int change(int amount, int[] coins) {
		if (amount == 0)
			return 1;
		if (amount < 0 || coins == null || coins.length == 0)
			return 0;
		return process(coins, 0, amount);
	}

	private int process(int[] coins, int index, int amount) {
		if (index == coins.length) {
			if (amount == 0)
				return 1;
			else
				return 0;
		}
		int result = 0;
		for (int i = 0; i * coins[index] <= amount; i++) {
			result += process(coins, index + 1, amount - coins[index] * i);
		}
		return result;
	}

}

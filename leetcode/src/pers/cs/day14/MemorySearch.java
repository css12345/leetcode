package pers.cs.day14;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

/**
 * 记忆化搜索
 * https://leetcode-cn.com/submissions/detail/21992726/
 * @author cs
 *
 */
public class MemorySearch {
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

		Map<Argument, Integer> map = new HashMap<>();
		return process(coins, 0, amount, map);
	}

	class Argument {
		int index, amount;

		public Argument(int index, int amount) {
			this.index = index;
			this.amount = amount;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + amount;
			result = prime * result + index;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Argument other = (Argument) obj;
			if (amount != other.amount)
				return false;
			if (index != other.index)
				return false;
			return true;
		}
	}

	private int process(int[] coins, int index, int amount, Map<Argument, Integer> map) {
		if (index == coins.length) {
			if (amount == 0)
				return 1;
			else
				return 0;
		}
		int result = 0;
		for (int i = 0; i * coins[index] <= amount; i++) {
			Argument argument = new Argument(index + 1, amount - coins[index] * i);
			if (map.containsKey(argument))
				result += map.get(argument);
			else {
				int value = process(coins, index + 1, amount - coins[index] * i, map);
				map.put(argument, value);
				result += value;
			}
		}
		return result;
	}

}

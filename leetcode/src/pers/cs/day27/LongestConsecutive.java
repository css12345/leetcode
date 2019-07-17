package pers.cs.day27;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LongestConsecutive {

	public static void main(String[] args) {
		int[] nums = { 100, 4, 200, 1, 3, 2 };
		assertEquals(4, new LongestConsecutive().longestConsecutive(nums));
	}

	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		return solve2(nums);
	}

	/**
	 * https://leetcode-cn.com/submissions/detail/23193046/
	 */
	private int solve2(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int max = 1;
		for (int i = 0;i < nums.length;i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], 1);
				if (map.containsKey(nums[i] + 1))
					max = Math.max(max, merge(map,nums[i],nums[i] + 1));
				if (map.containsKey(nums[i] - 1))
					max = Math.max(max, merge(map, nums[i] - 1, nums[i]));
			}
		}
		return max;
	}

	private int merge(Map<Integer, Integer> map, int less, int more) {
		int left = less - map.get(less) + 1;
		int right = more + map.get(more) - 1;
		int len = right - left + 1;
		map.put(left, len);
		map.put(right, len);
		return len;
	}

	/**
	 * https://leetcode-cn.com/submissions/detail/23192604/
	 */
	private int solve1(int[] nums) {
		Set<Integer> treeSet = new TreeSet<>();
		for (int num : nums)
			treeSet.add(num);
		int[] newNums = new int[nums.length];
		int len = 0;
		for (Iterator<Integer> iterator = treeSet.iterator();iterator.hasNext();) {
			newNums[len++] = iterator.next();
		}
		
		int max = 1;
		int current = 1;
		for (int i = len - 1;i >= 1;i--) {
			if (newNums[i] == newNums[i - 1] + 1) {
				current++;
			} else {
				current = 1;
			}
			max = Math.max(max, current);
		}
		return max;
	}
}

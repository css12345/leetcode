package pers.cs.day24;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class GetDesiredNum {
	public static void main(String[] args) {
		GetDesiredNum getDesiredNum = new GetDesiredNum();
		String[] testDatas = { "1^0|0|1", "1"};
		boolean[] desires = { false, false };
		int[] expected = { 2, 0 };
		for (int i = 0; i < testDatas.length; i++)
			assertEquals(expected[i], getDesiredNum.getDesiredNum(testDatas[i], desires[i]));
	}

	public int getDesiredNum(String exp, boolean desired) {
		if (exp == null || "".equals(exp))
			return 0;
		if (!isValid(exp))
			return 0;
		int[][][] map = new int[exp.length()][exp.length()][2];
		for (int i = 0;i < map.length;i++) 
			for (int j = 0;j < map[i].length;j++)
				for (int k = 0;k < map[i][j].length;k++)
					map[i][j][k] = -1;
		return num2(exp, desired, 0, exp.length() - 1, map);
	}

	/**
	 * 记忆化搜索
	 * https://www.nowcoder.com/profile/6394872/codeBookDetail?submissionId=50739619
	 */
	private int num2(String exp, boolean desired, int left, int right, int[][][] map) {
		int d = desired == true ? 1 : 0;
		if (map[left][right][d] != -1)
			return map[left][right][d];
		
		if (left == right) {
			boolean bitValue =  exp.charAt(left) == '1' ? true : false;
			return bitValue == desired ? 1 : 0;
		}

		int sum = 0;
		for (int i = left + 1; i < right; i++) {
			char c = exp.charAt(i);
			if (c == '&') {
				if (desired == true) {
					sum += num2(exp, true, left, i - 1,map) * num2(exp, true, i + 1, right, map);
				}
				else {
					sum += num2(exp, false, left, i - 1,map) * num2(exp, false, i + 1, right, map);
					sum += num2(exp, false, left, i - 1,map) * num2(exp, true, i + 1, right, map);
					sum += num2(exp, true, left, i - 1,map) * num2(exp, false, i + 1, right, map);
				}
			} else if (c == '|') {
				if (desired == false) {
					sum += num2(exp, false, left, i - 1,map) * num2(exp, false, i + 1, right, map);
				}
				else {
					sum += num2(exp, true, left, i - 1,map) * num2(exp, true, i + 1, right, map);
					sum += num2(exp, false, left, i - 1,map) * num2(exp, true, i + 1, right, map);
					sum += num2(exp, true, left, i - 1,map) * num2(exp, false, i + 1, right, map);
				}	
			} else if (c == '^') {
				if (desired == true) {
					sum += num2(exp, true, left, i - 1,map) * num2(exp, false, i + 1, right, map);
					sum += num2(exp, false, left, i - 1,map) * num2(exp, true, i + 1, right, map);
				}
				else {
					sum += num2(exp, false, left, i - 1,map) * num2(exp, false, i + 1, right, map);
					sum += num2(exp, true, left, i - 1,map) * num2(exp, true, i + 1, right, map);
				}
			}
		}
		
		map[left][right][d] = sum;
		return sum;
	}

	/**
	 * 递归解法
	 * https://www.nowcoder.com/profile/6394872/codeBookDetail?submissionId=50732774
	 */
	private int num1(String exp, boolean desired, int left, int right) {
		if (left == right) {
			boolean bitValue =  exp.charAt(left) == '1' ? true : false;
			return bitValue == desired ? 1 : 0;
		}

		int sum = 0;
		for (int i = left + 1; i < right; i++) {
			char c = exp.charAt(i);
			if (c == '&') {
				if (desired == true) {
					sum += num1(exp, true, left, i - 1) * num1(exp, true, i + 1, right);
				}
				else {
					sum += num1(exp, false, left, i - 1) * num1(exp, false, i + 1, right);
					sum += num1(exp, false, left, i - 1) * num1(exp, true, i + 1, right);
					sum += num1(exp, true, left, i - 1) * num1(exp, false, i + 1, right);
				}
			} else if (c == '|') {
				if (desired == false) {
					sum += num1(exp, false, left, i - 1) * num1(exp, false, i + 1, right);
				}
				else {
					sum += num1(exp, true, left, i - 1) * num1(exp, true, i + 1, right);
					sum += num1(exp, false, left, i - 1) * num1(exp, true, i + 1, right);
					sum += num1(exp, true, left, i - 1) * num1(exp, false, i + 1, right);
				}	
			} else if (c == '^') {
				if (desired == true) {
					sum += num1(exp, true, left, i - 1) * num1(exp, false, i + 1, right);
					sum += num1(exp, false, left, i - 1) * num1(exp, true, i + 1, right);
				}
				else {
					sum += num1(exp, false, left, i - 1) * num1(exp, false, i + 1, right);
					sum += num1(exp, true, left, i - 1) * num1(exp, true, i + 1, right);
				}
			}
		}
		return sum;
	}

	private boolean isValid(String exp) {
		if (exp.length() % 2 == 0)
			return false;
		for (int i = 0; i < exp.length(); i += 2)
			if (exp.charAt(i) != '0' && exp.charAt(i) != '1')
				return false;
		List<Character> signs = Arrays.asList('&', '|', '^');
		for (int i = 1; i < exp.length(); i += 2)
			if (!signs.contains(exp.charAt(i)))
				return false;
		return true;
	}
}

package io.github.css12345.day6;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 面试题46.把数字翻译成字符串<br>
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 * @author cs
 *
 */
public class TranslateNum {

	public static void main(String[] args) {
		assertEquals(5, new TranslateNum().translateNum(12258));
		assertEquals(3, new TranslateNum().translateNum(2105480003));
	}
	
	private Map<String, Integer> numPossiblesMap = new HashMap<>();

	public int translateNum(int num) {
		for (int i = 0; i <= 9; i++) {
			numPossiblesMap.put(String.valueOf(i), 1);
			numPossiblesMap.put('0' + String.valueOf(i), 1);
		}
		for (int i = 10; i <= 25; i++) {
			numPossiblesMap.put(String.valueOf(i), 2);
		}
		for (int i = 26; i <= 99; i++) {
			numPossiblesMap.put(String.valueOf(i), 1);
		}
		
		String numInString = String.valueOf(num);
		return translateNum(numInString);
	}

	private int translateNum(String num) {
		if (numPossiblesMap.containsKey(num))
			return numPossiblesMap.get(num);
		
		if (Integer.valueOf(num.substring(0, 2)) >= 26 || num.charAt(0) == '0')
			return translateNum(num.substring(1));
		return translateNum(num.substring(1)) + translateNum(num.substring(2));
	}
}

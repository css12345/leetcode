package io.github.css12345.day19;

import static org.junit.Assert.assertEquals;
/**
 * leetcode 67.二进制求和
 * https://leetcode-cn.com/problems/add-binary/
 * @author cs
 *
 */
public class AddBinary {

	public static void main(String[] args) {
		assertEquals("100", new AddBinary().addBinary("11", "1"));
		assertEquals("10101", new AddBinary().addBinary("1010", "1011"));
		assertEquals("101", new AddBinary().addBinary("00001", "0100"));
		assertEquals("0", new AddBinary().addBinary("00000", "00"));
	}

	public String addBinary(String a, String b) {
		//去掉字符串前面的0
		a = replacePrefixZero(a);
		b = replacePrefixZero(b);
		if (a.length() < b.length()) {
			String temp = a;
			a = b;
			b = temp;
		}
		
		StringBuilder resultBuilder = new StringBuilder();
		int addBit = 0;
		for (int i = 1; i <= b.length(); i++) {
			int sum = a.charAt(a.length() - i) - '0' + b.charAt(b.length() - i) - '0' + addBit;
			addBit = sum / 2;
			sum = sum % 2;
			resultBuilder.append(sum);
		}
		
		for (int i = a.length() - b.length() - 1; i >= 0; i--) {
			int sum = a.charAt(i) - '0' + addBit;
			addBit = sum / 2;
			sum = sum % 2;
			resultBuilder.append(sum);
		}
		if (addBit != 0)
			resultBuilder.append(addBit);
		return resultBuilder.reverse().toString();
	}

	private String replacePrefixZero(String a) {
		//如果是全0的情况，返回"0"
		boolean isAllZero = true;
		for (int i = 0; i < a.length(); i++)
			if (a.charAt(i) != '0') {
				isAllZero = false;
				break;
			}
		if (isAllZero)
			return "0";
		
		//去掉前导0
		int firstNotPrefixZero = 0;
		while (a.charAt(firstNotPrefixZero) == '0') {
			firstNotPrefixZero++;
		}
		if (firstNotPrefixZero == 0)
			return a;
		return a.substring(firstNotPrefixZero);
	}
}

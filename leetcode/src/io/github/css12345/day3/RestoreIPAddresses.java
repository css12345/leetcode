package io.github.css12345.day3;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode93.复原IP地址<br>
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 * @author cs
 *
 */
public class RestoreIPAddresses {

	public static void main(String[] args) {
		String[] testDatas = { "1111111", "25525511135", "01000" };
		List<List<String>> testResults = new ArrayList<>(testDatas.length);
		testResults.add(new ArrayList<>(Arrays.asList("1.1.11.111", "1.1.111.11", "1.11.1.111", "1.11.11.11",
				"1.11.111.1", "1.111.1.11", "1.111.11.1", "11.1.1.111", "11.1.11.11", "11.1.111.1", "11.11.1.11",
				"11.11.11.1", "11.111.1.1", "111.1.1.11", "111.1.11.1", "111.11.1.1")));
		testResults.add(new ArrayList<>(Arrays.asList("255.255.11.135", "255.255.111.35")));
		testResults.add(new ArrayList<>(Arrays.asList("0.10.0.0")));
		
		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(testResults.get(i), new RestoreIPAddresses().restoreIpAddresses(testDatas[i]));
//			System.out.println("-------------------------------------------");
		}
	}

	public List<String> restoreIpAddresses(String s) {
		List<String> ipAddresses = new ArrayList<>();
		int len = s.length();
		//这3个位置将地址分为四部分，即[0,pos1),[pos1,pos2),[pos2,pos3),[pos4,len)
		int pos1, pos2, pos3;
		//按每个部分1位，至少需要长度为4；每个部分最多3位，长度最多12位
		if (len < 4 || len > 12)
			return ipAddresses;
		
		for (pos1 = 1; pos1 < Math.min(4, len - 2); pos1++) {
			for (pos2 = pos1 + 1; pos2 < Math.min(pos1 + 4, len - 1); pos2++) {
				for (pos3 = pos2 + 1; pos3 < Math.min(pos2 + 4, len); pos3++) {
//					System.out.println(pos1 + " " + pos2 + " " + pos3);
					String part1 = s.substring(0, pos1);
					String part2 = s.substring(pos1, pos2);
					String part3 = s.substring(pos2, pos3);
					String part4 = s.substring(pos3, len);
					
					if (!isValid(part1) || !isValid(part2) || !isValid(part3) || !isValid(part4))
						continue;
					
					String ipAddress = part1 + '.' + part2 + '.' + part3 + '.' + part4;
					ipAddresses.add(ipAddress);
				}
			}
		}
		return ipAddresses;
	}

	private boolean isValid(String part) {
		int number = Integer.parseInt(part);
		//避免00,03这类情况
		String parsedPart = String.valueOf(number);
		if (!part.equals(parsedPart))
			return false;
		return number >= 0 && number <= 255;
	}
}

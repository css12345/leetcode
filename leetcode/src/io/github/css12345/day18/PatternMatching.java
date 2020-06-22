package io.github.css12345.day18;

import static org.junit.Assert.assertEquals;
/**
 * leetcode面试题 16.18.模式匹配<br>
 * https://leetcode-cn.com/problems/pattern-matching-lcci/
 * @author cs
 *
 */
public class PatternMatching {

	public static void main(String[] args) {
		assertEquals(true, new PatternMatching().patternMatching("abba", "dogcatcatdog"));
		assertEquals(false, new PatternMatching().patternMatching("abba", "dogcatcatfish"));
		assertEquals(false, new PatternMatching().patternMatching("aaaa", "dogcatcatdog"));
		assertEquals(true, new PatternMatching().patternMatching("abba", "dogdogdogdog"));
		assertEquals(true, new PatternMatching().patternMatching("bbba", "xxxxxxy"));
		assertEquals(true, new PatternMatching().patternMatching("a", "zqvamqvuuvvazv"));
		assertEquals(true, new PatternMatching().patternMatching("bbbbbbbbbbbbbbabbbbb",
				"ppppppppppppppjsftcleifftfthiehjiheyqkhjfkyfckbtwbelfcgihlrfkrwireflijkjyppppg"));
		assertEquals(false, new PatternMatching().patternMatching("bb", "thuhrh"));
	}

	public boolean patternMatching(String pattern, String value) {
		if (pattern.equals("")) {
			if (value.equals(""))
				return true;
			return false;
		}

		int countOfA = 0, countOfB = 0;
		for (int i = 0; i < pattern.length(); i++)
			if (pattern.charAt(i) == 'a')
				countOfA++;
			else
				countOfB++;

		if (value.equals("")) {
			if (countOfA == 0 || countOfB == 0)
				return true;
			return false;
		}

		// 只有a或b且value不为空
		if (countOfA == 0 || countOfB == 0) {
			int count = countOfA == 0 ? countOfB : countOfA;
			if (value.length() % count != 0)
				return false;
			int len = value.length() / count;
			String str = value.substring(0, len);
			boolean succeed = true;
			for (int i = len; i <= value.length() - len; i += len) {
				String nextStr = value.substring(i, i + len);
				if (!nextStr.equals(str)) {
					succeed = false;
					break;
				}
			}
			if (succeed)
				return true;
			return false;
		}

		// 枚举b对应字符串的长度
		for (int lenB = 0; lenB <= value.length() / countOfB; lenB++) {
			if ((value.length() - lenB * countOfB) % countOfA != 0)
				continue;

			int lenA = (value.length() - lenB * countOfB) / countOfA;
			String strA = null, strB = null;
			boolean succeed = true;
			for (int i = 0, indexOfValue = 0; i < pattern.length(); i++) {
				if (pattern.charAt(i) == 'a') {
					if (strA == null) {
						strA = value.substring(indexOfValue, indexOfValue + lenA);
					} else {
						String nextStrA = value.substring(indexOfValue, indexOfValue + lenA);
						if (!nextStrA.equals(strA)) {
							succeed = false;
							break;
						}
					}
					indexOfValue += lenA;
				} else {
					if (strB == null) {
						strB = value.substring(indexOfValue, indexOfValue + lenB);
					} else {
						String nextStrB = value.substring(indexOfValue, indexOfValue + lenB);
						if (!nextStrB.equals(strB)) {
							succeed = false;
							break;
						}
					}
					indexOfValue += lenB;
				}
			}

			if (succeed)
				return true;
		}
		return false;
	}
}

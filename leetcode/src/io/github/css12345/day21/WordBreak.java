package io.github.css12345.day21;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * leetcode 139.单词拆分<br>
 * https://leetcode-cn.com/problems/word-break/
 * @author cs
 *
 */
public class WordBreak {

	public static void main(String[] args) {
		assertEquals(true, new WordBreak().wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet", "code"))));
		assertEquals(true, new WordBreak().wordBreak("applepenapple", new ArrayList<>(Arrays.asList("apple", "pen"))));
		assertEquals(false, new WordBreak().wordBreak("catsandog",
				new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))));
		assertEquals(true, new WordBreak().wordBreak("aaaaaaa", new ArrayList<>(Arrays.asList("aaaa", "aaa"))));
		assertEquals(false, new WordBreak().wordBreak(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
				new ArrayList<>(Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa",
						"aaaaaaaaa", "aaaaaaaaaa"))));
	}

	private Map<Pos, Boolean> cachedResultMap = new HashMap<>();

	private static final class Pos {
		private final int startPos, endPos;

		public Pos(int startPos, int endPos) {
			this.startPos = startPos;
			this.endPos = endPos;
		}

		public static final Pos valueOf(int startPos, int endPos) {
			Pos pos = new Pos(startPos, endPos);
			return pos;
		}

		@Override
		public String toString() {
			return "(" + startPos + ", " + endPos + ")";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + endPos;
			result = prime * result + startPos;
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
			Pos other = (Pos) obj;
			if (endPos != other.endPos)
				return false;
			if (startPos != other.startPos)
				return false;
			return true;
		}

	}

	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> words = new HashSet<>(wordDict);
		return wordBreak(s, words, 0, 1);
	}

	private boolean wordBreak(String s, Set<String> words, int startPos, int endPos) {
		Pos pos = Pos.valueOf(startPos, endPos);
		if (cachedResultMap.containsKey(pos)) {
			return cachedResultMap.get(pos);
		}

		String currentStr = s.substring(startPos, endPos);
		if (words.contains(currentStr)) {
			if (endPos == s.length() || wordBreak(s, words, endPos, endPos + 1)) {
				cachedResultMap.put(pos, true);
				return true;
			}

			boolean result = wordBreak(s, words, startPos, endPos + 1);
			cachedResultMap.put(pos, result);
			return result;
		}
		if (endPos == s.length()) {
			cachedResultMap.put(pos, false);
			return false;
		}
		boolean result = wordBreak(s, words, startPos, endPos + 1);
		cachedResultMap.put(pos, result);
		return result;
	}
}

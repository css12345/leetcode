package io.github.css12345.day11;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 14.最长公共前缀<br>
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * @author cs
 *
 */
public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[][] testDatas = { { "flower", "flow", "flight" }, { "dog", "racecar", "car" }, { "flower", "flow" } };
		String[] testResults = { "fl", "", "flow" };
		for (int i = 0; i < testDatas.length; i++)
			assertEquals(testResults[i], new LongestCommonPrefix().longestCommonPrefix(testDatas[i]));
	}

	public String longestCommonPrefix(String[] strs) {
		Trie trie = new Trie();
		for (String str : strs) {
			if (str.equals(""))
				return "";
			trie.insert(str);
		}
		return trie.findLongestCommonPrefix();
	}

	private static class Trie {
		char c;
		boolean isEnd = false;
		List<Trie> childs = new ArrayList<>();

		public void insert(String str) {
			Trie currentTrieNode = this;
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				boolean existChildTrieNode = false;
				for (Trie childTrieNode : currentTrieNode.childs) {
					if (childTrieNode.c == ch) {
						existChildTrieNode = true;
						if (i == str.length() - 1)
							childTrieNode.isEnd = true;
						currentTrieNode = childTrieNode;
						break;
					}
				}
				if (!existChildTrieNode) {
					Trie childTrieNode = new Trie();
					childTrieNode.c = ch;
					if (i == str.length() - 1)
						childTrieNode.isEnd = true;
					currentTrieNode.childs.add(childTrieNode);
					currentTrieNode = childTrieNode;
				}
			}
		}

		public String findLongestCommonPrefix() {
			StringBuilder prefixBuilder = new StringBuilder();
			Trie currentTrieNode = this;
			while (currentTrieNode.childs.size() == 1 && !currentTrieNode.isEnd) {
				currentTrieNode = currentTrieNode.childs.get(0);
				prefixBuilder.append(currentTrieNode.c);
			}
			return prefixBuilder.toString();
		}
	}
}

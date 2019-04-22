package pers.cs.day7;

import java.util.HashMap;
import java.util.Map;


//leetcode208
public class Trie {

	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.insert("apple");
		System.out.println(trie.search("apple")); // 返回 true
		System.out.println(trie.search("app")); // 返回 false
		System.out.println(trie.startsWith("app")); // 返回 true
		trie.insert("app");
		System.out.println(trie.search("app")); // 返回 true

	}

	char c;
	boolean isWorld = false;
	Map<Character, Trie> childs = new HashMap<>();

	/** Initialize your data structure here. */
	public Trie() {

	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		int k = 0;
		Trie commonTrie = this;
		while (true) {
			if (k == word.length()) {
				commonTrie.isWorld = true;
				return;
			}
			if (commonTrie.childs.containsKey(word.charAt(k))) {
				commonTrie = commonTrie.childs.get(word.charAt(k));
				k++;
			} else
				break;
		}

		while (k < word.length()) {
			Trie trie = new Trie();
			trie.c = word.charAt(k);
			if (k == word.length() - 1)
				trie.isWorld = true;
			commonTrie.childs.put(trie.c, trie);
			commonTrie = trie;
			k++;
		}
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		int k = 0;
		Trie commonTrie = this;
		while (true) {
			if (k == word.length())
				return commonTrie.isWorld;
			if (commonTrie.childs.containsKey(word.charAt(k))) {
				commonTrie = commonTrie.childs.get(word.charAt(k));
				k++;
			} else
				return false;
		}
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		int k = 0;
		Trie commonTrie = this;
		while (true) {
			if (k == prefix.length())
				return true;
			if (commonTrie.childs.containsKey(prefix.charAt(k))) {
				commonTrie = commonTrie.childs.get(prefix.charAt(k));
				k++;
			} else
				return false;
		}
	}
}

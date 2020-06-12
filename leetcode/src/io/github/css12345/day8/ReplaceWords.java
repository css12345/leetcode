package io.github.css12345.day8;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 648.单词替换<br>
 * https://leetcode-cn.com/problems/replace-words/
 * @author cs
 *
 */
public class ReplaceWords {

	public static void main(String[] args) {
		List<String> testDataDict1 = new ArrayList<>(Arrays.asList("cat", "bat", "rat"));
		String testDataSentence1 = "the cattle was rattled by the battery";
		assertEquals("the cat was rat by the bat", new ReplaceWords().replaceWords(testDataDict1, testDataSentence1));
	}

	public String replaceWords(List<String> dict, String sentence) {
//      正常匹配解法，时间复杂度O(n*m*min(x,y))，n为句子单词数，m为字典单词数，x为字典单词长度，y为句子单词长度
//		StringBuilder stringBuilder = new StringBuilder();
//		String[] words = sentence.split(" ");
//		for (String word : words) {
//			String minPrefixDictWord = null;
//			for (String dictWord : dict) {
//				if (word.startsWith(dictWord)) {
//					if (minPrefixDictWord == null || dictWord.length() < minPrefixDictWord.length())
//						minPrefixDictWord = dictWord;
//				}
//			}
//			
//			String newSentenceWord = minPrefixDictWord == null ? word : minPrefixDictWord;
//			stringBuilder.append(newSentenceWord);
//			stringBuilder.append(' ');
//		}
//		return stringBuilder.toString().trim();
		
		//字典树解法，插入时间复杂度O(m*x)，后续单词查找时间复杂度O(n*x)
		Trie trie = new Trie();
		for (String dictWord : dict) {
			trie.insert(dictWord);
		}
		StringBuilder stringBuilder = new StringBuilder();
		String[] words = sentence.split(" ");
		for (String word : words) {
			String minPrefixWordInDict = trie.searchMinPrefix(word);
			String newSentenceWord = minPrefixWordInDict == null ? word : minPrefixWordInDict;
			stringBuilder.append(newSentenceWord);
			stringBuilder.append(' ');
		}
		return stringBuilder.toString().trim();
	}
	
	private static class Trie {
		char c;
		boolean isEnd;
		Trie[] next = new Trie[26];
		
		public void insert(String word) {
			//上一个字母对应的字典树节点
			Trie lastTrieNode = this;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (lastTrieNode.next[c - 'a'] == null) {
					lastTrieNode.next[c - 'a'] = new Trie();
					lastTrieNode.next[c - 'a'].c = c;
				}
				if (i == word.length() - 1) {
					lastTrieNode.next[c - 'a'].isEnd = true;
				}
				lastTrieNode = lastTrieNode.next[c - 'a'];
			}
		}
		
		public String searchMinPrefix(String word) {
			Trie trieNode = this;
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (trieNode.next[c - 'a'] == null)
					return null;
				stringBuilder.append(c);
				if (trieNode.next[c - 'a'].isEnd == true) 
					return stringBuilder.toString();
				trieNode = trieNode.next[c - 'a'];
			}
			return null;
		}
	}
}

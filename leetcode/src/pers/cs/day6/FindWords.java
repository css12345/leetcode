package pers.cs.day6;

import java.util.ArrayList;
import java.util.List;

//leetcode212
public class FindWords {

	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.insert("apple");
		System.out.println(trie.search("apple")); // 返回 true
		System.out.println(trie.search("app")); // 返回 false
		System.out.println(trie.startsWith("app")); // 返回 true
		trie.insert("app");
		System.out.println(trie.search("app")); // 返回 true

//		String[] words = { "oath", "pea", "eat", "rain" };
//		char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
//				{ 'i', 'f', 'l', 'v' } };
//		System.out.println(new FindWords().findWords(board, words));

		String[] words = { "b" };
		char[][] board = { { 'a' } };
		System.out.println(new FindWords().findWords(board, words));
	}

	// 1.对于每个单词使用dfs查找是否存在
	// 2.使用字典树
	public List<String> findWords(char[][] board, String[] words) {
		List<String> list = new ArrayList<>();
		// solveUseDfs(board, words, list);
		solveUseTrie(board, words, list);
		return list;
	}

	private void solveUseTrie(char[][] board, String[] words, List<String> list) {
		Trie trie = new Trie();
		for (String word : words)
			trie.insert(word);
		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) {
				visited[i][j] = true;
				findWords(board, trie, visited, list, "" + board[i][j], i, j);
				// System.out.println(list);
				visited[i][j] = false;
			}
	}

	private void findWords(char[][] board, Trie trie, boolean[][] visited, List<String> list, String currentWord, int x,
			int y) {
		if (!trie.startsWith(currentWord))
			return;
		if (trie.search(currentWord))
			list.add(currentWord);
		for (int i = 0; i < dxdy.length; i++) {
			int newX = x + dxdy[i][0], newY = y + dxdy[i][1];
			if (isValid(newX, newY, board) && !visited[newX][newY]) {
				visited[newX][newY] = true;
				findWords(board, trie, visited, list, currentWord + board[newX][newY], newX, newY);
				visited[newX][newY] = false;
			}
		}
	}

	private void solveUseDfs(char[][] board, String[] words, List<String> list) {
		for (String word : words)
			if (dfs(board, word))
				list.add(word);
	}

	private static int[][] dxdy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private boolean dfs(char[][] board, String word) {
		if (word == null || word.equals(""))
			return true;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == word.charAt(0)) {
					boolean[][] visited = new boolean[board.length][board[i].length];
					visited[i][j] = true;
					if (dfs(board, word, visited, i, j, 1))
						return true;
				}
		return false;
	}

	private boolean dfs(char[][] board, String word, boolean[][] visited, int x, int y, int currentNeedToCheckIndex) {
		if (currentNeedToCheckIndex == word.length())
			return true;
		for (int i = 0; i < dxdy.length; i++) {
			int newX = x + dxdy[i][0], newY = y + dxdy[i][1];
			if (isValid(newX, newY, board) && !visited[newX][newY]
					&& board[newX][newY] == word.charAt(currentNeedToCheckIndex)) {
				visited[newX][newY] = true;
				if (dfs(board, word, visited, newX, newY, currentNeedToCheckIndex + 1))
					return true;
				visited[newX][newY] = false;
			}
		}
		return false;
	}

	private boolean isValid(int x, int y, char[][] board) {
		int row = board.length, column = board[0].length;
		if (x >= 0 && x < row && y >= 0 && y < column)
			return true;
		return false;
	}

}

class Trie {
	char c;
	boolean isWorld = false;
	List<Trie> childs = new ArrayList<>();

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
			boolean exist = false;
			for (Trie trie : commonTrie.childs)
				if (trie.c == word.charAt(k)) {
					k++;
					commonTrie = trie;
					exist = true;
					break;
				}
			if (!exist)
				break;
		}

		while (k < word.length()) {
			Trie trie = new Trie();
			trie.c = word.charAt(k);
			if (k == word.length() - 1)
				trie.isWorld = true;
			commonTrie.childs.add(trie);
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
			boolean exist = false;
			for (Trie trie : commonTrie.childs)
				if (trie.c == word.charAt(k)) {
					k++;
					commonTrie = trie;
					exist = true;
					break;
				}
			if (!exist)
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
			boolean exist = false;
			for (Trie trie : commonTrie.childs)
				if (trie.c == prefix.charAt(k)) {
					k++;
					commonTrie = trie;
					exist = true;
					break;
				}
			if (!exist)
				return false;
		}
	}
}

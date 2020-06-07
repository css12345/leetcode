package io.github.css12345.day4;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * leetcode126.单词接龙 II<br>
 * https://leetcode-cn.com/problems/word-ladder-ii/
 * @author cs
 *
 */
public class FindLadders {
	public static void main(String[] args) {
		String beginWord = "hit", endWord = "cog";
		List<String> worldList1 = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
		List<String> worldList2 = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
		List<List<String>> result1 = new ArrayList<>();
		result1.add(new ArrayList<>(Arrays.asList("hit", "hot", "dot", "dog", "cog")));
		result1.add(new ArrayList<>(Arrays.asList("hit", "hot", "lot", "log", "cog")));

		List<List<String>> result2 = new ArrayList<>();
		assertEquals(result1, new FindLadders().findLadders(beginWord, endWord, worldList1));
		assertEquals(result2, new FindLadders().findLadders(beginWord, endWord, worldList2));
	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> allPossibles = new ArrayList<>();
		
		Map<String, Integer> wordMap = new HashMap<>();
		for (int i = 0; i < wordList.size(); i++)
			wordMap.put(wordList.get(i), i);
		if (!wordMap.containsKey(beginWord)) {
			wordList.add(beginWord);
			wordMap.put(beginWord, wordList.size() - 1);
		}

		if (!wordMap.containsKey(endWord))
			return allPossibles;

		int[][] differentBits = new int[wordList.size()][wordList.size()];
		//对角线位置两个字符串相同，不同位为0
		//先求上半部分，因为两边是对称的，然后下半部分直接赋值即可
		for (int i = 0; i < wordList.size(); i++) {
			for (int j = i + 1; j < wordList.size(); j++) {
				String str1 = wordList.get(i);
				String str2 = wordList.get(j);
				for (int k = 0; k < str1.length(); k++)
					if (str1.charAt(k) != str2.charAt(k))
						differentBits[i][j]++;
			}
		}
		
		for (int i = 0; i < wordList.size(); i++) {
			for (int j = 0; j < i; j++) {
				differentBits[i][j] = differentBits[j][i];
			}
		}
		
//		for (int[] is : differentBits) {
//			System.out.println(Arrays.toString(is));
//		}

		Queue<List<String>> queue = new LinkedList<>();
		queue.add(new ArrayList<>(Arrays.asList(beginWord)));
		int beginIndex = wordMap.get(beginWord);
		int[] cost = new int[wordList.size()];
		for (int i = 0; i < cost.length; i++) {
			if (i != beginIndex)
				cost[i] = Integer.MAX_VALUE;
		}
		
		while (!queue.isEmpty()) {
			List<String> topList = queue.poll();
			String topElement = topList.get(topList.size() - 1);
			int topIndex = wordMap.get(topElement);
			
			if (topElement.equals(endWord)) {
				allPossibles.add(topList);
			} else {
				for (int i = 0; i < differentBits[topIndex].length; i++) {
					if (differentBits[topIndex][i] != 1)
						continue;
					
					if (cost[i] >= cost[topIndex] + 1) {
						cost[i] = cost[topIndex] + 1;
						List<String> newList = new ArrayList<>(topList);
						newList.add(wordList.get(i));
						queue.add(newList);
					}
				}
			}
		}
		return allPossibles;
	}
}

package io.github.css12345.day5;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 990.等式方程的可满足性<br>
 * https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
 * @author cs
 *
 */
public class EquationsPossible {

	public static void main(String[] args) {
		String[][] testDatas = { {"a==b","e==c","b==c","a!=e"}, { "a==b", "b!=a" }, { "b==a", "a==b" }, { "a==b", "b==c", "a==c" },
				{ "a==b", "b!=c", "c==a" }, { "c==c", "b==d", "x!=z" } };
		boolean[] testResults = { false, false, true, true, false, true };

		for (int i = 0; i < testDatas.length; i++) {
			assertEquals(testResults[i], new EquationsPossible().equationsPossible(testDatas[i]));
		}
	}
	
	private Map<Character, Character> findUnionMap = new HashMap<>();
	
	public char find(char c) {
		char parent = findUnionMap.get(c);
		if (parent == c)
			return parent;
		
		char root = find(parent);
		findUnionMap.put(c, root);
		return root;
	}
	
	public void merge(char c1, char c2) {
		char root1 = find(c1);
		char root2 = find(c2);
		
		if (root1 != root2)
			findUnionMap.put(root1, root2);
	}

	public boolean equationsPossible(String[] equations) {
		//初始化并查集
		for (String equation : equations) {
			char firstCharacter = equation.charAt(0);
			char secondCharacter = equation.charAt(3);
			findUnionMap.put(firstCharacter, firstCharacter);
			findUnionMap.put(secondCharacter, secondCharacter);
		}
		
		List<String> equalsList = new ArrayList<>();
		List<String> notEqualsList = new ArrayList<>();
		
		for (String equation : equations) {
			if (equation.charAt(1) == '=')
				equalsList.add(equation);
			else 
				notEqualsList.add(equation);
		}
		
		//遍历等式列表，构建并查集
		for (String equalExpression : equalsList) {
			char c1 = equalExpression.charAt(0);
			char c2 = equalExpression.charAt(3);
			merge(c1, c2);
		}
		
		//遍历不等式列表，根据并查集判断是否存在矛盾
		for (String notEqualExpression : notEqualsList) {
			char c1 = notEqualExpression.charAt(0);
			char c2 = notEqualExpression.charAt(3);
			
			char root1 = find(c1);
			char root2 = find(c2);
			if (root1 == root2)
				return false;
		}
		return true;
	}
}

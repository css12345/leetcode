package pers.cs.day3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

//leetcode 22
public class GenerateParenthesis {

	public static void main(String[] args) {
		System.out.println(new GenerateParenthesis().generateParenthesis(3));
	}

	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<>();
		//brute(n, list);
		dfs(list,"",0,0,n);
		return list;
	}
	
	
	private void dfs(List<String> list, String current, int leftUsed, int rightUsed,int n) {
		if(current.length() == 2 * n)
			list.add(current);
		else {
			if(leftUsed < n)
				dfs(list, current + "(", leftUsed + 1, rightUsed, n);
			if(rightUsed < leftUsed)
				dfs(list, current + ")", leftUsed, rightUsed + 1, n);
		}
	}

	private void brute(int n, List<String> list) {
		HashSet<String>[] sets = new HashSet[2];
		for(int i = 0;i < sets.length;i++)
			sets[i] = new HashSet<>();
		
		sets[0].add("()");
		int last = 0;
		for(int i = 1;i < n;i++,last = 1 - last) {
			sets[1 - last].clear();
			Iterator<String> iterator = sets[last].iterator();
			while(iterator.hasNext()) {
				String current = iterator.next();
				
				for(int j = 0;j <= current.length();j++) {
					StringBuilder stringBuilder = new StringBuilder(current);
					stringBuilder.insert(j, "()");
					sets[1 - last].add(stringBuilder.toString());
				}
			}
		}
		
		list.addAll(sets[1 - n % 2]);
	}

}

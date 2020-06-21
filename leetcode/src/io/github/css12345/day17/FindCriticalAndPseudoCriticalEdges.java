package io.github.css12345.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1489.找到最小生成树里的关键边和伪关键边<br>
 * https://leetcode-cn.com/contest/weekly-contest-194/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
 * @author cs
 *
 */
public class FindCriticalAndPseudoCriticalEdges {
	public static void main(String[] args) {
		List<List<Integer>> result1 = new ArrayList<>();
		result1.add(new ArrayList<>(Arrays.asList(0, 1)));
		result1.add(new ArrayList<>(Arrays.asList(2, 3, 4, 5)));
		
		System.out.println(new FindCriticalAndPseudoCriticalEdges().findCriticalAndPseudoCriticalEdges(5, new int[][] {
				{ 0, 1, 1 }, { 1, 2, 1 }, { 2, 3, 2 }, { 0, 3, 2 }, { 0, 4, 3 }, { 3, 4, 3 }, { 1, 4, 6 } }));

		List<List<Integer>> result2 = new ArrayList<>();
		result2.add(new ArrayList<>());
		result2.add(new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
		
		System.out.println(new FindCriticalAndPseudoCriticalEdges().findCriticalAndPseudoCriticalEdges(4,
				new int[][] { { 0, 1, 1 }, { 1, 2, 1 }, { 2, 3, 1 }, { 0, 3, 1 } }));
	}

	public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
		int mst = kruskal(n, edges);
		Set<Integer> criticalEdges = new HashSet<>();
		Set<Integer> criticalAndPseudoCriticalEdges = new HashSet<>();
		//对于MST的关建边，增加边的权值，那么MST的值会增加。
		//对于MST的关键边以及伪关键边，减小边的权值，那么MST的值会减少。
		for (int i = 0; i < edges.length; i++) {
			edges[i][2]++;
			if (kruskal(n, edges) > mst)
				criticalEdges.add(i);
			edges[i][2]--;
			
			edges[i][2]--;
			if (kruskal(n, edges) < mst)
				criticalAndPseudoCriticalEdges.add(i);
			edges[i][2]++;
		}
		
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>(criticalEdges));
		List<Integer> pseudoCriticalEdges = new ArrayList<>();
		for (int edge : criticalAndPseudoCriticalEdges)
			if (!criticalEdges.contains(edge))
				pseudoCriticalEdges.add(edge);
		result.add(pseudoCriticalEdges);
		return result;
	}
	
	private static class FindUnionSet {
		private int[] f;
		
		public FindUnionSet (int n) {
			f = new int[n];
			for (int i = 0; i < n; i++)
				f[i] = i;
		}
		
		public int find (int x) {
			return f[x] == x ? f[x] : (f[x] = find(f[x]));
		}
		
		public boolean merge (int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			
			if (rootX == rootY)
				return false;
			
			f[rootX] = rootY;
			return true;
		}
	}

	private int kruskal(int n, int[][] edges) {
		List<List<Integer>> edgeList = new ArrayList<>();
		for (int i = 0; i < edges.length; i++)
			edgeList.add(new ArrayList<>(Arrays.asList(edges[i][0], edges[i][1], edges[i][2])));
		Collections.sort(edgeList, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return Integer.compare(o1.get(2), o2.get(2));
			}
		});
		
		
		FindUnionSet findUnionSet = new FindUnionSet(n);
		int selectedEdgeNum = 0;
		int mst = 0;
		for (int i = 0; selectedEdgeNum < n - 1; i++) {
			int from = edgeList.get(i).get(0);
			int to = edgeList.get(i).get(1);
			int weight = edgeList.get(i).get(2);
			
			if (findUnionSet.merge(from, to)) {
				mst += weight;
				selectedEdgeNum++;
			}
		}
		return mst;
	}
	
}

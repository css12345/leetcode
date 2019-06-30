package pers.cs.day11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用bfs+剪枝来做
 * https://leetcode-cn.com/submissions/detail/21720398/
 * @author cs
 *
 */
public class BFS {

	private int startX, startY, minValue;
	private int[][] grid;
	private int M, N;
	private Queue<SearchItem> queue;

	private class SearchItem {
		int x, y, value;

		public SearchItem(int x, int y, int value) {
			super();
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + value;
			result = prime * result + x;
			result = prime * result + y;
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
			SearchItem other = (SearchItem) obj;
			if (value != other.value)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}

	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
			return 0;

		M = grid.length;
		N = grid[0].length;
		startX = startY = 0;
		this.grid = grid;
		minValue = grid[0][0];

		queue = new LinkedList<>();
		queue.add(new SearchItem(startX, startY, grid[0][0]));
		search();
		return minValue;
	}

	private void search() {
		while (!queue.isEmpty()) {
			SearchItem item = queue.poll();
			if (item.x == M - 1 && item.y == N - 1) {
				minValue = item.value;
				break;
			}

			if (item.x < M - 1) {
				SearchItem downItem = new SearchItem(item.x + 1, item.y, item.value + grid[item.x + 1][item.y]);
				if (!whetherReplace(downItem)) {
					queue.add(downItem);
				}
			}

			if (item.y < N - 1) {
				SearchItem rightItem = new SearchItem(item.x, item.y + 1, item.value + grid[item.x][item.y + 1]);
				if (!whetherReplace(rightItem)) {
					queue.add(rightItem);
				}
			}
		}
	}

	private boolean whetherReplace(SearchItem item) {
		for (SearchItem searchItem : queue) {
			if (searchItem.x == item.x && searchItem.y == item.y) {
				if(item.value < searchItem.value) {
					queue.remove(searchItem);
					queue.add(item);
				}
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(new BFS().minPathSum(grid));
	}
}

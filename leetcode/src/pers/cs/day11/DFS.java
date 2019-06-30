package pers.cs.day11;

/**
 * 使用dfs+剪枝来做
 * https://leetcode-cn.com/submissions/detail/21718940/
 * @author cs
 *
 */
public class DFS {

	private int startX, startY, value;
	// best[i][j]表示从(0,0)到(i,j)的最短距离
	private int[][] best;
	private int[][] grid;
	private int M, N;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;

	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
			return 0;

		M = grid.length;
		N = grid[0].length;
		startX = startY = 0;
		value = grid[0][0];
		best = new int[M][N];
		this.grid = grid;
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				best[i][j] = Integer.MAX_VALUE;
		
		best[0][0] = grid[0][0];

		if (startX < M - 1)
			search(DOWN);
		if (startY < N - 1)
			search(RIGHT);
		return best[M - 1][N - 1];
	}

	private void search(int direction) {
		setUp(direction);
		if (startX <= M - 1 && startY <= N - 1 && value < best[startX][startY]) {
			best[startX][startY] = value;
			if (startX < M - 1)
				search(DOWN);
			if (startY < N - 1)
				search(RIGHT);
		}
		recover(direction);
	}

	private void setUp(int direction) {
		if (direction == DOWN) {
			startX += 1;
		} else if (direction == RIGHT) {
			startY += 1;
		}

		value += grid[startX][startY];
	}

	private void recover(int direction) {
		value -= grid[startX][startY];

		if (direction == DOWN) {
			startX -= 1;
		} else if (direction == RIGHT) {
			startY -= 1;
		}

	}

}

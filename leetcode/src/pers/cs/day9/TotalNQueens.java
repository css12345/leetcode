package pers.cs.day9;

//leetcode 52,使用位运算统计N皇后解法个数
public class TotalNQueens {

	public static void main(String[] args) {
		System.out.println(new TotalNQueens().totalNQueens(4));
	}

	private int result = 0;

	public int totalNQueens(int n) {
		if (n <= 0)
			return 0;
		result = 0;
		dfs(0, 0, 0, 0, n);
		return result;
	}

	/**
	 * 
	 * @param row 第几行
	 * @param col 这个数的二进制针对列来看表示这一行的可用情况，0可用，1不可用
	 * @param pie 这个数的二进制针对撇来看表示这一行的可用情况，0可用，1不可用
	 * @param na  这个数的二进制针对捺来看表示这一行的可用情况，0可用，1不可用
	 * @param n 几皇后
	 */
	private void dfs(int row, int col, int pie, int na, int n) {
		if (row == n) {
			result++;
			return;
		}

		//求出当前行哪些可用，哪些不可用，0不可用，1可用
		//这里比如八皇后的第三行，第一行第一列和第二行第三列有皇后
		/* col = 10100000
		 * pie = 01000000
		 * na  = 00110000
		 * 这一行的情况应该是00001111
		 * 这里取或并取反后前24位变成了1需要去掉，只看后8位
		 */
		int currentRowCondition = (~ (col | pie | na));
		currentRowCondition &= ((1 << n) - 1);
		
		while(currentRowCondition > 0) {
			//将一个数和它的反码与运算后可得到 从后往前最近的那一位是1，其他位是0的数
			int currentAvailablePos = currentRowCondition & (-currentRowCondition);
			dfs(row + 1, col | currentAvailablePos, (pie | currentAvailablePos) << 1, (na | currentAvailablePos) >> 1, n);
			//去掉从后往前最近的那个1
			currentRowCondition &= (currentRowCondition - 1);
		}
	}
}

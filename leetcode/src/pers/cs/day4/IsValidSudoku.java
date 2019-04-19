package pers.cs.day4;

import java.util.Scanner;

//leetcode 36
public class IsValidSudoku {

	public static void main(String[] args) {
		//read();
		char[][] board = {
				  {'8','3','.','.','7','.','.','.','.'},
				  {'6','.','.','1','9','5','.','.','.'},
				  {'.','9','8','.','.','.','.','6','.'},
				  {'8','.','.','.','6','.','.','.','3'},
				  {'4','.','.','8','.','3','.','.','1'},
				  {'7','.','.','.','2','.','.','.','6'},
				  {'.','6','.','.','.','.','2','8','.'},
				  {'.','.','.','4','1','9','.','.','5'},
				  {'.','.','.','.','8','.','.','7','9'}
				};
		System.out.println(new IsValidSudoku().isValidSudoku(board));
	}

	private static void read() {
		Scanner input = new Scanner(System.in);
		StringBuilder stringBuilder = new StringBuilder();
		int n = 11;
		while(n-- > 0) {
			String string = input.nextLine();
			string = string.replace('[', '{');
			string = string.replace(']', '}');
			string = string.replace("\"", "'");
			stringBuilder.append(string + "\n");
		}
		input.close();
		System.out.println(stringBuilder.toString());
	}

	static final int ROW = 9,COLUMN = 9;
	static final int SMALL_ROW = 3,SMALL_COLUMN = 3;
	int[][] dxdy = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,0},{0,1},{1,-1},{1,0},{1,1}};
	
	public boolean isValidSudoku(char[][] board) {
		if(board == null || board.length != ROW || board[0].length != COLUMN)
			return false;
		
		for(int i = 0;i < ROW;i++) 
			if(!judgeRow(board[i]))
				return false;
		
		for(int i = 0;i < COLUMN;i++)
			if(!judgeColumn(board,i))
				return false;
		
		int timesOfRow = ROW / SMALL_ROW,timesOfColumn = COLUMN / SMALL_COLUMN;
		for(int i = 0;i < timesOfRow;i++)
			for(int j = 0;j < timesOfColumn;j++)
				if(!judgeSmallArea(board,i * SMALL_ROW + 1,j * SMALL_COLUMN + 1))
					return false;		
		return true;
	}

	private boolean judgeSmallArea(char[][] board, int centerX, int centerY) {
		int[] times = new int[COLUMN];
		for(int i = 0;i < dxdy.length;i++) {
			int x = centerX + dxdy[i][0],y = centerY + dxdy[i][1];
			if(board[x][y] == '.')
				continue;
			int k = board[x][y] - '0' - 1;
			times[k]++;
			if(times[k] > 1)
				return false;
		}
		return true;
	}

	private boolean judgeColumn(char[][] board, int k) {
		int[] times = new int[COLUMN];
		for(int i = 0;i < ROW;i++) {
			if(board[i][k] == '.')
				continue;
			int x = board[i][k] - '0' - 1;
			times[x]++;
			if(times[x] > 1)
				return false;
		}
		return true;
	}

	private boolean judgeRow(char[] rowBoard) {
		int[] times = new int[COLUMN];
		for(int i = 0;i < COLUMN;i++) {
			if(rowBoard[i] == '.')
				continue;
			int x = rowBoard[i] - '0' - 1;
			times[x]++;
			if(times[x] > 1)
				return false;
		}
		return true;
	}
}

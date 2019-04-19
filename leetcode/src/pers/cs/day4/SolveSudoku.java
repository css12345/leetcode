package pers.cs.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//leetcode 37
public class SolveSudoku {

	public static void main(String[] args) {
		char[][] board = {
				  {'5','3','.','.','7','.','.','.','.'},
				  {'6','.','.','1','9','5','.','.','.'},
				  {'.','9','8','.','.','.','.','6','.'},
				  {'8','.','.','.','6','.','.','.','3'},
				  {'4','.','.','8','.','3','.','.','1'},
				  {'7','.','.','.','2','.','.','.','6'},
				  {'.','6','.','.','.','.','2','8','.'},
				  {'.','.','.','4','1','9','.','.','5'},
				  {'.','.','.','.','8','.','.','7','9'}
				};
		long startTime = System.currentTimeMillis();
		new SolveSudoku().solveSudoku(board);
		System.out.println(System.currentTimeMillis() - startTime + " ms");
		for(int i = 0;i < board.length;i++) {
			for(int j = 0;j < board[i].length;j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}

	public void solveSudoku(char[][] board) {
		if(board == null || board.length != ROW || board[0].length != COLUMN)
			return;
		//直接dfs超时
		//directDfs(board,0,0);
		
		ArrayList<Cell> needToWrite = new ArrayList<>();
		//首先统计所有.字符对应的格子可以填的元素
		for(int i = 0;i < ROW;i++)
			for(int j = 0;j < COLUMN;j++) {
				if(board[i][j] != '.')
					continue;
				Cell cell = new Cell();
				cell.x = i;
				cell.y = j;
				cell.availableNumbers = calculateAvailableNumbers(board,i,j);
				needToWrite.add(cell);
			}
		//按可以填的数的多少从小到大排序，再进行dfs，这时不再从1-9选数，而是从之前求的列表中进行筛选
		Collections.sort(needToWrite, new Comparator<Cell>() {

			@Override
			public int compare(Cell o1, Cell o2) {
				int size1 = o1.availableNumbers.size(),size2 = o2.availableNumbers.size();
				if(size1 < size2)
					return -1;
				else if(size1 == size2)
					return 0;
				else 
					return 1;
			}
		});
		
		dfs(board,needToWrite,0);
	}
	
	private boolean dfs(char[][] board, ArrayList<Cell> needToWrite, int index) {
		if(index == needToWrite.size())
			return true;
		Cell cell = needToWrite.get(index);
		for(Integer num:cell.availableNumbers) {
			board[cell.x][cell.y] = (char)(num + '0');
			if(!isValidSudoku(board))
				continue;
			if(dfs(board, needToWrite, index + 1))
				return true;
		}
		board[cell.x][cell.y] = '.';
		return false;
	}

	private ArrayList<Integer> calculateAvailableNumbers(char[][] board, int x, int y) {
		ArrayList<Integer> result = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		for(int i = 0;i < COLUMN;i++) {
			if(board[x][i] == '.')
				continue;
			Integer num = board[x][i] - '0';
			result.remove(num);
		}
		
		for(int i = 0;i < ROW;i++) {
			if(board[i][y] == '.')
				continue;
			Integer num = board[i][y] - '0';
			result.remove(num);
		}
		
		int centerOfX = x / SMALL_ROW * SMALL_ROW + 1,centerOfY = y / SMALL_COLUMN * SMALL_COLUMN + 1;
		for(int i = 0;i < dxdy.length;i++) {
			if(board[centerOfX + dxdy[i][0]][centerOfY + dxdy[i][1]] == '.')
				continue;
			Integer num = board[centerOfX + dxdy[i][0]][centerOfY + dxdy[i][1]] - '0';
			result.remove(num);
		}
		
		return result;	
	}

	static class Cell {
		int x,y;
		ArrayList<Integer> availableNumbers;
	}
	

	private boolean directDfs(char[][] board,int currentRow,int currentColumn) {
		if(currentColumn == COLUMN) {
			currentRow++;
			currentColumn = 0;
		}
		if(currentRow == ROW)
			return true;
		if(board[currentRow][currentColumn] != '.')
			if(directDfs(board, currentRow, currentColumn + 1)) 
				return true;
		
		for(int i = 1;i <= 9;i++) {
			board[currentRow][currentColumn] = (char)('0' + i);
			if(!isValidSudoku(board)) 
				continue;
			if(directDfs(board, currentRow, currentColumn + 1)) 
				return true;
		}
		board[currentRow][currentColumn] = '.';
		return false;
	}
	
	static final int ROW = 9,COLUMN = 9;
	static final int SMALL_ROW = 3,SMALL_COLUMN = 3;
	int[][] dxdy = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,0},{0,1},{1,-1},{1,0},{1,1}};
	
	public boolean isValidSudoku(char[][] board) {		
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

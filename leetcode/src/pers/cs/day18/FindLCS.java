package pers.cs.day18;

import java.util.Scanner;

public class FindLCS {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str1 = input.nextLine();
		String str2 = input.nextLine();
		input.close();
		System.out.println(findLCS(str1,str2));
	}

	/**
	 *https://www.51nod.com/Challenge/ProblemSubmitDetail.html#!#judgeId=762941
	 */
	private static String findLCS(String str1, String str2) {
		if(str1 == null || str1.equals("") || str2 == null || str2.equals(""))
			return null;
		int M = str1.length(),N = str2.length();
		int[][] dp = new int[M][N];
		boolean repeat = false;
		for(int i = 0;i < M;i++) {
			if(repeat)
				dp[i][0] = 1;
			else {
				if(str1.charAt(i) == str2.charAt(0)) {
					repeat = true;
					dp[i][0] = 1;
				}
			}
		}
		
		repeat = false;
		for(int i = 0;i < N;i++) {
			if(repeat)
				dp[0][i] = 1;
			else {
				if(str1.charAt(0) == str2.charAt(i)) {
					repeat = true;
					dp[0][i] = 1;
				}
			}
		}
		
		for(int i = 1;i < M;i++) 
			for(int j = 1;j < N;j++) 
				if(str1.charAt(i) == str2.charAt(j)) 
					dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]));
				else 
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			
		char[] result = new char[dp[M - 1][N - 1]];
		int k = result.length - 1;
		for(int i = M - 1,j = N - 1;k >= 0;) {	
			if(i > 0 && dp[i][j] == dp[i - 1][j])
				i--;
			else if(j > 0 && dp[i][j] == dp[i][j - 1])
				j--;
			else {
				result[k--] = str1.charAt(i);
				i--;
				j--;
			}
		}
		return String.valueOf(result);
	}

}

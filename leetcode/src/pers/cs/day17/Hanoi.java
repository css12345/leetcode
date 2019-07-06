package pers.cs.day17;

import static org.junit.Assert.assertEquals;

public class Hanoi {

	public static void main(String[] args) {
		int[][] testDatas = {{1,1},{2,1},{3,3},{2,2}};
		int[] expecteds = {0,1,3,-1};
		Hanoi hanoi = new Hanoi();
		for(int i = 0;i < testDatas.length;i++) {
			assertEquals(expecteds[i], hanoi.step1(testDatas[i]));
			assertEquals(expecteds[i], hanoi.step2(testDatas[i]));
		}
	}
	
	/**
	 * 递归解法
	 */
	public int step1(int[] arr) {
		if(arr == null || arr.length == 0)
			return -1;
		return process(arr,1,3,2,arr.length - 1);
	}
	
	private int process(int[] arr, int from, int to, int help, int k) {
		if(k == -1)
			return 0;
		if(arr[k] != from && arr[k] != to)
			return -1;
		
		if(arr[k] == from) 
			return process(arr, from, help, to, k - 1);
		int result = process(arr, help, to, from, k - 1);
		if(result == -1)
			return -1;
		return (int) (Math.pow(2, k) + result);	
	}

	/**
	 * 非递归解法
	 */
	public int step2(int[] arr) {
		if(arr == null || arr.length == 0)
			return -1;
		int from = 1,to = 3,help = 2;
		int result = 0;
		for(int i = arr.length - 1;i >= 0;i--) {
			if(arr[i] != from && arr[i]	!= to) {
				return -1;
			}
			if(arr[i] == from) {
				int tmp = to;
				to = help;
				help = tmp;
			}
			else {
				int tmp = from;
				from = help;
				help = tmp;
				result += Math.pow(2, i);
			}
		}
		return result;
	}
}

package pers.cs.day8;

//leetcode 191
public class HammingWeight {

	public static void main(String[] args) {
		
	}

	//n&n-1可以去除最后一个1
	public int hammingWeight(int n) {
		int sum = 0;
		while(n != 0) {
			sum++;
			n = n & (n - 1);
		}
		return sum;
	}

}

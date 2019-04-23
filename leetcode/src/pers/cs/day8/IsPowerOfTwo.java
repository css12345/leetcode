package pers.cs.day8;

//leetcode 231
public class IsPowerOfTwo {

	public static void main(String[] args) {
		int[] nums = {1,16,218};
		for(int n:nums)
			System.out.println(new IsPowerOfTwo().isPowerOfTwo(n));
	}

	public boolean isPowerOfTwo(int n) {
		if(n <= 0)
			return false;
		if((n & (n - 1)) == 0)
			return true;
		return false;
	}
}

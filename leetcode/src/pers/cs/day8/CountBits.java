package pers.cs.day8;

//leetcode 338
public class CountBits {

	public static void main(String[] args) {
		int[] nums = {2,5};
		for(int num:nums) {
			int[] result = new CountBits().countBits(num);
			for(int x:result)
				System.out.print(x + " ");
			System.out.println();
		}
	}

	public int[] countBits(int num) {
		if(num < 0)
			return null;
		int[] result = new int[num + 1];
		for(int i = 1;i <= num;i++) 
			result[i] = result[(i & (i - 1))] + 1;
		
		return result;
	}

}

package pers.cs.day5;

//leetcode 69
public class MySqrt {

	public static void main(String[] args) {
		MySqrt mySqrt = new MySqrt();
		System.out.println(mySqrt.mySqrt1(4));
		System.out.println(mySqrt.mySqrt1(8));
		System.out.println(mySqrt.mySqrt1(1));
		System.out.println(mySqrt.mySqrt1(2147395599));
	}
	
	public int mySqrt(int x) {
		if(x == 1)
			return 1;
		long left = 1,right = x / 2;
		while(left <= right) {
			long mid = (left + right) / 2;
			if(mid * mid == x)
				return (int) mid;
			
			if(mid * mid < x)
				left = mid + 1;
			else 
				right = mid - 1;
		}
		return (int) right;
	}
	
	public double mySqrt1(int x) {
		if(x == 1)
			return 1;
		double left = 1,right = x / 2;
		while(right - left > 1e-9) {
			double mid = (left + right) / 2;
			if(mid * mid == x)
				return mid;
			
			if(mid * mid < x)
				left = mid;
			else 
				right = mid;
		}
		return right;
	}

}

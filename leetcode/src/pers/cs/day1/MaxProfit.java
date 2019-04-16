package pers.cs.day1;

//leetcode 122
public class MaxProfit {
	public static void main(String[] args) {
		int[][] prices = { { 7, 1, 5, 3, 6, 4 }, { 1, 2, 3, 4, 5 }, { 7, 6, 4, 3, 1 } };
		for(int[] price:prices)
			System.out.println(new MaxProfit().maxProfit(price));
	}

	//使用贪心算法，只要后一天比前一天价格高就买
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0)
			return 0;
		int maxProfit = 0;

		for (int i = 0; i < prices.length - 1; i++)
			if (prices[i + 1] > prices[i])
				maxProfit += (prices[i + 1] - prices[i]);

		return maxProfit;
	}
}

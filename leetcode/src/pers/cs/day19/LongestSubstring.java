package pers.cs.day19;

import static org.junit.Assert.assertEquals;

public class LongestSubstring {

	public static void main(String[] args) {
		assertEquals(4, new LongestSubstring().findLongest("1AB2345CD",9,"12345EF",7));
		assertEquals(1, new LongestSubstring().findLongest("cbbbaaac",8,"bccca",5));
	}
	
	/**
	 * https://www.nowcoder.com/profile/6394872/codeBookDetail?submissionId=50256151
	 */
	public int findLongest(String A, int n, String B, int m) {
        if(n <= 0 || m <= 0)
        	return 0;
        
        int len = 0;
        int row = 0, col = m - 1;
        int max = 0;
        while(row < n) {
        	int i = row, j = col;
        	len = 0;
        	while(i >= 0 && i < n && j >= 0 && j < m) {
        		if(A.charAt(i) == B.charAt(j))
        			len++;
        		else 
        			len = 0;
        		if(len > max) {
        			max = len;
        		}
        		i++;
        		j++;
        	}
        	
        	if(col > 0)
        		col--;
        	else 
        		row++;
        }
		return max;
    }
}

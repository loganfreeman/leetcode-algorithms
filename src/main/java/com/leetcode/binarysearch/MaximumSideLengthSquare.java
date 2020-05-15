package com.leetcode.binarysearch;

/**
 *
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
 *
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 */
public class MaximumSideLengthSquare {
    /**
     * (x1, y1) coordinate of top left corner
     * (x2, y2) coordinate of bottom right corner
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public int rangeSum(int x1, int y1, int x2, int y2, int[][] dp) {
        return dp[y2][x2] - dp[y2][x1 - 1] - dp[y1 - 1][x2] + dp[y1 - 1][x1 - 1];
    }
    // solution one: DP + Brute Force
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        int ans = 0;

        int[][] dp = new int[m+1][n+1];

        for (int y = 1; y <= m; ++y)
            for (int x = 1; x <= n; ++x)
                dp[y][x] = dp[y][x - 1] + dp[y - 1][x]  - dp[y - 1][x - 1] + mat[y - 1][x - 1];


        for (int y = 1; y <= m; ++y)
            for (int x = 1; x <= n; ++x) {
/*                for (int k = 0; y + k <= m && x + k <= n; ++k) {
                    if (rangeSum(x, y, x + k, y + k, dp) > threshold) break;
                    ans = Math.max(ans, k + 1);
                }*/

                int l = 0;
                int r = Math.min(m - y, n - x) + 1;
                while (l < r) {
                    int p = l + (r - l) / 2;
                    // Find smllest l that > threshold, ans = (l + 1) - 1
                    if (rangeSum(x, y, x + p, y + p, dp) > threshold)
                        r = p;
                    else
                        l = p + 1;
                }
                ans = Math.max(ans, (l + 1) - 1);
            }


        return ans;
    }
}

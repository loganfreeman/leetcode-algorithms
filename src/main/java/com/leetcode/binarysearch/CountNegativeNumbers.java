package com.leetcode.binarysearch;

/**
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 *
 * Return the number of negative numbers in grid.
 */
public class CountNegativeNumbers {
    public int countNegatives(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        int r = m - 1;
        int c = 0;
        while(r >= 0 && c < n) {
            if(grid[r][c] < 0) {
                ans += n - c;
                --r;
            } else {
                ++c;
            }
        }
        return ans;
    }
}

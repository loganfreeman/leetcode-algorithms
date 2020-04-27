package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/minimum-path-sum/

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sums = new int[m][n];

        for(int i = 0; i < m; i ++) {
            sums[i][0] = i > 0 ? sums[i-1][0] + grid[i][0] : grid[i][0];
        }

        for(int j = 0; j < n; j++) {
            sums[0][j] = j > 0 ? sums[0][j-1] + grid[0][j] : grid[0][j];
        }

        for(int i = 1; i < m; i ++) {
            for(int j = 1; j < n; j++) {
                sums[i][j] = Math.min(sums[i-1][j], sums[i][j-1]) + grid[i][j];
            }
        }

        return sums[m-1][n-1];
    }
}

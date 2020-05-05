package com.leetcode.depthfirstsearch;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 */
public class MinimumPathSum {
    public int dfs(int i, int j, int[][] grid) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }

        if (i < grid.length - 1 && j < grid[0].length - 1) {
            int r1 = grid[i][j] + dfs(i + 1, j, grid);
            int r2 = grid[i][j] + dfs(i, j + 1, grid);
            return Math.min(r1, r2);
        }

        if (i < grid.length - 1) {
            return grid[i][j] + dfs(i + 1, j, grid);
        }

        if (j < grid[0].length - 1) {
            return grid[i][j] + dfs(i, j + 1, grid);
        }

        return 0;
    }
}

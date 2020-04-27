package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/unique-paths-ii/

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 */
public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] path = new int[m][n];

        for(int i = 0; i < m; i++) {
            if(obstacleGrid[i][0] > 0) {
                path[i][0] = 0;
            } else {
                path[i][0] = i > 0 ? path[i-1][0] : 1;
            }
        }


        for(int j = 0; j < n; j++) {
            if(obstacleGrid[0][j] > 0) {
                path[0][j] = 0;
            } else {
                path[0][j] = j > 0 ? path[0][j-1] : 1;
            }
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(obstacleGrid[i][j] > 0) {
                    path[i][j] = 0;
                } else{
                    path[i][j] = path[i-1][j] + path[i][j-1];
                }
            }
        }

        return path[m-1][n-1];
    }
}

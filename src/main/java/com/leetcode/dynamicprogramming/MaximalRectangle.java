package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/maximal-rectangle/

import java.util.Stack;

/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;
        int[][] height = new int[m][n];

        int maxArea = 0;
        // calculate a height histogram for each row using DP
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    height[i][j] = 0;
                } else {
                    height[i][j] = i == 0 ? 1 : height[i - 1][j] + 1;
                }
            }
        }
        // iterating through each row, using the histogram to calculate max rectangle and update max
        for (int i = 0; i < m; i++) {
            int area = maxAreaInHist(height[i]);
            if (area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }

    private int maxAreaInHist(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();

        int i = 0;
        int max = 0;

        while (i < height.length) {
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i++);
            } else {
                // whether a bar is blocked, we calculate how much the bar can support and update max
                int t = stack.pop();
                max = Math.max(max, height[t]
                        * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        // the stack only contains bars with increasing height, we calculate how much each can support and update max
        while(!stack.isEmpty()) {
            int t = stack.pop();
            max = Math.max(max, height[t]
                    * (stack.isEmpty() ? i : i - stack.peek() - 1));
        }

        return max;
    }
}

package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/triangle/

import java.util.List;

/**
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class TrianglePath {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // For storing the result
        // in a 1-D array, and
        // simultaneously updating
        // the result.
        int []memo = new int[n];


        // For the bottom row
        for (int i = 0;  i < n; i++)
            memo[i] = triangle.get(n-1).get(i);

        // Calculation of the
        // remaining rows, in
        // bottom up manner.
        for (int i = n - 2;  i >= 0; i--)
            for (int j = 0;  j < triangle.get(i).size(); j++)
                memo[j] = triangle.get(i).get(j) +   (int)Math.min(memo[j], memo[j + 1]);

        // return the
        // top element
        return memo[0];

    }
}

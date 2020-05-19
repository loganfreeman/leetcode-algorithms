package com.leetcode.breadthfirstsearch;

import java.lang.reflect.Array;
import java.util.*;

/**
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 *
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 1.
 * Example 3:
 *
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 1.
 * Notes:
 *
 * 1 <= grid.length = grid[0].length <= 50.
 * 0 <= grid[i][j] <= 1.
 * Solution
 * Step 1: give each connected component a unique id and count its ara.
 *
 * Step 2: for each 0 zero, check its 4 neighbours, sum areas up by unique ids.
 */
public class ConnectTwoIslands {
    int m_;
    int n_;
    int color_;
    public int largestIsland(int[][] grid) {
        color_ = 1;

        m_ = grid.length;
        n_ = grid[0].length;
        Map<Integer, Integer> areas = new HashMap<>(); // color -> area
        areas.put(0, 0);
        int ans = 0;
        for (int i = 0; i < m_; ++i)
            for (int j = 0; j < n_; ++j)
                if (grid[i][j] == 1) {
                    ++color_;
                    int area = getArea(j, i, grid);
                    areas.put(color_, area);
                    ans = Math.max(ans, areas.get(color_));

                }
        for (int i = 0; i < m_; ++i)
            for (int j = 0; j < n_; ++j)
                if (grid[i][j] == 0) {
                    int area = 1;
                    Integer colors[] = {getColor(j, i - 1, grid), getColor(j, i + 1, grid),
                            getColor(j - 1, i, grid), getColor(j + 1, i, grid)};
                    Set<Integer> set = new HashSet<Integer>(Arrays.asList(colors));

                    for (int color : set) {
                        area += areas.get(color);
                    }

                    ans = Math.max(ans, area);
                }
        return ans;
    }

    int getColor(int x, int y, int[][] g) {
        return (x < 0 || x >= n_ || y < 0 || y >= m_) ? 0 : g[y][x];
    }

    // Return the area of a connected component, set all elements to color_.
    int getArea(int x, int y, int[][] g) {
        if (x < 0 || x >= n_ || y < 0 || y >= m_ || g[y][x] != 1) return 0;
        g[y][x] = color_;
        return 1 + getArea(x - 1, y, g) + getArea(x + 1, y, g)
                + getArea(x, y - 1, g) + getArea(x, y + 1, g);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {{1, 1}, {1, 0}};
        ConnectTwoIslands connectTwoIslands = new ConnectTwoIslands();
        int ans = connectTwoIslands.largestIsland(grid);
        System.out.println(ans);
    }
}

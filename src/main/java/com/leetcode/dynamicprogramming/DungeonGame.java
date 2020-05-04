package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/dungeon-game/

/*

The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.



Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        //init dp table
        int[][] h = new int[m][n];

        h[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

        //init last row
        for (int i = m - 2; i >= 0; i--) {
            h[i][n - 1] = Math.max(h[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }

        //init last column
        for (int j = n - 2; j >= 0; j--) {
            h[m - 1][j] = Math.max(h[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }

        //calculate dp table
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = Math.max(h[i + 1][j] - dungeon[i][j], 1);
                int right = Math.max(h[i][j + 1] - dungeon[i][j], 1);
                h[i][j] = Math.min(right, down);
            }
        }

        return h[0][0];
    }
}

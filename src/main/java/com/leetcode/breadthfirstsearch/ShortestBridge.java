package com.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 *
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 *
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 * Solution: DFS + BFS
 * Use DFS to find one island and color all the nodes as 2 (BLUE).
 * Use BFS to find the shortest path from any nodes with color 2 (BLUE) to any nodes with color 1 (RED).
 */
public class ShortestBridge {
    public int shortestBridge(int[][] A) {
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;
        for (int i = 0; i < A.length && !found; ++i)
            for (int j = 0; j < A[0].length && !found; ++j)
                if (A[i][j] == 1) {
                    dfs(A, j, i, queue);
                    found = true;
                }

        int steps = 0;
        int[] dirs = new int[] {0, 1, 0, -1, 0};
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] pair = queue.peek();
                int x = pair[0];
                int y = pair[1];
                queue.poll();
                for(int i = 0; i < 4; i++) {
                    int tx = x + dirs[i];
                    int ty = y + dirs[i + 1];
                    if (tx < 0 || ty < 0 || tx >= A[0].length || ty >= A.length || A[ty][tx] == 2) continue;
                    if (A[ty][tx] == 1) return steps;
                    A[ty][tx] = 2;
                    queue.add(new int[] {tx, ty});
                }
            }
            ++steps;
        }
        return -1;
    }

    public void dfs(int[][] matrix, int x, int y, Queue<int[]> queue) {
        if (x < 0 || y < 0 || x >= matrix[0].length || y >= matrix.length || matrix[y][x] != 1) return;
        matrix[y][x] = 2;
        queue.add(new int[] {x, y});
        dfs(matrix, x - 1, y, queue);
        dfs(matrix, x, y - 1, queue);
        dfs(matrix, x + 1, y, queue);
        dfs(matrix, x, y + 1, queue);
    }
}

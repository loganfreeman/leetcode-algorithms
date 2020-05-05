package com.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Use an array to track the parent node for each cell.
 */
public class NumberofIslandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parent = new int[m * n];
        Arrays.fill(parent,-1);

        ArrayList<Integer> result = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int count = 0;
        for (int[] position : positions) {
            count++;
            int idx = n * position[0] + position[1];

            if (parent[idx] == -1) {
                parent[idx] = idx;
            }

            for (int k = 0; k < 4; k++) {
                int x = position[0] + dx[k];
                int y = position[1] + dy[k];

                int idxNeighbor = n * x + y;

                if (x >= 0 && x < m && y >= 0 && y < n && parent[idxNeighbor] != -1) {
                    int p = getParent(parent, idxNeighbor);

                    //set neighor's parent to be current idx
                    if (parent[p] != idx) {
                        parent[p] = idx;
                        count--;
                    }
                }
            }

            result.add(count);
        }

        return result;
    }

    private int getParent(int[] parent, int i) {
        while (parent[i] != i) {
            i = parent[i];
        }

        return i;
    }
}

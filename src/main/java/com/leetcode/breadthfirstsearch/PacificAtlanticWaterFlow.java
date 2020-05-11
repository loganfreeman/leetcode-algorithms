package com.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
    int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] p = new boolean[m][n];
        boolean[][] a = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(p, matrix, i, 0);
            dfs(a, matrix, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs(p, matrix, 0, i);
            dfs(a, matrix, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] && p[i][j]) res.add(new int[] {i, j});
            }
        }
        return res;
    }

    private void dfs(boolean[][] visited, int[][] matrix, int i, int j) {
        int m = matrix.length, n = matrix[0].length;
        visited[i][j] = true;
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || matrix[x][y] < matrix[i][j]) continue;
            dfs(visited, matrix, x, y);
        }
    }
}

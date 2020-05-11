package com.leetcode.breadthfirstsearch;

import java.util.LinkedList;

/**
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 */
public class UpdateMatrix {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;


        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 1) {
                    bfs(i, j, matrix);
                }
            }
        }

        return matrix;
    }

    public void bfs(int i, int j, int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j, 0});
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int d = 0; d < 4; d++) {
                int x = current[0] + dx[d];
                int y = current[1] + dy[d];
                if(x < 0 || y < 0 || x >= m || y >= n) continue;
                if(matrix[x][y] == 0) {
                    matrix[i][j] = current[2] + 1;
                } else {
                    queue.offer(new int[] { x, y, current[2] + 1});
                }
            }
        }
    }

    public int[][] updateMatrixDP(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // scan left top down
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] > 0) {
                    int top = i > 0 ? matrix[i - 1][j] + 1 : 10000;
                    int left = j > 0 ? matrix[i][j - 1] + 1 : 10000;
                    matrix[i][j] = Math.min(top, left);
                }
            }
        }

        // scan right-bottom up
        for(int i = m -1 ; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] > 0) {
                    int bottom = i < m -1 ? matrix[i + 1][j] + 1 : 10000;
                    int right = j < n - 1 ? matrix[i][j + 1] + 1 : 10000;
                    int min = Math.min(bottom, right);
                    matrix[i][j] = Math.min(matrix[i][j], min);

                }
            }
        }

        return matrix;
    }
}

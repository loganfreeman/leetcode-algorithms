package com.leetcode.backtracking;

/**
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules:
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * <p>
 * Write a function to compute the next state (after one update) of the board given its current state.
 */
public class GameOfLife {
    /**
     * Because we need to solve the problem in place, we can use the higher bit to record the next state. And at the end, shift right a bit to get the next state for each cell.
     * @param board
     */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;

        int m = board.length;
        int n = board[0].length;

        int[] x = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] y = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int nx = i + x[k];
                    int ny = j + y[k];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && (board[nx][ny] & 1) == 1) {
                        count++;
                    }
                }

                //<2 die
                if (count < 2) {
                    board[i][j] &= 1;
                }

                //same state
                if (count == 2 || count == 3) {
                    board[i][j] |= board[i][j] << 1;
                }

                //go live
                if (count == 3) {
                    board[i][j] |= 2;
                }

                //>3 die
                if (count > 3) {
                    board[i][j] &= 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1;

            }
        }
    }
}

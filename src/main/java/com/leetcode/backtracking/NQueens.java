package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 */
public class NQueens {
    /* A utility function to print solution */
    static void printSolution(int board[][], List<List<String>> results, int N)
    {

        List<String> sol = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            String s = "";
            for(int j = 0; j < N; j++) {
                if(board[i][j] == 1) {
                    s += 'Q';
                }else {
                    s += '.';
                }
            }
            sol.add(s);
        }

        results.add(sol);
    }

    static boolean solveNQUtil(int board[][], int col, int N, List<List<String>> results)
    {
        /* base case: If all queens are placed
        then return true */
        if (col == N)
        {
            printSolution(board, results, N);
            return true;
        }

        /* Consider this column and try placing
        this queen in all rows one by one */
        boolean res = false;
        for (int i = 0; i < N; i++)
        {
            /* Check if queen can be placed on
            board[i][col] */
            if ( isSafe(board, i, col) )
            {
                /* Place this queen in board[i][col] */
                board[i][col] = 1;

                // Make result true if any placement
                // is possible
                res = solveNQUtil(board, col + 1, N, results) ;

                /* If placing queen in board[i][col]
                doesn't lead to a solution, then
                remove queen from board[i][col] */
                board[i][col] = 0; // BACKTRACK
            }
        }

        /* If queen can not be place in any row in
            this column col then return false */
        return res;
    }

    static boolean isSafe(int board[][], int row, int col)
    {
        int i, j;
        int N = board.length;

        /* Check this row on left side */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        int[][] board = new int[n][n];

        solveNQUtil(board, 0, n, results);
        return results;
    }

}

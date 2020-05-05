package com.leetcode.breadthfirstsearch;

/**
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {
    public void merge(char[][] board, int i, int j){
        board[i][j] = '#';

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int k=0; k<4; k++){
            int x = i+dx[k];
            int y = j+dy[k];

            if(x>=0 && x<board.length
                    && y>=0 && y<board[0].length
                    && board[x][y]=='O'){
                merge(board, x, y);
            }
        }
    }
    public void solve(char[][] board) {
        if(board == null || board.length==0)
            return;

        int m = board.length;
        int n = board[0].length;

        //merge O's on left & right boarder
        for(int i=0;i<m;i++){
            if(board[i][0] == 'O'){
                merge(board, i, 0);
            }

            if(board[i][n-1] == 'O'){
                merge(board, i, n-1);
            }
        }

        //merge O's on top & bottom boarder
        for(int j=0; j<n; j++){
            if(board[0][j] == 'O'){
                merge(board, 0, j);
            }

            if(board[m-1][j] == 'O'){
                merge(board, m-1, j);
            }
        }

        //process the board
        for(int i=0;i<m;i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }
}

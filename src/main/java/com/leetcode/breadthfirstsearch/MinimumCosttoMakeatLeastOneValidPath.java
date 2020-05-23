package com.leetcode.breadthfirstsearch;
// http://www.zhouzaida.tech/algorithms/leetcode/bfs/minimum-cost-to-make-at-least-one-valid-path-in-a-grid.html

/**
 * Given a m x n grid . Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be: - 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1] ) - 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1] ) - 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j] ) - 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j] )
 *
 * Notice that there could be some invalid signs on the cells of the grid which points outside the grid .
 *
 * You will initially start at the upper left cell (0,0) . A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn’t have to be the shortest.
 *
 * You can modify the sign on a cell with cost = 1 . You can modify the sign on a cell one time only.
 *
 * Return the minimum cost to make the grid have at least one valid path.
 */
public class MinimumCosttoMakeatLeastOneValidPath {
    public int minCost(int[][] grid) {
        return 0;
    }
}

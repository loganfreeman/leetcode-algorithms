package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/

/**
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BestTimetoBuyandSellStockIV {
    // Find maximum profit earned from at most k stock transactions
    // Input to the function are stock prices of n days and positive number k
    public  int maxProfit(int k, int[] price)
    {
        // get number of days n
        int n = price.length;

        if(n<2) return 0;

        // profit[i][j] stores the maximum profit gained by doing
        // at most i transactions till j'th day
        int[][] profit = new int[k + 1][n];

        // fill profit[][] matrix in bottom-up fashion
        for (int i = 0; i <= k; i++)
        {
            for (int j = 0; j < n; j++)
            {
                // profit is 0 when:
                // i = 0 i.e. for 0'th day
                // j = 0 i.e. no transaction is being performed

                if (i == 0 || j == 0) {
                    profit[i][j] = 0;
                }
                else
                {
                    int max_so_far = 0;
                    for (int x = 0; x < j; x++)
                    {
                        int curr_price = price[j] - price[x] + profit[i-1][x];
                        if (max_so_far < curr_price) {
                            max_so_far = curr_price;
                        }
                    }

                    profit[i][j] = Math.max(profit[i][j-1], max_so_far);
                }
            }
        }

        return profit[k][n-1];
    }
}

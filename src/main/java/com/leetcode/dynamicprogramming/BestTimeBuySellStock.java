package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 */
public class BestTimeBuySellStock {
    public int maxProfit(int[] prices) {
        if(prices.length < 2) return 0;
        int min = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > min) {
                int diff = prices[i] - min;

                if(diff > maxProfit) {
                    maxProfit = diff;
                }
            }
            if(prices[i] < min) {
                min = prices[i];
            }
        }

        return maxProfit;
    }
}

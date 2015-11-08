// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

package com.htyleo.algorithms;

public class BestTimeToBuyAndSellStockII {
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int profit = 0;
        int prev = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prev) {
                profit += prices[i] - prev;
            }
            prev = prices[i];
        }

        return profit;
    }
}

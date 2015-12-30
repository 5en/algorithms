package com.htyleo.algorithms;

public class BestTimeToBuyAndSellStockWithCooldown {
    /*
             s0 (rest)
             |         \
        (buy)|          \ (rest)
             |           \
             s1---------->s2
           (rest)  (sell)
    */
    // O(N) time, O(1) space
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int oldS0 = 0;
        int oldS1 = -prices[0];
        int oldS2 = 0;
        int newS0 = 0;
        int newS1 = 0;
        int newS2 = 0;

        for (int i = 1; i < prices.length; i++) {
            newS0 = Math.max(oldS0, oldS2);
            newS1 = Math.max(oldS1, oldS0 - prices[i]);
            newS2 = oldS1 + prices[i];
            oldS0 = newS0;
            oldS1 = newS1;
            oldS2 = newS2;
        }

        return Math.max(newS0, newS2);
    }
}

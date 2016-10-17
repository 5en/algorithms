// https://leetcode.com/problems/guess-number-higher-or-lower-ii/

package com.htyleo.algorithms;

public class GuessNumberHigherOrLowerII {

    // min-max problem
    //
    // denote cost[i][j] as the (least) money we need to have to guarantee a win for guessing a number with in [i, j]
    // if we guess x, (i <= x <= j), assuming we always have the bad luck, we need to pay x + max(cost[i][x-1], cost[x+1][j])
    // =>
    // cost[i][j] = min {x + max(cost[i][x-1], cost[x+1][j]) | for x = i, i+1, ..., j}
    public int getMoneyAmount(int n) {

        int[][] cost = new int[n + 1][n + 1];
        for (int i = 1; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                cost[i][j] = (i == j ? 0 : -1);
            }
        }

        return cal(cost, 1, n);
    }

    private int cal(int[][] cost, int low, int high) {
        if (cost[low][high] != -1) {
            return cost[low][high];
        }

        int result = Integer.MAX_VALUE;
        for (int x = low; x <= high; x++) {
            int maxCost = 0;
            if (x > low) {
                maxCost = Math.max(maxCost, x + cal(cost, low, x - 1));
            }
            if (x < high) {
                maxCost = Math.max(maxCost, x + cal(cost, x + 1, high));
            }

            result = Math.min(result, maxCost);
        }

        cost[low][high] = result;

        return result;
    }

}

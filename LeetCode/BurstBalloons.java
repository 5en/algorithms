// https://leetcode.com/problems/burst-balloons/

package com.htyleo.algorithms;

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int N = nums.length;
        if (N == 0) {
            return 0;
        }

        // exnums[0] exnums[1...N] exnums[N+1]
        //    1      nums[1...N]        1
        int[] exnums = new int[N + 2];
        exnums[0] = 1;
        exnums[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            exnums[i + 1] = nums[i];
        }

        int[][] memo = new int[N + 2][N + 2];
        return maxCoinsSub(memo, exnums, 0, N + 1);
    }

    // divide and conquer with memoization
    private int maxCoinsSub(int[][] memo, int[] exnums, int lb, int ub) {
        // <= 2 balloons
        if (lb + 1 >= ub) {
            return 0;
        }

        if (memo[lb][ub] != 0) {
            return memo[lb][ub];
        }

        // check each last balloon
        int maxCoins = 0;
        for (int last = lb + 1; last < ub; last++) {
            int leftResult = maxCoinsSub(memo, exnums, lb, last);
            int rightResult = maxCoinsSub(memo, exnums, last, ub);
            maxCoins = Math.max(maxCoins, leftResult + exnums[lb] * exnums[last] * exnums[ub]
                                          + rightResult);
        }
        memo[lb][ub] = maxCoins;

        return memo[lb][ub];
    }

    // O(N^3) time, O(N^2) space
    // divide and conquer with DP
    public int maxCoins2(int[] nums) {
        int N = nums.length;
        if (N == 0) {
            return 0;
        }

        // exnums[0] exnums[1...N] exnums[N+1]
        int[] exnums = new int[N + 2];
        exnums[0] = 1;
        exnums[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            exnums[i + 1] = nums[i];
        }

        int[][] coins = new int[N + 2][N + 2];
        for (int len = 3; len <= N + 2; len++) {
            for (int left = 0; left + len - 1 <= N + 1; left++) {
                int right = left + len - 1;
                int maxCoins = 0;
                for (int last = left + 1; last < right; last++) {
                    maxCoins = Math.max(maxCoins, coins[left][last] + exnums[left] * exnums[last]
                                                  * exnums[right] + coins[last][right]);
                }
                coins[left][right] = maxCoins;
            }
        }

        return coins[0][N + 1];
    }
}

// Given several coins with values in a bag. How many different values of the sum of any number of coins?

package com.htyleo.algorithms;

import java.util.HashSet;
import java.util.Set;

public class CoinValueSum {

    public static void main(String[] args) {
        System.out.println(coinValueSum(new int[] { 1, 2, 3 }));
        System.out.println(coinValueSum(new int[] { 1, 2, 3, 8 }));
    }

    public static Set<Integer> coinValueSum(int[] coins) {
        Set<Integer> result = new HashSet<Integer>();
        coinValueSumSub(coins, 0, 0, result);
        return result;
    }

    private static void coinValueSumSub(int[] coins, int curr, int sum, Set<Integer> result) {
        if (curr == coins.length) {
            result.add(sum);
            return;
        }

        // choose coins[curr]
        coinValueSumSub(coins, curr + 1, sum + coins[curr], result);

        // not choose coins[curr]
        coinValueSumSub(coins, curr + 1, sum, result);
    }
}

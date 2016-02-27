// https://leetcode.com/problems/coin-change/

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        return coinChangeSR(new HashMap<Integer, Integer>(), coins, amount);
    }

    private int coinChangeSR(Map<Integer, Integer> cache, int[] coins, int amount) {
        if (cache.containsKey(amount)) {
            return cache.get(amount);
        }
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int result = -1;
        for (int coin : coins) {
            int currResult = coinChangeSR(cache, coins, amount - coin);
            if (currResult == -1) {
                continue;
            }
            result = (result == -1) ? 1 + currResult : Math.min(result, 1 + currResult);
        }

        cache.put(amount, result);
        return result;
    }
}

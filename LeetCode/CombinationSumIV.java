// https://leetcode.com/problems/combination-sum-iv/
//
// The idea comes from https://leetcode.com/problems/climbing-stairs/

package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        // target -> # of combinations
        Map<Integer, Integer> cache = new HashMap<>();
        return combinationSum4Sub(nums, target, cache);
    }

    private int combinationSum4Sub(int[] nums, int target, Map<Integer, Integer> cache) {
        if (cache.containsKey(target)) {
            return cache.get(target);
        }

        int result = 0;

        // consider the last number is "num"
        for (int num : nums) {
            if (num > target) {
                break;
            } else if (num == target) {
                result++;
                break;
            } else {
                // num < target
                result += combinationSum4Sub(nums, target - num, cache);
            }
        }

        cache.put(target, result);

        return result;
    }

}

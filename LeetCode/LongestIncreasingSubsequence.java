// https://leetcode.com/problems/longest-increasing-subsequence/

package com.htyleo.algorithms;

public class LongestIncreasingSubsequence {
    // O(N^2) time, O(N) space
    public int lengthOfLIS(int[] nums) {
        int result = 0;

        // lis[i]: LIS ending at nums[i], 0 <= i < N
        int[] lis = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
            result = Math.max(result, lis[i]);
        }

        return result;
    }
}

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

    // O(N*logN) time, O(N) space
    public int lengthOfLIS2(int[] nums) {
        // M[l]: index of the smallest element such that there is an IS of length l that ends at nums[M[l]]
        // It is easy to prove that A[M[1]], A[M[2]], ..., A[M[maxL]] is increasing, where maxL is the length of LIS.

        int N = nums.length;
        if (N == 0) {
            return 0;
        }

        int maxL = 1;
        int[] M = new int[N + 1];
        M[1] = 0;

        for (int i = 1; i < N; i++) {
            // find A[M[j]] < nums[i] <= A[M[j+1]]
            int lo = 1;
            int hi = maxL;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[M[mid]] < nums[i]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            // nums[i] <= nums[M[lo]]

            int len = lo - 1 + 1;
            if (len > maxL) {
                maxL = len;
                M[len] = i;
            } else if (nums[i] < nums[M[len]]) {
                M[len] = i;
            }
        }

        return maxL;
    }
}

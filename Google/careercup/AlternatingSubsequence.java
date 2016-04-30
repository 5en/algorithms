// Given an array of numbers, find the longest alternating subsequence.
// That is, a subsequence [a1, a2, a3, ..., ak] where a1 > a2, a3 < a2, a4 > a3, ... or vice versa
// (Graphically looks like /\/\/\... or \/\/\/\....

package com.htyleo.algorithms;

public class AlternatingSubsequence {

    public static void main(String[] args) {
        System.out.println(longestAlternatingSubsequence(new int[] { 1, 2, 3 }));
        System.out.println(longestAlternatingSubsequence(new int[] { 1, 3, 2 }));
        System.out.println(longestAlternatingSubsequence(new int[] { 1, 3, 2, 2, 5, 5, 3, 7 }));
    }

    // O(N^2) time, O(N) space
    public static int longestAlternatingSubsequence(int[] nums) {
        int N = nums.length;
        if (N == 0) {
            return 0;
        }

        int maxLen = 1;

        // lasDown[i] longest alternating subsequence that ends at nums[i] such that nums[i-1] > nums[i]
        int[] lasDown = new int[N];
        lasDown[0] = 1;

        // lasDown[i] longest alternating subsequence that ends at nums[i] such that nums[i-1] < nums[i]
        int[] lasUp = new int[N];
        lasUp[0] = 1;

        for (int i = 1; i < N; i++) {
            lasDown[i] = 1;
            lasUp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    lasDown[i] = Math.max(lasDown[i], lasUp[j] + 1);
                } else if (nums[j] < nums[i]) {
                    lasUp[i] = Math.max(lasUp[i], lasDown[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, lasDown[i]);
            maxLen = Math.max(maxLen, lasUp[i]);
        }

        return maxLen;
    }
}

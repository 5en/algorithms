// https://leetcode.com/problems/patching-array/

package com.htyleo.algorithms;

public class PatchingArray {
    public int minPatches(int[] nums, int n) {
        int result = 0;

        int i = 0;
        long miss = 1; // miss is the smallest sum in [1,n] that may be missing. We know we can build all sums in [1,miss).
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                // We have num <= miss in the given array, we add it to those smaller sums to build all sums in [1,miss+num).
                miss += nums[i];
                i++;
            } else {
                // If we don't, we must add such a number to the array, and it's best to add miss itself, to maximize the reach.
                miss <<= 1;
                result++;
            }
        }

        return result;
    }
}

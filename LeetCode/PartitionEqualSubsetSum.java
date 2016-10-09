// https://leetcode.com/problems/partition-equal-subset-sum/

package com.htyleo.algorithms;

public class PartitionEqualSubsetSum {

    // O(N * target) time
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // if the sum is odd, fail
        if (sum % 2 == 1) {
            return false;
        }

        // <=> find a subset of nums whose sum == sum / 2
        // reached[i] (i = 0...sum/2) indicates whether a subset of nums whose sum == i
        int target = sum / 2;
        boolean[] reached = new boolean[target + 1];
        for (int num : nums) {
            for (int i = 0; i <= target - num; i++) {
                if (reached[i]) {
                    reached[i + num] = true;
                }
            }
            if (num <= target) {
                reached[num] = true;
            }
        }

        return reached[target];
    }

}

// https://leetcode.com/problems/single-number-ii/

package com.htyleo.algorithms;

public class SingleNumberII {
    public static int singleNumber(int[] nums) {
        int[] sum = new int[32]; // sum the bits for each bit position
        for (int num : nums) {
            for (int i = 0; i < 32 && num != 0; i++) {
                sum[i] += (num & 1);
                num = num >>> 1;
            }
        }

        int result = 0;
        for (int i = 31; i >= 0; i--) {
            result = result << 1;
            result = result | (sum[i] % 3); // must be 0 or 1. ("3" can be generalized to k)
        }

        return result;
    }
}

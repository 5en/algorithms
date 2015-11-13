// https://leetcode.com/problems/missing-number/

package com.htyleo.algorithms;

public class MissingNumber {
    public static int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // recursively put x at nums[x]
            int x = nums[i];
            while (x < nums.length && x != nums[x]) {
                int tmp = nums[x];
                nums[x] = x;
                x = tmp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }

        return nums.length;
    }
}

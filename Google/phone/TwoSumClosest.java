// Given a list of numbers, return a pair of numbers so that their sum is the closest to zero

package com.htyleo.algorithms;

import java.util.Arrays;

public class TwoSumClosest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSumClosest(new int[] { -1, 3, 1, 2 })));
        System.out.println(Arrays.toString(twoSumClosest(new int[] { -1, -3, -2 })));
        System.out.println(Arrays.toString(twoSumClosest(new int[] { 1, 3, 2 })));
        System.out.println(Arrays.toString(twoSumClosest(new int[] { 1, 3, -2, 5, -9 })));
    }

    // O(NlogN) time, O(1) space
    public static int[] twoSumClosest(int[] nums) {
        int opt = Integer.MAX_VALUE;
        int[] result = new int[2];

        Arrays.sort(nums);
        for (int left = 0, right = nums.length - 1; left < right;) {
            int diff = nums[left] + nums[right];
            int absDiff = Math.abs(diff);

            if (absDiff < opt) {
                opt = absDiff;
                result[0] = nums[left];
                result[1] = nums[right];
            }

            if (diff < 0) {
                left++;
            } else if (diff > 0) {
                right--;
            } else {
                break;
            }
        }

        return result;
    }
}

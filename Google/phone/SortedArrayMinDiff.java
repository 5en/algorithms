// Given an sorted array of doubles and a double target number，return closest number’s index.

package com.htyleo.algorithms;

public class SortedArrayMinDiff {
    public static void main(String[] args) {
        System.out.println(cloestNum(new double[] { -5, -3, -1.5, -1, 0.5, 1.5, 2, 7 }, 0));
        System.out.println(cloestNum(new double[] { -5, -3, -1.5, -1 }, 0));
        System.out.println(cloestNum(new double[] { 1, 2, 3 }, 0));
        System.out.println(cloestNum(new double[] { 0 }, 0));
    }

    public static int cloestNum(double[] nums, double target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // nums[right] <= target
        // nums[left] > target
        double minDiff = Double.MAX_VALUE;
        int optIdx = -1;
        if (right >= 0 && Math.abs(nums[right] - target) < minDiff) {
            minDiff = Math.abs(nums[right] - target);
            optIdx = right;
        }
        if (left < nums.length && Math.abs(nums[left] - target) < minDiff) {
            minDiff = Math.abs(nums[left] - target);
            optIdx = left;
        }

        return optIdx;
    }

}

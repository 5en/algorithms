// sorted array a[], region (lb, ub], count numbers in a[] that are within (lb, ub]

package com.htyleo.algorithms;

public class SortedArrayInRange {

    public static void main(String[] args) {
        System.out.println(count(new int[] { 1, 2, 4, 4, 6 }, -1, 0));
        System.out.println(count(new int[] { 1, 2, 4, 4, 6 }, 1, 2));
        System.out.println(count(new int[] { 1, 2, 4, 4, 6 }, 2, 4));
        System.out.println(count(new int[] { 1, 2, 4, 4, 6 }, 6, 7));
        System.out.println(count(new int[] { 1, 2, 4, 4, 6 }, 7, 8));
    }

    public static int count(int[] nums, int lb, int ub) {
        int left = binarySearch(nums, lb);
        int right = binarySearch(nums, ub);

        return right - left;
    }

    // return max i such that nums[i] <= x
    private static int binarySearch(int[] nums, int x) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left - 1;
    }
}

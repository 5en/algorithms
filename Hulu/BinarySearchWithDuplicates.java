// binary search. if there are duplicates, return the first one

package com.htyleo.algorithms;

public class BinarySearchWithDuplicates {

    public static void main(String[] args) {
        System.out.println(bs(new int[] { 2 }, 2));
        System.out.println(bs(new int[] { 1 }, 2));
        System.out.println(bs(new int[] { 1, 2, 2 }, 2));
        System.out.println(bs(new int[] { 1, 1, 2, 2 }, 2));
    }

    public static int bs(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (right + 1 < a.length && a[right + 1] == target) ? right + 1 : -1;
    }
}

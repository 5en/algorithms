package com.htyleo.algorithms.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] a = { 10, 3, 567, 23, 65, 55, 28, 123, 505, 32 };
        System.out.println(Arrays.toString(a));
        MergeSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

    // worst-case O(N*logN), O(1) space
    public static void sort(int[] a) {
        sortSR(a, 0, a.length - 1);
    }

    private static void sortSR(int[] a, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;
        sortSR(a, left, mid);
        sortSR(a, mid + 1, right);

        // merge
        int[] tmp = new int[right - left + 1];
        int tmpi = 0;
        int li = left;
        int ri = mid + 1;
        while (li <= mid && ri <= right) {
            if (a[li] <= a[ri]) {
                tmp[tmpi++] = a[li++];
            } else {
                tmp[tmpi++] = a[ri++];
            }
        }
        if (li > mid) {
            while (ri <= right) {
                tmp[tmpi++] = a[ri++];
            }
        } else if (ri > right) {
            while (li <= mid) {
                tmp[tmpi++] = a[li++];
            }
        }

        // write back to the array
        for (tmpi = 0; tmpi < tmp.length; tmpi++) {
            a[left + tmpi] = tmp[tmpi];
        }
    }
}

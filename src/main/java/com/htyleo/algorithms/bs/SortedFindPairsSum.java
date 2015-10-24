package com.htyleo.algorithms.bs;

public class SortedFindPairsSum {
    public static void main(String[] args) {
        System.out.println(find(new int[] { 2, 4, 6, 7, 10, 12, 15 }, 10));
        System.out.println(find(new int[] { -10, -6, -2, 1, 10, 12, 15 }, 10));
    }

    // O(N*logN)
    // a[] is sorted (increasing), find # of pairs whose sum is less than a given value
    public static int find(int[] a, int x) {
        int count = 0;

        for (int i = 0; i <= a.length - 2; i++) {

            // search j: a[j] < x - a[i] <= a[j+1] in a[i+1...a.length-1]
            int low = i + 1;
            int high = a.length - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (a[mid] < x - a[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // j = high
            count += high - (i + 1) + 1;
        }

        return count;
    }
}

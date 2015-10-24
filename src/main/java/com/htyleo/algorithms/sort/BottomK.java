package com.htyleo.algorithms.sort;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class BottomK {
    public static void main(String[] args) {
        // 3, 10, 23, 28, [32], 55, 65, 123, 505, 567 
        int[] a = { 10, 3, 567, 23, 65, 55, 28, 123, 505, 32 };
        System.out.println(a[bottomK(a, 5, 0, a.length - 1)]);
        int[] b = { 10, 3, 567, 23, 65, 55, 28, 123, 505, 32 };
        System.out.println(bottomK2(b, 5, 0, a.length - 1));
        int[] c = { 10, 3, 567, 23, 65, 55, 28, 123, 505, 32 };
        System.out.println(bottomK3(c, 5));
    }

    // worst-case O(N). return index
    // deterministic select
    public static int bottomK(int[] a, int k, int left, int right) {
        if (left == right) {
            return left;
        }

        int[] medians = new int[(int) Math.ceil(((double) (right - left + 1)) / 5)];
        int[] mi2ai = new int[medians.length];

        int mi = 0;
        int gi = left;
        for (; gi + 4 <= right; gi += 5) {
            // groups of 5 elements, calculate its median
            mi2ai[mi] = medianIdx(a, gi, gi + 4);
            medians[mi] = a[mi2ai[mi]];
            mi++;
        }
        if (gi < right) {
            mi2ai[mi] = medianIdx(a, gi, right);
            medians[mi] = a[mi2ai[mi]];
        }

        // median of medians, used as the pivot
        int pivotIdx = mi2ai[bottomK(medians, (int) Math.ceil(((double) medians.length) / 2), 0,
            medians.length - 1)];
        pivotIdx = partition(a, pivotIdx, left, right);

        // recursion
        int pivotRank = pivotIdx - left + 1;
        if (k == pivotRank) {
            return pivotIdx;
        } else if (k < pivotRank) {
            return bottomK(a, k, left, pivotIdx - 1);
        } else {
            // k > pivotRank
            return bottomK(a, k - pivotRank, pivotIdx + 1, right);
        }
    }

    private static int medianIdx(int a[], int left, int right) {
        int cur = 0;
        int i = 0;
        int j = 0;

        for (i = left + 1; i <= right; i++) {
            cur = a[i];
            for (j = i; j >= left + 1; j--) {
                if (a[j - 1] > cur) {
                    a[j] = a[j - 1];
                } else {
                    break;
                }
            }
            a[j] = cur;
        }

        return (left + right) / 2;
    }

    // expected O(N)
    // random select
    public static int bottomK2(int[] a, int k, int left, int right) {
        if (left == right) {
            return a[left];
        }

        int pivotIdx = choosePivot(a, left, right);
        pivotIdx = partition(a, pivotIdx, left, right);
        int pivotRank = pivotIdx - left + 1;
        if (k == pivotRank) {
            return a[pivotIdx];
        } else if (k < pivotRank) {
            return bottomK2(a, k, left, pivotIdx - 1);
        } else {
            // k > pivotRank
            return bottomK2(a, k - pivotRank, pivotIdx + 1, right);
        }
    }

    private static int partition(int[] a, int pivotIdx, int left, int right) {
        if (left == right) {
            return pivotIdx;
        }

        int pivotValue = a[pivotIdx];
        // store a[right] at pivotIdx
        a[pivotIdx] = a[right];

        // determine the index to store the pivot value
        pivotIdx = left;
        for (int i = left; i <= right - 1; i++) {
            if (a[i] < pivotValue) {
                int tmp = a[pivotIdx];
                a[pivotIdx] = a[i];
                a[i] = tmp;
                pivotIdx++;
            }
        }

        // now:
        // a[left...pivotIdx-1] < pivotValue
        // a[pivotIdx...right-1] >= pivotValue

        a[right] = a[pivotIdx];
        // store pivotValue at pivotIdx
        a[pivotIdx] = pivotValue;

        return pivotIdx;
    }

    // return pivot index
    private static int choosePivot(int[] a, int left, int right) {
        // new Random().nextInt(n) returns [0, n)
        return left + new Random().nextInt(right - left + 1);
    }

    // O(N*logK), scan every element only once
    // keep a max-heap storing the K smallest elements
    public static int bottomK3(int[] a, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, Collections.reverseOrder()); // max-heap

        for (int i = 0; i < k; i++) {
            pq.add(a[i]);
        }

        for (int i = k; i < a.length; i++) {
            if (a[i] < pq.peek()) {
                pq.remove();
                pq.add(a[i]);
            }
        }

        return pq.peek();
    }
}

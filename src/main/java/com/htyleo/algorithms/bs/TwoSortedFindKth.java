package com.htyleo.algorithms.bs;

public class TwoSortedFindKth {
    public static void main(String[] args) {
        int[] A = { 1, 2, 3, 5 };
        int[] B = { 2, 3, 3, 4 };
        System.out.println(find(A, B, 1)); // 1
        System.out.println(find(A, B, 2)); // 2
        System.out.println(find(A, B, 3)); // 2
        System.out.println(find(A, B, 6)); // 3
        System.out.println(find(A, B, 7)); // 4
        System.out.println(find(A, B, 8)); // 5
        System.out.println();

        A = new int[] { 1, 2, 3 };
        B = new int[] { 2, 4, 5, 6, 7, 8, 8, 9, 10, 10 };
        System.out.println(find(A, B, 10)); // 8
        System.out.println(find(A, B, 3)); // 2
        System.out.println(find(A, B, 4)); // 3
        System.out.println();

        A = new int[] { 1, 2, 3 };
        B = new int[] { 4, 4, 5, 6, 7, 8, 8, 9, 10, 10 };
        System.out.println(find(A, B, 10)); // 8
        System.out.println(find(A, B, 3)); // 3
        System.out.println(find(B, A, 5)); // 4
        System.out.println();
    }

    public static int find(int[] A, int[] B, int k) {
        return findSR(A, 0, A.length - 1, B, 0, B.length - 1, k);
    }

    public static int findSR(int[] A, int al, int ah, int[] B, int bl, int bh, int k) {
        int aLen = ah - al + 1;
        int bLen = bh - bl + 1;

        // Guarantee A.length <= B.length
        if (aLen > bLen) {
            return findSR(B, bl, bh, A, al, ah, k);
        }

        // Base case.
        if (aLen == 0) {
            return B[bl + k - 1];
        }

        if (k == 1) {
            return Math.min(A[al], B[bl]);
        }

        // Split k, 
        // one part is distributed to A, A[al, ..., al+ak-1]
        // the other part is distributed to B, B[bl, ..., bl+bk-1]
        int ak = Math.min(aLen, k / 2);
        int bk = k - ak;

        int aPartitionIndex = al + (ak - 1);
        int bPartitionIndex = bl + (bk - 1);

        if (A[aPartitionIndex] == B[bPartitionIndex]) {
            return A[aPartitionIndex];
        } else if (A[aPartitionIndex] < B[bPartitionIndex]) {
            // Drop the left part of A, and
            // do recursion on the right part of A, and
            // the entire current part of B.
            return findSR(A, aPartitionIndex + 1, ah, B, bl, bh, k - ak);
        } else {
            // Drop the left part of B, and
            // do recursion on the entire current part of A, and
            // the right part of B.
            return findSR(A, al, ah, B, bPartitionIndex + 1, bh, k - bk);
        }
    }
}

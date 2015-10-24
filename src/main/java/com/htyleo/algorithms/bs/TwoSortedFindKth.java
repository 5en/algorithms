package com.htyleo.algorithms.bs;

public class TwoSortedFindKth {
    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 5 };
        int[] b = { 2, 3, 3, 4 };
        System.out.println(find(a, b, 1)); // 1
        System.out.println(find(a, b, 2)); // 2
        System.out.println(find(a, b, 3)); // 2
        System.out.println(find(a, b, 6)); // 3
        System.out.println(find(a, b, 7)); // 4
        System.out.println(find(a, b, 8)); // 5
        System.out.println();
        System.out.println(find2(a, b, 1)); // 1
        System.out.println(find2(a, b, 2)); // 2
        System.out.println(find2(a, b, 3)); // 2
        System.out.println(find2(a, b, 6)); // 3
        System.out.println(find2(a, b, 7)); // 4
        System.out.println(find2(a, b, 8)); // 5
        System.out.println();

        a = new int[] { 1, 2, 3 };
        b = new int[] { 2, 4, 5, 6, 7, 8, 8, 9, 10, 10 };
        System.out.println(find(a, b, 10)); // 8
        System.out.println(find(a, b, 3)); // 2
        System.out.println(find(a, b, 4)); // 3
        System.out.println();
        System.out.println(find2(a, b, 10)); // 8
        System.out.println(find2(a, b, 3)); // 2
        System.out.println(find2(a, b, 4)); // 3
        System.out.println();

        a = new int[] { 1, 2, 3 };
        b = new int[] { 4, 4, 5, 6, 7, 8, 8, 9, 10, 10 };
        System.out.println(find(a, b, 10)); // 8
        System.out.println(find(a, b, 3)); // 3
        System.out.println(find(b, a, 5)); // 4
        System.out.println();
        System.out.println(find2(a, b, 10)); // 8
        System.out.println(find2(a, b, 3)); // 3
        System.out.println(find2(b, a, 5)); // 4
        System.out.println();
    }

    // log(K)
    public static int find(int[] a, int[] b, int k) {
        if (k > a.length + b.length) {
            return -1;
        }

        if (k == 1) {
            return Math.min(a[0], b[0]);
        }

        // invariant: (ia+1) + (ib+1) == k
        int ia = k / 2 - 1;
        int ib = k - k / 2 - 1;
        if (ia >= a.length) {
            int delta = ia - (a.length - 1);
            ia -= delta;
            ib += delta;
        } else if (ib >= b.length) {
            int delta = ib - (b.length - 1);
            ib -= delta;
            ia += delta;
        }

        while (ia >= 0 && ib < b.length) {
            // make sure a[ia] >= b[ib]
            if (a[ia] < b[ib]) {
                int[] tmp = a;
                a = b;
                b = tmp;
                int tmpi = ia;
                ia = ib;
                ib = tmpi;
            }

            // if a[ia] <= b[ib+1], found
            if (ib == b.length - 1 || b[ib + 1] >= a[ia]) {
                return a[ia];
            }

            // if a[ia] > b[ib+1], decrease ia, increase ib
            int delta = Math.min(ia, ib) / 2;
            if (delta == 0) {
                delta = 1;
            }
            ia -= delta;
            ib += delta;
        }

        if (ia < 0) {
            return b[k - 1];
        }

        // ib >= b.length
        return a[k - b.length - 1];
    }

    public static int find2(int[] a, int[] b, int k) {
        return find2SR(a, 0, a.length - 1, b, 0, b.length - 1, k);
    }

    public static int find2SR(int[] A, int al, int ah, int[] B, int bl, int bh, int k) {
        int aLen = ah - al + 1;
        int bLen = bh - bl + 1;

        // Guarantee the first array's size is smaller than the second one,
        // which is convenient to remaining part calculation.
        if (aLen > bLen) {
            return find2SR(B, bl, bh, A, al, ah, k);
        }

        // Base case.
        if (aLen == 0) {
            return B[bl + k - 1];
        }

        if (k == 1) {
            return Math.min(A[al], B[bl]);
        }

        // Split k, 
        // one part is distributed to A,
        // the other part is distributed to B.
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
            return find2SR(A, aPartitionIndex + 1, ah, B, bl, bh, k - ak);
        } else {
            // Drop the left part of B, and
            // do recursion on the entire current part of A, and
            // the right part of B.
            return find2SR(A, al, ah, B, bPartitionIndex + 1, bh, k - bk);
        }
    }
}

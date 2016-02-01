// https://leetcode.com/problems/perfect-squares/

package com.htyleo.algorithms;

public class PerfectSquares {
    // O(n) space
    public int numSquares(int n) {
        int[] result = new int[n + 1];
        return numSquaresSR(result, n);
    }

    private int numSquaresSR(int[] result, int n) {
        if (result[n] != 0) {
            return result[n];
        }

        if (n <= 3) {
            return n;
        }

        int opt = n;
        for (int factor = 1;; factor++) {
            int square = factor * factor;
            if (square > n) {
                break;
            }
            opt = Math.min(opt, numSquaresSR(result, n - square) + 1);
        }
        result[n] = opt;

        return opt;
    }
}

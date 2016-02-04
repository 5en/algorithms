// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

package com.htyleo.algorithms;

public class LongestIncreasingPathInAMatrix {
    // O(M*N) time, O(M*N) space
    public int longestIncreasingPath(int[][] matrix) {
        int M = matrix.length;
        if (M == 0) {
            return 0;
        }
        int N = matrix[0].length;
        int[][] result = new int[M][N];

        int opt = 0;
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                opt = Math.max(opt, lip(matrix, result, row, col));
            }
        }
        return opt;
    }

    private int lip(int[][] matrix, int[][] result, int row, int col) {
        if (result[row][col] > 0) {
            return result[row][col];
        }

        int opt = 1;
        if (row - 1 >= 0 && matrix[row][col] < matrix[row - 1][col]) {
            opt = Math.max(opt, 1 + lip(matrix, result, row - 1, col));
        }
        if (row + 1 < matrix.length && matrix[row][col] < matrix[row + 1][col]) {
            opt = Math.max(opt, 1 + lip(matrix, result, row + 1, col));
        }
        if (col - 1 >= 0 && matrix[row][col] < matrix[row][col - 1]) {
            opt = Math.max(opt, 1 + lip(matrix, result, row, col - 1));
        }
        if (col + 1 < matrix[0].length && matrix[row][col] < matrix[row][col + 1]) {
            opt = Math.max(opt, 1 + lip(matrix, result, row, col + 1));
        }

        result[row][col] = opt;
        return opt;
    }
}

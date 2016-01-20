// https://leetcode.com/problems/search-a-2d-matrix-ii/

package com.htyleo.algorithms;

public class SearchA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        int N = matrix[0].length;

        int row = 0;
        int col = N - 1;
        // start at the top right corner
        while (row < M && col >= 0) {
            if (matrix[row][col] > target) {
                // if smaller than target, move left
                col--;
            } else if (matrix[row][col] < target) {
                // if greater than target, move down
                row++;
            } else {
                return true;
            }
        }

        return false;
    }
}

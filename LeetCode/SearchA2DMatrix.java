// https://leetcode.com/problems/search-a-2d-matrix/

package com.htyleo.algorithms;

public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        int N = matrix[0].length;
        int low = 0;
        int high = M * N - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int[] midIndex = parse(N, mid);
            int midRow = midIndex[0];
            int midCol = midIndex[1];
            if (matrix[midRow][midCol] < target) {
                low = mid + 1;
            } else if (matrix[midRow][midCol] > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }

    // return [row, col]
    private int[] parse(int numCol, int num) {
        int row = num / numCol;
        int col = num % numCol;
        return new int[] { row, col };
    }
}

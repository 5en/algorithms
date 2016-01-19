// https://leetcode.com/problems/rotate-image/

package com.htyleo.algorithms;

public class RotateImage {
    /*
        [1]  (2)  {3}  [4]      [13] (9)  {5}  [1]
        {5}  #6#  #7#  (8)  =>  {14} #10# #6#  (2)
        (9)  #10# #11# {12}     (15) #11# #7#  {3}
        [13] {14} (15) [16]     [16] {12} (8)  [4]

        m[i][j]     =>  m[j][n-1-i]
    */
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        int halfFloorN = N / 2;
        for (int row = 0; row < halfFloorN; row++) {
            for (int col = 0; col <= halfFloorN; col++) {
                if (col == halfFloorN && N % 2 == 0) {
                    continue;
                }

                int newRow;
                int newCol;
                int val = matrix[row][col];
                for (int i = 0; i < 4; i++) {
                    newRow = col;
                    newCol = N - 1 - row;
                    int tmp = matrix[newRow][newCol];
                    matrix[newRow][newCol] = val;
                    row = newRow;
                    col = newCol;
                    val = tmp;
                }
            }
        }
    }
}

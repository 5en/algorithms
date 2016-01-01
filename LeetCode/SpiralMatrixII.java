// https://leetcode.com/problems/spiral-matrix-ii/

package com.htyleo.algorithms;

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        Direct d = new Direct();
        int row = 0;
        int col = 0;
        for (int i = 1; i <= n * n; i++) {
            result[row][col] = i;

            if (i == n * n) {
                break;
            }

            int newRow = row + d.rowInc;
            int newCol = col + d.colInc;
            while (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n
                   || result[newRow][newCol] != 0) {
                d.nextDirect();
                newRow = row + d.rowInc;
                newCol = col + d.colInc;
            }

            row = newRow;
            col = newCol;
        }

        return result;
    }

    private static class Direct {
        public int flag   = 0; // 0 right, 1 down, 2 left, 3 up
        public int rowInc = 0;
        public int colInc = 1;

        public void nextDirect() {
            flag = (flag + 1) % 4;
            switch (flag) {
                case 0:
                    rowInc = 0;
                    colInc = 1;
                    break;
                case 1:
                    rowInc = 1;
                    colInc = 0;
                    break;
                case 2:
                    rowInc = 0;
                    colInc = -1;
                    break;
                case 3:
                    rowInc = -1;
                    colInc = 0;
                    break;
            }
        }
    }
}

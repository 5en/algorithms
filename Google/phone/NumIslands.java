// Given a 2-dimension array of 1s and 0s, find the number of different island

package com.htyleo.algorithms;

public class NumIslands {
    public static void main(String[] args) {
        // 1 0 1
        // 0 1 1
        // 1 0 1
        int[][] m = { { 1, 0, 1 }, { 0, 1, 1 }, { 1, 0, 1 } };
        System.out.println(numIslands(m));
    }

    public static int numIslands(int[][] m) {
        int count = 0;
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[0].length; col++) {
                count += search(m, row, col);
            }
        }

        return count;
    }

    private static int search(int[][] m, int row, int col) {
        int NUM_ROWS = m.length;
        int NUM_COLS = m[0].length;
        if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLS) {
            return 0;
        }

        if (m[row][col] != 1) {
            return 0;
        }

        m[row][col] = -1;
        search(m, row - 1, col);
        search(m, row + 1, col);
        search(m, row, col - 1);
        search(m, row, col + 1);

        return 1;
    }
}

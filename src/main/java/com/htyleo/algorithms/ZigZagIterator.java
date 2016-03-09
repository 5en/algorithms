package com.htyleo.algorithms;

public class ZigZagIterator {
    public static void main(String[] args) {
        int[][] m = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        ZigZag zz = new ZigZag(m);

        for (int i = 0; i < m.length * m[0].length; i++) {
            System.out.println(zz.next());
        }
    }

    //  1   2   3   4
    //  5   6   7   8
    //  9   10  11  12
    //  13  14  15  16

    private static class ZigZag {
        private final int[][] m;
        private final int     NUM_ROW;
        private final int     NUM_COL;
        int                   row   = 0;
        int                   col   = 0;
        int                   state = 0; // 0: go along the edge, 1: down, 2: up

        public ZigZag(int[][] m) {
            this.m = m;
            this.NUM_ROW = m.length;
            this.NUM_COL = m[0].length;
        }

        public int next() {
            int result = m[row][col];

            switch (state) {
                case 0:
                    if (row == 0 || col == NUM_COL - 1) {
                        if (col < NUM_COL - 1) {
                            col++;
                        } else {
                            row++;
                        }
                        state = 1;
                    } else if (col == 0 || row == NUM_ROW - 1) {
                        if (row < NUM_ROW - 1) {
                            row++;
                        } else {
                            col++;
                        }
                        state = 2;
                    }
                    break;
                case 1:
                    row++;
                    col--;
                    if (row == NUM_ROW - 1 || col == 0) {
                        state = 0;
                    }
                    break;
                case 2:
                    row--;
                    col++;
                    if (row == 0 || col == NUM_COL - 1) {
                        state = 0;
                    }
                    break;
            }

            return result;
        }
    }
}

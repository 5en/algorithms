// Given N*N matrix, support 2 operations,
// update(x,y,v), update matrix[x][y] to be v
// query(x1,y1,x2,y2), return sum of matrix[x1…x2][y1…y2]
// Follow up if frequently calling query, design a more efficient data structure

package com.htyleo.algorithms;

public class MatrixSum {
    public static void main(String[] args) {
        // 1 2 3
        // 4 5 6
        // 7 8 9
        int[][] m = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println(matrixSum(m, 0, 0, 1, 1));
        System.out.println(matrixSum(m, 1, 1, 2, 2));
        System.out.println(matrixSum(m, 0, 0, 2, 2));
    }

    public static int matrixSum(int[][] m, int x1, int y1, int x2, int y2) {
        int M = m.length;
        int N = m[0].length;
        // cache[x][y] is the sum from m[0][0] to m[x][y]
        int[][] cache = new int[M][N];
        cache[0][0] = m[0][0];
        for (int y = 1; y < N; y++) {
            cache[0][y] = m[0][y] + cache[0][y - 1];
        }
        for (int x = 1; x < M; x++) {
            cache[x][0] = m[x][0] + cache[x - 1][0];
        }
        for (int x = 1; x < M; x++) {
            for (int y = 1; y < N; y++) {
                cache[x][y] = m[x][y] + cache[x - 1][y] + cache[x][y - 1] - cache[x - 1][y - 1];
            }
        }

        int result = cache[x2][y2];
        if (x1 - 1 >= 0) {
            result -= cache[x1 - 1][y2];
        }
        if (y1 - 1 >= 0) {
            result -= cache[x2][y1 - 1];
        }
        if (x1 - 1 >= 0 && y1 - 1 >= 0) {
            result += cache[x1 - 1][y1 - 1];
        }
        return result;
    }

}

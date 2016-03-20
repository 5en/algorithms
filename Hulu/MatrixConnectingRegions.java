// given a 0,1 matrix, count the 1's connecting regions that are not connected to the boundaries

package com.htyleo.algorithms;

import java.util.ArrayDeque;
import java.util.Queue;

public class MatrixConnectingRegions {
    public static void main(String[] args) {
        // 1 0 1
        // 0 1 0
        // 1 0 1
        int[][] m = { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } };
        System.out.println(count(m));
        m = new int[][] { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } };
        System.out.println(count2(m));

        // 1 0 1
        // 0 1 1
        // 1 0 1
        int[][] m2 = { { 1, 0, 1 }, { 0, 1, 1 }, { 1, 0, 1 } };
        System.out.println(count(m2));
        m2 = new int[][] { { 1, 0, 1 }, { 0, 1, 1 }, { 1, 0, 1 } };
        System.out.println(count2(m2));
    }

    public static int count(int[][] m) {
        int M = m.length;
        int N = m[0].length;

        int count = 0;
        for (int row = 1; row < M - 1; row++) {
            for (int col = 1; col < N - 1; col++) {
                count += bfs(m, row, col);
            }
        }

        return count;
    }

    private static int bfs(int[][] m, int row, int col) {
        if (m[row][col] != 1) {
            return 0;
        }

        int result = 1;

        int M = m.length;
        int N = m[0].length;
        int startPos = row * N + col;
        Queue<Integer> mainQ = new ArrayDeque<Integer>();
        mainQ.add(startPos);
        Queue<Integer> auxQ = new ArrayDeque<Integer>();

        while (!mainQ.isEmpty()) {
            while (!mainQ.isEmpty()) {
                Integer currPos = mainQ.remove();
                int currRow = currPos / N;
                int currCol = currPos % N;

                if (currRow == 0 || currRow == M - 1 || currCol == 0 || currCol == N - 1) {
                    // connect the border
                    result = 0;
                }

                m[currRow][currCol] = -1;

                if (currRow - 1 >= 0 && m[currRow - 1][currCol] == 1) {
                    auxQ.add((currRow - 1) * N + currCol);
                }
                if (currRow + 1 < M && m[currRow + 1][currCol] == 1) {
                    auxQ.add((currRow + 1) * N + currCol);
                }
                if (currCol - 1 >= 0 && m[currRow][currCol - 1] == 1) {
                    auxQ.add(currRow * N + currCol - 1);
                }
                if (currCol + 1 < N && m[currRow][currCol + 1] == 1) {
                    auxQ.add(currRow * N + currCol + 1);
                }
            }

            Queue<Integer> tmpQ = mainQ;
            mainQ = auxQ;
            auxQ = tmpQ;
        }

        return result;
    }

    public static int count2(int[][] m) {
        int count = 0;

        int M = m.length;
        int N = m[0].length;
        for (int row = 1; row < M - 1; row++) {
            for (int col = 1; col < N - 1; col++) {
                int pos = row * N + col;
                count += (dfs(m, pos) == 1 ? 1 : 0);
            }
        }

        return count;
    }

    private static int dfs(int[][] m, int pos) {
        int M = m.length;
        int N = m[0].length;
        int row = pos / N;
        int col = pos % N;
        if (m[row][col] == 1 && (row == 0 || row == M - 1 || col == 0 || col == N - 1)) {
            return -1;
        }
        if (m[row][col] != 1) {
            return 0;
        }

        m[row][col] = -1;

        int count = 1;
        if (row - 1 >= 0) {
            if (dfs(m, (row - 1) * N + col) == -1) {
                count = -1;
            }
        }
        if (row + 1 < M) {
            if (dfs(m, (row + 1) * N + col) == -1) {
                count = -1;
            }
        }
        if (col - 1 >= 0) {
            if (dfs(m, row * N + col - 1) == -1) {
                count = -1;
            }
        }
        if (col + 1 >= 0) {
            if (dfs(m, row * N + col + 1) == -1) {
                count = -1;
            }
        }

        return count;
    }
}

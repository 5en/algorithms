// https://leetcode.com/problems/maximal-square/

import java.util.Random;

public class MaximalSquare {
    public static int maximalSquare(char[][] matrix) {
        if (new Random().nextInt() % 2 == 0) {
            return maximalSquare1(matrix);
        } else {
            return maximalSquare2(matrix);
        }
    }

    // O(H*W) time, O(min(H,W)) space
    public static int maximalSquare1(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int maxSize = 0;

        int H = matrix.length;
        int W = matrix[0].length;
        if (W <= H) {
            int[] prevRowSizes = new int[W];
            for (int col = 0; col < W; col++) {
                prevRowSizes[col] = matrix[0][col] == '1' ? 1 : 0;
                maxSize = Math.max(maxSize, prevRowSizes[col]);
            }
            int[] currRowSizes = new int[W];
            for (int row = 1; row < H; row++) {
                for (int col = 0; col < W; col++) {
                    int size = 0;
                    if (matrix[row][col] == '1') {
                        size = 1;
                        if (col - 1 >= 0) {
                            size = Math.min(Math.min(currRowSizes[col-1], prevRowSizes[col]), prevRowSizes[col-1]) + 1;
                        }
                    }
                    currRowSizes[col] = size;
                    maxSize = Math.max(maxSize, size);
                }

                int[] tmp = prevRowSizes;
                prevRowSizes = currRowSizes;
                currRowSizes = tmp;
            }
        } else {
            int[] prevColSizes = new int[H];
            for (int row = 0; row < H; row++) {
                prevColSizes[row] = matrix[row][0] == '1' ? 1 : 0;
                maxSize = Math.max(maxSize, prevColSizes[row]);
            }
            int[] currColSizes = new int[H];
            for (int col = 1; col < W; col++) {
                for (int row = 0; row < H; row++) {
                    int size = 0;
                    if (matrix[row][col] == '1') {
                        size = 1;
                        if (row - 1 >= 0) {
                            size = Math.min(Math.min(currColSizes[row-1], prevColSizes[row]), prevColSizes[row-1]) + 1;
                        }
                    }
                    currColSizes[row] = size;
                    maxSize = Math.max(maxSize, size);
                }

                int[] tmp = prevColSizes;
                prevColSizes = currColSizes;
                currColSizes = tmp;
            }
        }

        return maxSize * maxSize;
    }

    // O(H*W) time, O(H*W) space
    public static int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int maxSize = 0;

        int H = matrix.length;
        int W = matrix[0].length;
        int[][] sizes = new int[H][W];
        for (int row = 0; row < H; row++) {
            for (int col = 0; col < W; col++) {
                int size = 0;
                if (matrix[row][col] == '1') {
                    size = 1;
                    if (row - 1 >= 0 && col - 1 >= 0) {
                        size = Math.min(Math.min(sizes[row-1][col], sizes[row][col-1]), sizes[row-1][col-1]) + 1;
                    }
                }
                sizes[row][col] = size;
                maxSize = Math.max(maxSize, size);
            }
        }

        return maxSize * maxSize;
    }
}

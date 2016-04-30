// find max sum(nums[start, end]), end - start + 1 == size
// follow up: 2D window

package com.htyleo.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWindowSum {

    public static void main(String[] args) {
        int[] nums = { 2, 3, 4, 4, 5, 3, 2 };
        System.out.println(maxWindowSum(nums, 3));

        // 1  2  3  4
        // 5  6  7  8
        // 9  10 11 12
        // 13 14 15 16
        int[][] m = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        System.out.println(maxWindowSum2D(m, 2, 2));
    }

    public static int maxWindowSum(int[] nums, int size) {
        if (nums.length < size) {
            return 0;
        }

        // keep a queue
        Queue<Integer> q = new LinkedList<Integer>();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            q.add(nums[i]);
            sum += nums[i];
        }

        int maxSum = sum;
        for (int i = size; i < nums.length; i++) {
            sum -= q.remove();
            q.add(nums[i]);
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    //  a1  a2  a3  a4  a5
    //  b1 (b2  b3  b4) b5
    //  c1 (c2  c3  c4) c5
    //  d1 (d2  d3  d4) d5
    public static int maxWindowSum2D(int[][] m, int rowLen, int colLen) {
        // prepare cache[i][j] = sum{m[0][0] ... m[i][j]}
        int NUM_ROWS = m.length;
        int NUM_COLS = m[0].length;
        int[][] cache = new int[NUM_ROWS][NUM_COLS];
        cache[0][0] = m[0][0];
        for (int y = 1; y < NUM_COLS; y++) {
            cache[0][y] = m[0][y] + cache[0][y - 1];
        }
        for (int x = 1; x < NUM_ROWS; x++) {
            cache[x][0] = m[x][0] + cache[x - 1][0];
        }
        for (int x = 1; x < NUM_ROWS; x++) {
            for (int y = 1; y < NUM_COLS; y++) {
                cache[x][y] = m[x][y] + cache[x - 1][y] + cache[x][y - 1] - cache[x - 1][y - 1];
            }
        }

        int maxSum = 0;
        for (int x = 0; x + rowLen - 1 < NUM_ROWS; x++) {
            for (int y = 0; y + colLen - 1 < NUM_COLS; y++) {
                int sum = regionSum(cache, x, y, x + rowLen - 1, y + colLen - 1);
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    private static int regionSum(int[][] cache, int x1, int y1, int x2, int y2) {
        int result = cache[x2][y2];
        if (x1 - 1 >= 0) {
            result -= cache[x1 - 1][y2];
        }
        if (y1 - 1 >= 0) {
            result -= cache[x2][y1 - 1];
        }
        if (x1 - 1 >= 0 && y1 - 1 >= 0) {
            result += cache[y1 - 1][y1 - 1];
        }

        return result;
    }
}

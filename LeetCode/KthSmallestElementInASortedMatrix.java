// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

package com.htyleo.algorithms;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KthSmallestElementInASortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int NUM_ROWS = matrix.length;
        int NUM_COLS = matrix[0].length;

        PriorityQueue<Num> minHeap = new PriorityQueue<Num>(new Comparator<Num>() {
            @Override
            public int compare(Num num1, Num num2) {
                return Integer.compare(num1.val, num2.val);
            }
        });

        Set<Integer> visited = new HashSet<Integer>();

        minHeap.add(new Num(0, matrix[0][0]));
        visited.add(0);
        for (int i = 1; i <= k - 1; i++) {
            Num num = minHeap.remove();
            int row = num.index / NUM_COLS;
            int col = num.index % NUM_COLS;

            if (row + 1 < NUM_ROWS) {
                int index = (row + 1) * NUM_COLS + col;
                if (!visited.contains(index)) {
                    minHeap.add(new Num(index, matrix[row + 1][col]));
                    visited.add(index);
                }
            }
            if (col + 1 < NUM_COLS) {
                int index = row * NUM_COLS + (col + 1);
                if (!visited.contains(index)) {
                    minHeap.add(new Num(index, matrix[row][col + 1]));
                    visited.add(index);
                }
            }
        }

        return minHeap.remove().val;
    }

    private static class Num {
        public final int index;
        public final int val;

        public Num(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

}

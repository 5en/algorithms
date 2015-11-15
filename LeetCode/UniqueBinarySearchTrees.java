// https://leetcode.com/problems/unique-binary-search-trees/

package com.htyleo.algorithms;

public class UniqueBinarySearchTrees {
    // O(N^2) time, O(N) space
    public static int numTrees(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] result = new int[n + 1]; // result[i] is the number of trees given i nodes.
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int numLeft = 0; numLeft <= i - 1; numLeft++) {
                int numRight = i - 1 - numLeft;
                result[i] += result[numLeft] * result[numRight];
            }
        }

        return result[n];
    }
}

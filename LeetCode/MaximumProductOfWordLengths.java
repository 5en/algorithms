// https://leetcode.com/problems/maximum-product-of-word-lengths/

package com.htyleo.algorithms;

public class MaximumProductOfWordLengths {
    // O(N^2 time), O(N) space
    public int maxProduct(String[] words) {
        int N = words.length;

        int[] masks = new int[N]; // masks[i] indicates the characters of words[i]
        for (int i = 0; i < N; i++) {
            String word = words[i];
            for (int ci = 0; ci < word.length(); ci++) {
                masks[i] |= 1 << (word.charAt(ci) - 'a');
            }
        }

        int opt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    opt = Math.max(opt, words[i].length() * words[j].length());
                }
            }
        }

        return opt;
    }
}

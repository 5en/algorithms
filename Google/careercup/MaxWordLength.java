// Given an array of words (i.e. ["ABCW", "BAZ", "FOO", "BAR", "XTFN", "ABCDEF"]), 
// find the max value of length(s) * length(t), where s and t are words from the array. 
// The catch here is that the two words cannot share any characters.

package com.htyleo.algorithms;

public class MaxWordLength {
    public static void main(String[] args) {
        String[] words = { "ABCW", "BAZ", "FOO", "BAR", "XTFN", "ABCDEF" };
        System.out.println(maxWordLength(words));
    }

    // O(N*M + N^2)
    // # of words == N
    // average word length == M
    public static int maxWordLength(String[] words) {

        // indicator[i] ~ 'A'+i ~ ith position is 1
        int[] indicators = new int[26];
        int indicator = 1;
        for (int i = 0; i < 26; i++) {
            indicators[i] = indicator;
            indicator <<= 1;
        }

        int N = words.length;
        int[] wordBits = new int[N];
        for (int i = 0; i < N; i++) {
            int wordBit = 0;
            String word = words[i];
            for (int chi = 0; chi < word.length(); chi++) {
                char ch = Character.toUpperCase(word.charAt(chi));
                wordBit |= indicators[ch - 'A'];
            }
            wordBits[i] = wordBit;
        }

        int opt = 0;
        for (int i = 0; i <= N - 2; i++) {
            for (int j = i + 1; j <= N - 1; j++) {
                if ((wordBits[i] & wordBits[j]) == 0) {
                    opt = Math.max(opt, words[i].length() * words[j].length());
                }
            }
        }

        return opt;
    }
}

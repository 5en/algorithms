// https://leetcode.com/problems/remove-duplicate-letters/

package com.htyleo.algorithms;

import java.util.Arrays;

public class RemoveDuplicateLetters {
    // https://leetcode.com/discuss/73777/easy-to-understand-iterative-java-solution
    //
    //    The basic idea is to find out the smallest result letter by letter (one letter at a time). Here is the thinking process for input "cbacdcbc":
    //
    //    1. find out the last appeared position for each letter; c - 7 b - 6 a - 2 d - 4
    //    2. find out the smallest index from the map in step 1 (a - 2);
    //    3. the first letter in the final result must be the smallest letter from index 0 to index 2;
    //    4. repeat step 2 to 3 to find out remaining letters.
    //
    //    the smallest letter from index 0 to index 2: a
    //    the smallest letter from index 3 to index 4: c
    //    the smallest letter from index 4 to index 4: d
    //    the smallest letter from index 5 to index 6: b
    //    so the result is "acdb"
    //
    //    Notes:
    //
    //    1. After one letter is determined in step 3, it need to be removed from the "last appeared position map", and the same letter should be ignored in the following steps
    //    2. In step 3, the beginning index of the search range should be the index of previous determined letter plus one

    public String removeDuplicateLetters(String s) {
        int N = s.length();

        // numlastIdx[n] = i: the last index of character ('a'+n) is i
        int[] numlastIdx = new int[26];
        Arrays.fill(numlastIdx, -1);

        // isLastIdx[i]: whether i is the last index of some character
        boolean[] isLastIdx = new boolean[N];

        // preprocess
        for (int i = 0; i < N; i++) {
            char ch = s.charAt(i);

            // clear old lastIdx
            int lastIdx = numlastIdx[ch - 'a'];
            if (lastIdx != -1) {
                isLastIdx[lastIdx] = false;
            }

            // set new lastIdx
            numlastIdx[ch - 'a'] = i;
            isLastIdx[i] = true;
        }

        // remove duplicates
        boolean[] processed = new boolean[26];
        StringBuilder result = new StringBuilder();
        for (int i = 0, start = 0; i < N;) {
            if (processed[s.charAt(i) - 'a'] || !isLastIdx[i]) {
                i++;
                continue;
            }

            int minIdx = findMinIdx(s, processed, start, i);
            char min = s.charAt(minIdx);
            result.append(min);
            processed[min - 'a'] = true;

            start = minIdx + 1;
            while (start > i) {
                i++;
            }
        }

        return result.toString();
    }

    private int findMinIdx(String s, boolean[] processed, int start, int end) {
        int minIdx = end;
        char min = s.charAt(minIdx);

        for (int i = end - 1; i >= start; i--) {
            char ch = s.charAt(i);
            if (!processed[ch - 'a'] && ch <= min) {
                minIdx = i;
                min = ch;
            }
        }

        return minIdx;
    }
}

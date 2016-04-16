// https://leetcode.com/problems/remove-duplicate-letters/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RemoveDuplicateLetters {
    // https://leetcode.com/discuss/73777/easy-to-understand-iterative-java-solution
    //
    //    The basic idea is to find out the smallest result letter by letter (one letter at a time).
    //    Here is the thinking process for input "cbacdcbc":
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
    //    1. After one letter is determined in step 3, it need to be removed from the "last appeared position map", and the same letter should be ignored in the following steps
    //    2. In step 2, choose the one with the smallest index when there is a tie
    //    3. In step 3, the beginning index of the search range should be the index of previous determined letter plus one

    // O(N)
    public String removeDuplicateLetters(String s) {
        // char -> last index
        // larger index comes first
        Map<Character, Integer> lastIdx = new LinkedHashMap<Character, Integer>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (lastIdx.containsKey(ch)) {
                continue;
            }

            lastIdx.put(ch, i);
        }

        StringBuilder result = new StringBuilder();

        List<Map.Entry<Character, Integer>> lastIdxList = new ArrayList<Map.Entry<Character, Integer>>(
            lastIdx.entrySet());
        Set<Character> picked = new HashSet<Character>();
        int lb = 0;
        for (int i = lastIdxList.size() - 1; i >= 0; i--) {
            Map.Entry<Character, Integer> entry = lastIdxList.get(i);
            char ch = entry.getKey();
            int ub = entry.getValue();
            if (picked.contains(ch)) {
                continue;
            }

            while (true) {
                int minChIdx = findMinChar(picked, s, lb, ub);
                char minChar = s.charAt(minChIdx);
                picked.add(minChar);
                result.append(minChar);
                lb = minChIdx + 1;

                if (minChar == ch) {
                    break;
                }
            }
        }

        return result.toString();
    }

    // find the smallest char (choose the one with the smallest index when there is a tie)
    private int findMinChar(Set<Character> picked, String s, int lb, int ub) {
        int minChIdx = ub;
        char minCh = s.charAt(ub);

        for (int i = ub - 1; i >= lb; i--) {
            char ch = s.charAt(i);
            if (picked.contains(ch)) {
                continue;
            }

            if (ch <= minCh) {
                minCh = ch;
                minChIdx = i;
            }
        }

        return minChIdx;
    }
}

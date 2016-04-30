// Given a string, find the length of the longest substring T that contains at most K distinct characters.

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {

    public static void main(String[] args) {
        System.out.println(longestSubstring("eceba", 2));
        System.out.println(longestSubstring("eceba", 3));
    }

    // O(N*K) time
    //
    // The main idea is to maintain a sliding window with K unique characters.
    // The key is to store the last occurrence of each character as the value in the hashmap.
    // This way, whenever the size of the hashmap exceeds K,
    // we can traverse through the map to find the character with the left most index, and remove 1 character from our map.
    public static int longestSubstring(String s, int K) {
        if (s.length() == 0) {
            return 0;
        }

        int maxLen = 0;
        int left = 0;
        int right = -1;

        // keep char's right-most index
        Map<Character, Integer> chIdx = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            chIdx.put(s.charAt(i), i);
            right++;

            if (chIdx.size() > K) {
                int minIdx = s.length() - 1;
                for (int idx : chIdx.values()) {
                    minIdx = Math.min(minIdx, idx);
                }
                // remove the entry with left-most index
                chIdx.remove(s.charAt(minIdx));
                left = minIdx + 1;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

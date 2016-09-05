// https://leetcode.com/contest/3/problems/longest-substring-with-at-least-k-repeating-characters/

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithAtLeastKRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring(
            "aaabb", 3));
        System.out.println(new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring(
            "ababbc", 2));
    }

    public int longestSubstring(String s, int k) {
        Map<Character, Integer> chCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            chCount.put(ch, chCount.containsKey(ch) ? chCount.get(ch) + 1 : 1);
        }

        Set<Character> excludes = new HashSet<>();
        for (char ch : chCount.keySet()) {
            if (chCount.get(ch) < k) {
                excludes.add(ch);
            }
        }

        // the whole string satisfies
        if (nextExcludeIndex(s, excludes, 0) == s.length()) {
            return s.length();
        }

        // partition
        int maxLen = 0;
        for (int start = nextIncludeIndex(s, excludes, 0); start < s.length();) {
            int end = nextExcludeIndex(s, excludes, start);
            if (start < s.length() && end > start && end <= s.length()) {
                maxLen = Math.max(maxLen, longestSubstring(s.substring(start, end), k));
            }
            start = nextIncludeIndex(s, excludes, end + 1);
        }

        return maxLen;
    }

    private int nextIncludeIndex(String s, Set<Character> excludes, int start) {
        for (int i = start; i < s.length(); i++) {
            if (!excludes.contains(s.charAt(i))) {
                return i;
            }
        }

        return s.length();
    }

    private int nextExcludeIndex(String s, Set<Character> excludes, int start) {
        for (int i = start; i < s.length(); i++) {
            if (excludes.contains(s.charAt(i))) {
                return i;
            }
        }

        return s.length();
    }
}

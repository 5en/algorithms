// Given str1, str2, return the length of the shortest substring of str1 that str2 is a subsequence of that substring
// For example, str1 = “acbacbc” str2=”abc”, the shortest substring is “acbc” which has length of 4.

package com.htyleo.algorithms;

public class ShortestSubstringContainsSubsequence {
    public static void main(String[] args) {
        System.out.println(shortestSubstring("acbacbc", "abc"));
    }

    // O(N^2) time
    public static int shortestSubstring(String s1, String s2) {
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(0)) {
                continue;
            }

            int j1 = i;
            int j2 = 0;
            while (j1 < s1.length() && j2 < s2.length()) {
                if (s1.charAt(j1) == s2.charAt(j2)) {
                    j1++;
                    j2++;
                } else {
                    j1++;
                }
            }

            // fount
            if (j2 == s2.length()) {
                minLen = Math.min(minLen, j1 - i);
            }
        }

        return minLen;
    }
}

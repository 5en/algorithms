// https://leetcode.com/problems/reverse-words-in-a-string/

package com.htyleo.algorithms;

public class ReverseWordsInAString {

    // First, reverse the whole string
    // Then reverse each word.
    public String reverseWords(String s) {
        s = reverse(s);

        StringBuilder result = new StringBuilder();
        int left = nextChar(s, 0);
        int right = nextNonChar(s, left + 1);
        while (left < s.length()) {
            result.append(reverse(s.substring(left, right))).append(" ");
            left = nextChar(s, right + 1);
            right = nextNonChar(s, left + 1);
        }

        if (result.length() > 0) {
            result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return sb.toString();
    }

    private int nextChar(String s, int start) {
        for (; start < s.length() && s.charAt(start) == ' '; start++) {
        }

        return start;
    }

    private int nextNonChar(String s, int start) {
        for (; start < s.length() && s.charAt(start) != ' '; start++) {
        }

        return start;
    }

}

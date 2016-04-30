// Given a list of strings, find all pairs of strings that can be concatenated to be palindrome.
// For example: bana nab

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PairPalindrome {
    public static void main(String[] args) {
        String[] strs = { "bana", "nab", "cooc", "ooc", "abedd", "eba" };
        for (String[] pair : pairPalindrome(strs)) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }

    // for every XY, if X is a palindrome, see if Y’ exists
    // for every XY, if Y is a palindrome, see if X’ exists

    public static List<String[]> pairPalindrome(String[] strs) {
        Set<String> strSet = new HashSet<String>(Arrays.asList(strs));

        List<String[]> result = new ArrayList<String[]>();
        for (String s : strs) {
            for (int right = 0; right < s.length() - 1; right++) {
                String X = s.substring(0, right + 1);
                if (isPalindrome(X)) {
                    String Y = s.substring(right + 1, s.length());
                    String rY = reverse(Y);
                    if (strSet.contains(rY)) {
                        result.add(new String[] { s, rY });
                    }
                }
            }

            for (int left = s.length() - 1; left > 0; left--) {
                String Y = s.substring(left, s.length());
                if (isPalindrome(Y)) {
                    String X = s.substring(0, left);
                    String rX = reverse(X);
                    if (strSet.contains(rX)) {
                        result.add(new String[] { s, rX });
                    }
                }
            }
        }

        return result;
    }

    private static boolean isPalindrome(String s) {
        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }

        return true;
    }

    private static String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);
        }

        return sb.toString();
    }

}

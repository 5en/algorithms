// Given a string, return the longest palindrome consisting of the characters in the string.
// Follow up how many of such palindrome?

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aabbcc"));
        System.out.println(longestPalindrome("aabbbccc"));
    }

    // Na, Nb, Nc
    // # of such palindromes:
    //      N = (Na / 2) + (Nb /2) + (Nc / 2)
    //      C(N, Na) + C(N-Na, Nb) + C(N-Na-Nb, Nc)
    public static String longestPalindrome(String s) {
        Map<Character, Integer> charCount = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            charCount.put(ch, charCount.containsKey(ch) ? charCount.get(ch) + 1 : 1);
        }

        StringBuilder sb = new StringBuilder();
        boolean hasOdd = false;
        int midIdx = 0;
        for (char ch : charCount.keySet()) {
            int count = charCount.get(ch);
            for (int i = 0; i < count / 2; i++) {
                sb.insert(0, ch);
                sb.append(ch);
                midIdx++;
            }

            if (count % 2 == 1) {
                if (hasOdd) {
                    continue;
                }
                sb.insert(midIdx, ch);
                hasOdd = true;
            }
        }

        return sb.toString();
    }

}

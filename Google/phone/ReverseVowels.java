// Given a string, reverse all vowels.
// For example, “united states” -> “enated stitus”

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReverseVowels {
    public static void main(String[] args) {
        System.out.println(reverseVowels("united states"));
    }

    // O(N) time, O(N) space
    public static String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<Character>();
        vowels.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        List<Integer> vis = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                vis.add(i);
            }
        }

        StringBuilder sb = new StringBuilder(s);
        for (int left = 0, right = vis.size() - 1; left < right; left++, right--) {
            char tmp = sb.charAt(vis.get(left));
            sb.setCharAt(vis.get(left), sb.charAt(vis.get(right)));
            sb.setCharAt(vis.get(right), tmp);
        }

        return sb.toString();
    }
}

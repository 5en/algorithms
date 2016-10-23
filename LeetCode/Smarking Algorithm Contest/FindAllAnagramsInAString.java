// https://leetcode.com/contest/10/problems/find-all-anagrams-in-a-string/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {

        Map<Character, Integer> pattern = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            inc(pattern, p.charAt(i));
        }

        List<Integer> result = new ArrayList<>();

        Map<Character, Integer> chCount = new HashMap<>();
        for (int i = 0; i < s.length() && i < p.length(); i++) {
            char ch = s.charAt(i);
            inc(chCount, ch);
        }
        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (pattern.equals(chCount)) {
                result.add(i);
            }
            dec(chCount, s.charAt(i));
            if (i + p.length() < s.length()) {
                inc(chCount, s.charAt(i + p.length()));
            }
        }

        return result;
    }

    private static void inc(Map<Character, Integer> map, char ch) {
        map.put(ch, map.containsKey(ch) ? map.get(ch) + 1 : 1);
    }

    private static void dec(Map<Character, Integer> map, char ch) {
        map.put(ch, map.get(ch) - 1);
        if (map.get(ch) == 0) {
            map.remove(ch);
        }
    }

}

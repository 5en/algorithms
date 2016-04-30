// Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
// We can keep "shifting" which forms the sequence:
// "abc" -> "bcd" -> ... -> "xyz"
// Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
// For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
// Return:
// [  ["abc","bcd","xyz"],  ["az","ba"],  ["acef"],  ["a","z"]]

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings {
    public static void main(String[] args) {
        String[] strs = { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" };
        System.out.println(group(strs));
    }

    // The basic idea is to set a key for each group:
    // the sum of the difference between the adjacent chars in one string.
    public static List<List<String>> group(String[] strs) {
        Map<String, List<String>> groups = new HashMap<String, List<String>>();
        for (String s : strs) {
            StringBuilder keySb = new StringBuilder();
            for (int i = 1; i < s.length(); i++) {
                keySb.append((s.charAt(i) - s.charAt(i - 1) + 26) % 26);
            }
            String key = keySb.toString();
            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<String>());
            }
            groups.get(key).add(s);
        }

        return new ArrayList<List<String>>(groups.values());
    }
}

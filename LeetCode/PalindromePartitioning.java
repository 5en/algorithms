// https://leetcode.com/problems/palindrome-partitioning/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        return helper(new HashMap<String, List<List<String>>>(), s);
    }

    private List<List<String>> helper(Map<String, List<List<String>>> cache, String s) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        List<List<String>> partitions = new ArrayList<List<String>>();

        // "a" -> [[a]]
        if (s.length() == 1) {
            List<String> partition = new LinkedList<String>();
            partition.add(s);
            partitions.add(partition);
            cache.put(s, partitions);
            return partitions;
        }

        for (int i = 0; i < s.length(); i++) {
            // [0, i]
            String sub1 = s.substring(0, i + 1);
            if (!isParlindrome(sub1)) {
                continue;
            }

            // [i+1, s.length()-1]
            String sub2 = (i == s.length() - 1) ? null : s.substring(i + 1, s.length());
            if (sub2 == null) {
                List<String> partition = new LinkedList<String>();
                partition.add(sub1);
                partitions.add(partition);
            } else {
                for (List<String> sub2Partition : helper(cache, sub2)) {
                    List<String> partition = new LinkedList<String>();
                    partition.add(sub1);
                    partition.addAll(sub2Partition);
                    partitions.add(partition);
                }
            }
        }

        cache.put(s, partitions);
        return partitions;
    }

    private boolean isParlindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}

// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/#/description

package com.htyleo.algorithms;

import java.util.List;

/**
 * Created by htyleo on 6/18/17.
 */
public class LongestWordInDictionaryThroughDeleting {

  // O(N*L) time, O(N) space, where N is the size of the dictionary and L is the length of s.
  public String findLongestWord(String s, List<String> d) {
    // reach[i]: next character to be matched for d[i] is d[i].charAt(reach[i])
    int[] reach = new int[d.size()];
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      for (int j = 0; j < d.size(); j++) {
        if (reach[j] < d.get(j).length() && d.get(j).charAt(reach[j]) == ch) {
          reach[j]++;
        }
      }
    }

    String res = "";
    int maxLen = 0;
    for (int j = 0; j < d.size(); j++) {
      int len = d.get(j).length();
      if (reach[j] != len) {
        continue;
      }
      if (len > maxLen || (len == maxLen && d.get(j).compareTo(res) < 0)) {
        res = d.get(j);
        maxLen = len;
      }
    }

    return res;
  }
}

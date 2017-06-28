// https://leetcode.com/problems/unique-substrings-in-wraparound-string/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/28/17.
 */
public class UniqueSubstringsInWraparoundString {

  // O(N) time
  public int findSubstringInWraproundString(String p) {
    // lens[i]: max length of substring that starts with character 'a' + i
    int[] lens = new int[26];

    for (int start = 0, i = 1; i <= p.length(); i++) {
      if (i == p.length() || p.charAt(i) - 'a' != (p.charAt(i - 1) - 'a' + 1) % 26) {
        // update length for substrings starting at p[start], p[start + 1], ..., p[i - 1]
        for (int j = start; j < i; j++) {
          lens[p.charAt(j) - 'a'] = Math.max(lens[p.charAt(j) - 'a'], i - j);
        }
        start = i;
      }
    }

    int res = 0;
    for (int i = 0; i < 26; i++) {
      res += lens[i];
    }

    return res;
  }
}

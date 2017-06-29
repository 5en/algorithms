// https://leetcode.com/problems/longest-uncommon-subsequence-ii/#/description

package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by htyleo on 6/29/17.
 */
public class LongestUncommonSubsequenceII {

  public int findLUSlength(String[] strs) {
    Set<String> used = new HashSet<>();
    Set<String> invalid = new HashSet<>();
    for (String s : strs) {
      if (used.contains(s)) {
        invalid.add(s);
      } else {
        used.add(s);
      }
    }

    // sort in descending order of length
    Arrays.sort(strs, (s1, s2) -> s1.length() >= s2.length() ? -1 : 1);
    for (String s : strs) {
      if (invalid.contains(s)) {
        continue;
      }

      boolean valid = true;
      for (String t : invalid) {
        if (isSubsequence(s, t)) {
          valid = false;
          break;
        }
      }

      if (valid) {
        return s.length();
      } else {
        invalid.add(s);
      }
    }

    return -1;
  }

  // check whether s is a subsequqnce of t
  private boolean isSubsequence(String s, String t) {
    if (s.length() > t.length()) {
      return false;
    }

    for (int si = 0, ti = 0; ti < t.length(); ti++) {
      if (s.length() - si > t.length() - ti) {
        return false;
      }

      if (s.charAt(si) == t.charAt(ti)) {
        si++;
        if (si == s.length()) {
          return true;
        }
      }
    }

    return false;
  }

}

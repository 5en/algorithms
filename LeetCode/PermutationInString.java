// https://leetcode.com/problems/permutation-in-string/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/23/17.
 */
public class PermutationInString {

  // O(L1 + L2) time, O(26) space
  public boolean checkInclusion(String s1, String s2) {
    int[] s1cnt = new int[26];
    for (int i = 0; i < s1.length(); i++) {
      s1cnt[s1.charAt(i) - 'a']++;
    }

    // matched keeps track of how many characters are matched
    int matched = 0;
    for (int i = 0; i < 26; i++) {
      if (s1cnt[i] == 0) {
        matched++;
      }
    }

    // sliding window
    int[] cnt = new int[26];

    for (int i = 0; i < s2.length(); i++) {
      // remove left char
      if (i >= s1.length()) {
        int outIdx = s2.charAt(i - s1.length()) - 'a';
        cnt[outIdx]--;

        if (cnt[outIdx] == s1cnt[outIdx]) {
          matched++;
        } else if (cnt[outIdx] == s1cnt[outIdx] - 1) {
          matched--;
        }
      }

      // add right char
      int inIdx = s2.charAt(i) - 'a';
      cnt[inIdx]++;
      if (cnt[inIdx] == s1cnt[inIdx]) {
        matched++;
      } else if (cnt[inIdx] == s1cnt[inIdx] + 1) {
        matched--;
      }

      if (matched == 26) {
        return true;
      }
    }

    return false;
  }

  // O(L1 + 26*L2) time, O(26) space
  public boolean checkInclusion2(String s1, String s2) {
    int[] s1cnt = new int[26];
    for (int i = 0; i < s1.length(); i++) {
      s1cnt[s1.charAt(i) - 'a']++;
    }

    // sliding window
    int[] cnt = new int[26];
    for (int i = 0; i < s2.length(); i++) {
      if (i >= s1.length()) {
        cnt[s2.charAt(i - s1.length()) - 'a']--;
      }
      cnt[s2.charAt(i) - 'a']++;
      if (arrayEqual(s1cnt, cnt)) {
        return true;
      }
    }

    return false;
  }

  private boolean arrayEqual(int[] a, int[] b) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }

    return true;
  }
}

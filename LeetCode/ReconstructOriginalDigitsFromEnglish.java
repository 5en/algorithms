// https://leetcode.com/problems/reconstruct-original-digits-from-english/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/15/17.
 */
public class ReconstructOriginalDigitsFromEnglish {

  public String originalDigits(String s) {
    // [z]ero
    // one
    // t[w]o
    // three
    // four
    // five
    // si[x]
    // seven
    // ei[g]ht
    // nine
    int[] cnt = new int[26];
    for (int i = 0; i < s.length(); i++) {
      cnt[s.charAt(i) - 'a']++;
    }

    int[] chs = new int[10];
    chs[0] = cnt['z' - 'a'];
    chs[2] = cnt['w' - 'a'];
    chs[6] = cnt['x' - 'a'];
    chs[8] = cnt['g' - 'a'];
    chs[7] = cnt['s' - 'a'] - chs[6];
    chs[5] = cnt['v' - 'a'] - chs[7];
    chs[4] = cnt['f' - 'a'] - chs[5];
    chs[3] = cnt['r' - 'a'] - chs[0] - chs[4];
    chs[1] = cnt['o' - 'a'] - chs[0] - chs[2] - chs[4];
    chs[9] = cnt['i' - 'a'] - chs[5] - chs[6] - chs[8];

    StringBuilder res = new StringBuilder();
    for (int i = 0; i < chs.length; i++) {
      for (int count = 0; count < chs[i]; count++) {
        res.append(i);
      }
    }

    return res.toString();
  }

}

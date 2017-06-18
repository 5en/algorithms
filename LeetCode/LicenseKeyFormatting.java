// https://leetcode.com/problems/license-key-formatting/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/18/17.
 */
public class LicenseKeyFormatting {

  public String licenseKeyFormatting(String S, int K) {
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < S.length(); i++) {
      if (S.charAt(i) != '-') {
        res.append(String.valueOf(S.charAt(i)).toUpperCase());
      }
    }

    int initPart = res.length() % K;
    for (int i = initPart; i < res.length(); i += K) {
      if (i != 0) {
        res.insert(i, '-');
        i++;
      }
    }

    return res.toString();
  }
}

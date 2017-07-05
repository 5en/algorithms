// https://leetcode.com/problems/remove-k-digits/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 7/5/17.
 */
public class RemoveKDigits {

  public String removeKdigits(String num, int k) {
    // find first two adjacent digits num[i] > num[j], remove num[i]
    StringBuilder sb = new StringBuilder(num);

    for (int i = 0; i < k; i++) {
      boolean found = false;
      for (int j = 1; j < sb.length(); j++) {
        ;
        if (sb.charAt(j - 1) > sb.charAt(j)) {
          sb.deleteCharAt(j - 1);
          found = true;
          break;
        }
      }

      // if all digits are in non-decreasing order, delete the last one
      if (!found && sb.length() != 0) {
        sb.deleteCharAt(sb.length() - 1);
      }

      // remove leading zero
      while (sb.length() != 0 && sb.charAt(0) == '0') {
        sb.deleteCharAt(0);
      }
    }

    return sb.length() == 0 ? "0" : sb.toString();
  }
}

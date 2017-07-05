// https://leetcode.com/problems/zigzag-conversion/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 7/5/17.
 */
public class ZigZagConversion {

  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }

    int N = s.length();
    StringBuilder res = new StringBuilder();

    // the pattern repeats with length 2*(numRows-1)
    int R = 2 * (numRows - 1);
    for (int i = 0; i <= R / 2; i++) {
      int curr = i;
      int diff = (R - curr) - curr;
      while (curr < N) {
        res.append(s.charAt(curr));

        if (diff > 0 && diff < R && curr + diff < N) {
          res.append(s.charAt(curr + diff));
        }
        curr += R;
      }
    }

    return res.toString();
  }

}

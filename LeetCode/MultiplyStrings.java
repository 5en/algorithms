// https://leetcode.com/problems/multiply-strings/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htyleo on 7/5/17.
 */
public class MultiplyStrings {

  public String multiply(String num1, String num2) {
    List<Integer> res = new ArrayList<>();

    int indent = 0;
    for (int i = num1.length() - 1; i >= 0; i--) {
      int d1 = num1.charAt(i) - '0';

      int curr = indent;
      int carry = 0;
      for (int j = num2.length() - 1; j >= 0; j--) {
        int d2 = num2.charAt(j) - '0';

        if (res.size() - 1 < curr) {
          res.add(0);
        }
        int sum = res.get(curr) + d1 * d2 + carry;
        res.set(curr, sum % 10);
        carry = sum / 10;

        curr++;
      }

      if (carry > 0) {
        if (res.size() - 1 < curr) {
          res.add(0);
        }
        res.set(curr, carry);
      }

      indent++;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = res.size() - 1; i >= 0; i--) {
      // ignore leading zeros
      int d = res.get(i);
      if (sb.length() == 0 && d == 0) {
        continue;
      }
      sb.append(d);
    }

    return sb.length() == 0 ? "0" : sb.toString();
  }
}

// https://leetcode.com/problems/next-greater-element-iii/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htyleo on 7/3/17.
 */
public class NextGreaterElementIII {

  public int nextGreaterElement(int n) {
    // find the X, ... X < Y1 >= Y2 >= ... >= Yn
    // swap X with Yi, where Yi is the smallest digit greater than X (if tie, choose the more significant number)
    // result ... Yi Yn Y(n-1) ... Y(i+1) X Y(i-1) ... Y1

    // 			1 2 4 4 3 3 2 2
    // =>
    // index	  6 5 4 3 2 1 0
    // 			  2 4 4 3 3 2 2
    //            3 4 4 3 2 2 2
    //			1 3 2 2 2 3 4 4
    List<Integer> digits = new ArrayList<>();
    boolean hasNGE = false;
    while (n != 0) {
      int d = n % 10;
      n /= 10;
      digits.add(d);
      if (digits.size() > 1 && d < digits.get(digits.size() - 2)) {
        hasNGE = true;
        break;
      }
    }

    if (!hasNGE) {
      return -1;
    }

    for (int i = 0; i < digits.size() - 1; i++) {
      if (digits.get(i) > digits.get(digits.size() - 1)) {
        int tmp = digits.get(i);
        digits.set(i, digits.get(digits.size() - 1));
        digits.set(digits.size() - 1, tmp);
        break;
      }
    }

    long res = n * 10 + digits.get(digits.size() - 1);
    for (int i = 0; i < digits.size() - 1; i++) {
      res = res * 10 + digits.get(i);
    }

    return res <= Integer.MAX_VALUE ? (int) res : -1;
  }
}

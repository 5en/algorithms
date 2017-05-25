// https://leetcode.com/problems/complex-number-multiplication/

package com.htyleo.algorithms;

/**
 * Created by htyleo on 5/25/17.
 */
public class ComplexNumberMultiplication {

  public String complexNumberMultiply(String a, String b) {
    String[] parts1 = a.split("\\+");
    int a1 = Integer.parseInt(parts1[0]);
    int b1 = Integer.parseInt(parts1[1].substring(0, parts1[1].length() - 1));
    String[] parts2 = b.split("\\+");
    int a2 = Integer.parseInt(parts2[0]);
    int b2 = Integer.parseInt(parts2[1].substring(0, parts2[1].length() - 1));

    return (a1 * a2 - b1 * b2) + "+" + (a1 * b2 + a2 * b1) + "i";
  }

}

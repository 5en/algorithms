// https://leetcode.com/problems/fraction-addition-and-subtraction/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/8/17.
 */
public class FractionAdditionAndSubtraction {

  public String fractionAddition(String expression) {
    long[] res = new long[]{0, 1};
    for (int start = 0; start < expression.length(); ) {
      long[] frac = parseFrac(expression, start);
      res = addFrac(res, new long[]{frac[0], frac[1]});
      start = (int) frac[2];
    }

    return res[0] + "/" + res[1];
  }

  // return [numerator, denominator, newStart]
  private long[] parseFrac(String s, int start) {
    // ignore the first negative operator
    int end = start + 1;
    for (; end < s.length(); end++) {
      char ch = s.charAt(end);
      if (ch == '+' || ch == '-') {
        break;
      }
    }

    String frac = s.substring(start, end);
    String[] parts = frac.split("/");
    return new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1]), end};
  }

  // leastCommonMultiple(a, b) = a * b / greatestCommonDivisor(a, b)
  private long[] addFrac(long[] frac1, long[] frac2) {
    long num1 = frac1[0];
    long denom1 = frac1[1];
    long num2 = frac2[0];
    long denom2 = frac2[1];

    long denomLcm = lcm(denom1, denom2);
    long num = num1 * (denomLcm / denom1) + num2 * (denomLcm / denom2);
    long resGcd = gcd(Math.abs(num), denomLcm);

    return new long[]{num / resGcd, denomLcm / resGcd};
  }

  private long lcm(long a, long b) {
    return a * b / gcd(a, b);
  }

  private long gcd(long a, long b) {
    while (a != 0 && b != 0) {
      if (a >= b) {
        a = a % b;
      } else {
        b = b % a;
      }
    }

    return a == 0 ? b : a;
  }

}

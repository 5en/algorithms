// https://leetcode.com/problems/additive-number/

package com.htyleo.algorithms;

import java.math.BigInteger;

public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        int N = num.length();
        // num1 = num[0, len1)
        for (int len1 = 1; len1 < N; len1++) {
            // ensure num1 does not have leading zeros
            if (len1 > 1 && num.charAt(0) == '0') {
                continue;
            }
            BigInteger num1 = new BigInteger(num.substring(0, len1));

            // num2 = num[len1, len1+len2)
            for (int len2 = 1; len1 + len2 < N; len2++) {
                // ensure num2 does not have leading zeros
                if (len2 > 1 && num.charAt(len1) == '0') {
                    continue;
                }

                BigInteger num2 = new BigInteger(num.substring(len1, len1 + len2));
                if (check(num1, num2, num.substring(len1 + len2))) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean check(BigInteger num1, BigInteger num2, String s) {
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            // cannot have leading zeros
            if (i > start && s.charAt(start) == '0') {
                return false;
            }

            BigInteger numCurr = new BigInteger(s.substring(start, i + 1));
            if (numCurr.compareTo(num1.add(num2)) > 0) {
                return false;
            } else if (numCurr.equals(num1.add(num2))) {
                num1 = num2;
                num2 = numCurr;
                start = i + 1;
            }
        }

        return start == s.length();
    }
}

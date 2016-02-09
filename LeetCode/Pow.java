// https://leetcode.com/problems/powx-n/

package com.htyleo.algorithms;

public class Pow {
    public double myPow(double x, int n) {
        long absN = n >= 0 ? n : -(long) n; // in case of Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE
        double result = 1;

        while (absN != 0) {
            if ((absN & 1) == 1) {
                result *= x;
            }
            x *= x;
            absN = absN >> 1;
        }

        return n >= 0 ? result : (1 / result);
    }

    public double myPow2(double x, int n) {
        long absN = n >= 0 ? n : -(long) n;
        double result = 0;

        if (absN == 0) {
            result = 1;
        } else if (absN == 1) {
            result = x;
        } else {
            double half = myPow(x, (int) (absN / 2));
            result = half * half * (absN % 2 == 0 ? 1 : x);
        }

        return n >= 0 ? result : (1 / result);
    }
}

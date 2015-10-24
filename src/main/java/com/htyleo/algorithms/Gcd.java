package com.htyleo.algorithms;

import java.math.BigInteger;

public class Gcd {
    public static void main(String[] args) {
        System.out.println(gcd(30, 42));
        System.out.println(gcd2(42, 30));
        System.out.println(new BigInteger("30").gcd(new BigInteger("42")));
        System.out.println(new BigInteger("42").gcd(new BigInteger("30")));
    }

    // Euclid's algorithm
    // suppose x >= y > 0
    // k = x / y
    // b = x % y
    // gcd(x, y) = gcd(b, y);
    public static int gcd(int x, int y) {
        while (x != 0 && y != 0) {
            if (x >= y) {
                x = x % y;
            } else {
                y = y % x;
            }
        }

        return (x == 0 ? y : x);
    }

    // suppose x >= y > 0
    // if x is even && y is even, gcd(x,y) = 2*gcd(x/2, y/2)
    // if x is even && y is odd, gcd(x,y) = gcd(x/2, y)
    // if x is odd && y is even, gcd(x,y) = gcd(x, y/2)
    // if x is odd && y is odd, gcd(x,y) = gcd(x-y, y)
    // O(log_2(max(x,y)))
    public static int gcd2(int x, int y) {
        int cd = 1;

        while (x != 0 && y != 0) {
            if (x % 2 == 0 && y % 2 == 0) {
                cd *= 2;
                x = x >> 1;
                y = y >> 1;
            } else if (x % 2 == 0 && y % 2 != 0) {
                x = x >> 1;
            } else if (x % 2 != 0 && y % 2 == 0) {
                y = y >> 1;
            } else {
                if (x >= y) {
                    x = x - y;
                } else {
                    y = y - x;
                }
            }
        }

        return (x == 0 ? y * cd : x * cd);
    }
}

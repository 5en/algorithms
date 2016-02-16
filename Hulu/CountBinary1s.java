package com.htyleo.algorithms;

public class CountBinary1s {
    public static void main(String[] args) {
        System.out.println(countBinary1s(5)); // 7
        System.out.println(countBinary1s(9)); // 15
    }

    public static int countBinary1s(int x) {
        if (x == 0) {
            return 0;
        }

        int result = 0;

        int n = maxTwoPowerNLessEqualX(x);
        result += countOneToTwoPowerN(n);

        // left-most 1s
        result += x - Math.pow(2, n);

        result += countBinary1s(x - (int) Math.pow(2, n));

        return result;
    }

    // count 1 ~ 2^N
    //
    // Example: 1 ~ 2^3
    //  001
    //  010
    //  011
    //  100
    //  101
    //  110
    //  111
    // 1000
    private static int countOneToTwoPowerN(int n) {
        return (int) (1 + n * Math.pow(2, n) / 2);
    }

    // find max n, 2^n <= x
    private static int maxTwoPowerNLessEqualX(int x) {
        return (int) Math.floor(Math.log(x) / Math.log(2));
    }
}

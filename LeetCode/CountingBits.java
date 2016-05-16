// https://leetcode.com/problems/counting-bits/

package com.htyleo.algorithms;

public class CountingBits {
    public int[] countBits(int num) {
        // 0: 0000
        //
        // 1: 0001
        // 2: 0010
        // 3: 0011
        //
        // 4: 0100
        // 5: 0101
        // 6: 0110
        // 7: 0111
        //
        // count[num] = 1 + count[num - max{2^n <= num}]

        int[] count = new int[num + 1];
        for (int i = 1, twoPower = 1; i <= num; i++) {
            if (i == twoPower << 1) {
                twoPower <<= 1;
            }
            count[i] = 1 + count[i - twoPower];
        }

        return count;
    }
}

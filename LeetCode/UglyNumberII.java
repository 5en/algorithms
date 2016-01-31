// https://leetcode.com/problems/ugly-number-ii/

package com.htyleo.algorithms;

public class UglyNumberII {
    // O(N) time, O(N) space
    //
    // Explanation: https://leetcode.com/discuss/58186/elegant-c-solution-o-n-space-time-with-detailed-explanation
    public int nthUglyNumber(int n) {
        int[] primes = { 2, 3, 5 };
        // candidates: primes[0]*ugly[index[0]], primes[1]*ugly[index[1]], primes[2]*ugly[index[2]]
        int[] index = new int[primes.length];
        int[] ugly = new int[n];
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            int next = Integer.MAX_VALUE;
            for (int p = 0; p < primes.length; p++) {
                next = Math.min(next, primes[p] * ugly[index[p]]);
            }
            ugly[i] = next;

            for (int p = 0; p < primes.length; p++) {
                if (next == primes[p] * ugly[index[p]]) {
                    index[p]++;
                }
            }
        }

        return ugly[n - 1];
    }
}

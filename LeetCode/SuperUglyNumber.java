// https://leetcode.com/problems/super-ugly-number/

package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SuperUglyNumber {
    // O(NlogK) time, O(max{N, K}) space
    public int nthSuperUglyNumber(int n, int[] primes) {
        int K = primes.length;
        PriorityQueue<Candidate> minHeap = new PriorityQueue<Candidate>();
        for (int k = 0; k < K; k++) {
            minHeap.add(new Candidate(primes[k]));
        }

        int[] ugly = new int[n];
        ugly[0] = 1;
        for (int i = 0; i < n; i++) {
            ugly[i] = minHeap.peek().val;

            while (ugly[i] == minHeap.peek().val) {
                Candidate c = minHeap.remove();
                c.val = c.prime * ugly[c.index++];
                minHeap.add(c);
            }
        }

        return ugly[n - 1];
    }

    private static class Candidate implements Comparable<Candidate> {
        int prime;
        int index = 0; // val = primes[k] * ugly[index] if ugly[index] == val
        int val   = 1; // candidate

        public Candidate(int prime) {
            this.prime = prime;
        }

        @Override
        public int compareTo(Candidate c) {
            return this.val < c.val ? -1 : (this.val == c.val ? 0 : 1);
        }
    }

    // O(NK) time, O(max{N, K}) space
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int K = primes.length;
        int[] index = new int[K]; // candidates: val[k]
        int[] val = new int[K];
        Arrays.fill(val, 1);
        int[] ugly = new int[n];

        int next = 1;
        for (int i = 0; i < n; i++) {
            ugly[i] = next;

            next = Integer.MAX_VALUE;
            for (int k = 0; k < K; k++) {
                // skip duplicate and avoid extra multiplication
                if (ugly[i] == val[k]) {
                    val[k] = primes[k] * ugly[index[k]++];
                }
                next = Math.min(next, val[k]);
            }
        }

        return ugly[n - 1];
    }

    // O(NK) time, O(max{N, K}) space
    public int nthSuperUglyNumber3(int n, int[] primes) {
        int K = primes.length;
        int[] index = new int[K]; // candidates: primes[k] * ugly[index[k]]
        int[] ugly = new int[n];
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            int next = Integer.MAX_VALUE;
            for (int k = 0; k < K; k++) {
                next = Math.min(next, primes[k] * ugly[index[k]]);
            }
            ugly[i] = next;

            for (int k = 0; k < K; k++) {
                if (next == primes[k] * ugly[index[k]]) {
                    index[k]++;
                }
            }
        }

        return ugly[n - 1];
    }
}

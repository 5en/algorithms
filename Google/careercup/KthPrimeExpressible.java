// Given a prime set, we call "prime expressible" if a number can be factorized only using given prime numbers.
// Find n-th big expressible number.
//        E.g., prime set = {2, 3}
//        expressible number = {2,3,4,6,8,9,12...}

package com.htyleo.algorithms;

import java.util.PriorityQueue;

public class KthPrimeExpressible {
    public static void main(String[] args) {
        System.out.println(primeExpressible(new int[] { 2, 3 }, 1));
        System.out.println(primeExpressible(new int[] { 2, 3 }, 2));
        System.out.println(primeExpressible(new int[] { 2, 3 }, 3));
        System.out.println(primeExpressible(new int[] { 2, 3 }, 4));
        System.out.println(primeExpressible(new int[] { 2, 3 }, 5));
        System.out.println(primeExpressible(new int[] { 2, 3 }, 6));
        System.out.println(primeExpressible(new int[] { 2, 3 }, 7));
    }

    // O(NlogN) + O(K * log(KN))
    public static int primeExpressible(int[] primes, int K) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(1);
        for (int prime : primes) {
            minHeap.add(prime);
        }

        int result = 0;
        for (int i = 0; i < K; i++) {
            result = minHeap.remove();
            while (!minHeap.isEmpty() && result == minHeap.peek()) {
                minHeap.remove();
            }

            for (int prime : primes) {
                minHeap.add(result * prime);
            }
        }

        return result;
    }
}

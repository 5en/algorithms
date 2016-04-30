// Find the n-th smallest multiple given a set of numbers.
// For example, set = {4, 6}, n = 6: The sequence is: 4, 6, 8, 12, 16, 18, etc... Answer is 18.

package com.htyleo.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NthSmallestMultiple {

    public static void main(String[] args) {
        System.out.println(nthSmallestMultiple(new int[] { 4, 6 }, 6));
    }

    // O(N*logK) time
    public static int nthSmallestMultiple(int[] factors, int N) {
        PriorityQueue<Num> minHeap = new PriorityQueue<Num>(factors.length, new Comparator<Num>() {
            @Override
            public int compare(Num n1, Num n2) {
                int val1 = n1.factor * n1.multiple;
                int val2 = n2.factor * n2.multiple;
                return val1 < val2 ? -1 : (val1 > val2 ? 1 : 0);
            }
        });

        for (int factor : factors) {
            minHeap.add(new Num(factor, 1));
        }

        int val = 0;
        for (int i = 0; i < N; i++) {
            Num num = minHeap.remove();
            minHeap.add(new Num(num.factor, num.multiple + 1));
            val = num.factor * num.multiple;
            while (true) {
                Num next = minHeap.peek();
                if (next.factor * next.multiple == val) {
                    next = minHeap.remove();
                    minHeap.add(new Num(next.factor, next.multiple + 1));
                } else {
                    break;
                }
            }
        }

        return val;
    }

    private static class Num {
        public final int factor;
        public final int multiple;

        public Num(int factor, int multiple) {
            this.factor = factor;
            this.multiple = multiple;
        }
    }
}

// Given an array of integers, return the largest product of any two integers.
// Follow up: return the largest product of any K integers

package com.htyleo.algorithms;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaxProductOfKNums {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[] { -1, -2, -3, -4 }, 3));
        System.out.println(maxProduct(new int[] { -1, -2, -3, -4, 0 }, 3));
        System.out.println(maxProduct(new int[] { -1, -2, -3, 0, 1, 2 }, 3));
        System.out.println(maxProduct(new int[] { -1, 1, 2 }, 3));
        System.out.println(maxProduct(new int[] { -1, 1, 2, 0 }, 3));
    }

    // O(N*logK)
    public static int maxProduct(int[] nums, int K) {
        // min heap
        PriorityQueue<Integer> maxKPos = new PriorityQueue<Integer>(K, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 <= i2 ? -1 : 1;
            }
        });

        // max heap
        PriorityQueue<Integer> minKNeg = new PriorityQueue<Integer>(K, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 >= i2 ? -1 : 1;
            }
        });

        // min heap
        PriorityQueue<Integer> maxKNeg = new PriorityQueue<Integer>(K, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 <= i2 ? -1 : 1;
            }
        });

        int zeroCount = 0;

        for (int num : nums) {
            if (num > 0) {
                if (maxKPos.size() < K) {
                    maxKPos.add(num);
                } else if (num > maxKPos.peek()) {
                    maxKPos.remove();
                    maxKPos.add(num);
                }
            } else if (num < 0) {
                if (minKNeg.size() < K) {
                    minKNeg.add(num);
                } else if (num < minKNeg.peek()) {
                    minKNeg.remove();
                    minKNeg.add(num);
                }

                if (maxKNeg.size() < K) {
                    maxKNeg.add(num);
                } else if (num > maxKNeg.peek()) {
                    maxKNeg.remove();
                    maxKNeg.add(num);
                }
            } else {
                zeroCount++;
            }
        }

        // no positive number

        if (maxKPos.isEmpty()) {
            if (maxKNeg.size() < K) {
                // less than K negative numbers, must contain 0
                return 0;
            } else if (K % 2 == 0) {
                // K is even
                // result = product of min K negatives
                int result = 1;
                for (int i = 0; i < K; i++) {
                    result *= minKNeg.remove();
                }
                return result;
            } else {
                // K is odd
                // if contains 0, return 0
                // else return product of max K negatives
                if (zeroCount > 0) {
                    return 0;
                }

                int result = 1;
                for (int i = 0; i < K; i++) {
                    result *= maxKNeg.remove();
                }
                return result;
            }
        }

        // contains positive number

        // num of positives + num of negatives < K, must contain 0
        if (maxKPos.size() + minKNeg.size() < K) {
            return 0;
        }

        Deque<Integer> posStack = new LinkedList<Integer>();
        while (!maxKPos.isEmpty()) {
            posStack.push(maxKPos.remove());
        }
        Deque<Integer> negStack = new LinkedList<Integer>();
        while (!minKNeg.isEmpty()) {
            negStack.push(minKNeg.remove());
        }

        int result = 1;
        while (K >= 2) {
            int posProd = -1;
            if (posStack.size() >= 2) {
                int maxPos = posStack.pop();
                posProd = maxPos * posStack.peek();
                posStack.push(maxPos);
            }

            int negProd = -1;
            if (negStack.size() >= 2) {
                int minNeg = negStack.pop();
                negProd = minNeg * negStack.peek();
                negStack.push(minNeg);
            }

            if (posProd == -1 && negProd == -1) {
                // this only happens when there are only 1 element in each heap
                result *= posStack.pop();
                result *= negStack.pop();
            } else if (posProd >= negProd) {
                result *= posProd;
                posStack.remove();
                posStack.remove();
            } else {
                result *= negProd;
                negStack.remove();
                negStack.remove();
            }

            K -= 2;
        }

        if (K == 1) {
            if (!posStack.isEmpty()) {
                result *= posStack.remove();
            } else {
                result *= negStack.remove();
            }
        }

        if (result < 0 && zeroCount > 0) {
            result = 0;
        }

        return result;
    }
}

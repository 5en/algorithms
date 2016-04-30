package com.htyleo.algorithms;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MathSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sum(new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 })));
        System.out.println(Arrays.toString(sum(new int[] { 9, 9, 2 }, new int[] { 0, 1, 3 })));
    }

    public static int[] sum(int[] a1, int[] a2) {
        Deque<Integer> stack = new ArrayDeque<Integer>();

        int carry = 0;
        for (int i = a1.length - 1, j = a2.length - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += (i >= 0 ? a1[i] : 0);
            sum += (j >= 0 ? a2[j] : 0);
            stack.push(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            stack.push(carry);
        }

        int[] a = new int[stack.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = stack.pop();
        }

        return a;
    }
}

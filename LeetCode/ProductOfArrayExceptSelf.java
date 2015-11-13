// https://leetcode.com/problems/product-of-array-except-self/

package com.htyleo.algorithms;

public class ProductOfArrayExceptSelf {
    // O(N) time, O(1) extra space
    public static int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] output = new int[N];
        // initially, output[i] = nums[0] * ... * nums[i-1]
        output[0] = 1;
        for (int i = 1; i < N; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        // when processing output[i], tmp == nums[N-1] * ... * nums[i+1]
        int tmp = 1;
        for (int i = N - 1; i >= 0; i--) {
            output[i] *= tmp;
            tmp *= nums[i];
        }

        return output;
    }

    // O(N) time, O(N) extra space
    public static int[] productExceptSelf2(int[] nums) {
        int N = nums.length;
        int[] head = new int[N]; // head[i] = nums[0] * ... * nums[i]
        head[0] = nums[0];
        for (int i = 1; i < N; i++) {
            head[i] = head[i - 1] * nums[i];
        }
        int[] tail = new int[N]; //tail[i] = nums[i] * ... * nums[N-1]
        tail[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            tail[i] = tail[i + 1] * nums[i];
        }

        int[] output = new int[N];
        output[0] = tail[1];
        output[N - 1] = head[N - 2];
        for (int i = 1; i < N - 1; i++) {
            output[i] = head[i - 1] * tail[i + 1];
        }

        return output;
    }
}

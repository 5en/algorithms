package com.htyleo.algorithms;

public class IncreasingTripletSubsequence {
    // O(N) time, O(1) space
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE; // arr[i] = first
        int second = Integer.MAX_VALUE; // arr[i] < arr[j] = second
        for (int num : nums) {
            int newFirst = Math.min(first, num);
            int newSecond = num > first ? Math.min(second, num) : second;
            if (num > second) {
                return true;
            }

            first = newFirst;
            second = newSecond;
        }

        return false;
    }
}

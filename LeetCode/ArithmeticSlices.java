// https://leetcode.com/problems/arithmetic-slices/

package com.htyleo.algorithms;

public class ArithmeticSlices {

    public int numberOfArithmeticSlices(int[] A) {
        int result = 0;

        int left = 0;
        int prevDiff = 0;
        int currDiff = 0;
        for (int i = 1; i < A.length; i++) {
            if (i == 1) {
                prevDiff = A[i] - A[i - 1];
                continue;
            }

            currDiff = A[i] - A[i - 1];
            if (prevDiff != currDiff) {
                result += calCount(left, i - 1);
                prevDiff = currDiff;
                left = i - 1;
            }
        }

        if (prevDiff == currDiff) {
            result += calCount(left, A.length - 1);
        }

        return result;
    }

    private int calCount(int left, int right) {
        int len = right - left + 1;
        if (len < 3) {
            return 0;
        }

        // 1 2 3 4 5 6
        // len = 3: count = 4
        // len = 4: count = 3
        // len = 5: count = 2
        // len = 6: count = 1
        return (1 + (len - 2)) * (len - 3 + 1) / 2;
    }

}

// https://leetcode.com/problems/valid-perfect-square/

package com.htyleo.algorithms;

public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        if (num <= 16) {
            return num == 1 || num == 4 || num == 9 || num == 16;
        }

        // for num > 16, 4 < sqrt(num) < num/4
        long left = 5;
        long right = num / 4 + 1;
        while (left <= right) {
            long mid = (left + right) / 2;
            long square = mid * mid;
            if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }

}

// https://leetcode.com/problems/sqrtx/

package com.htyleo.algorithms;

public class SqrtX {
    // i * i <= x
    // (i+1) * (i+1) > x
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        int low = 1;
        int high = x;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid <= x / mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low - 1;
    }
}

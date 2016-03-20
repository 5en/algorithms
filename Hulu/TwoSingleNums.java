// all numbers appears twice, except for two which appears only once

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSingleNums {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findNum(new int[] { 1, 1, 2, 3 })));
        System.out.println(Arrays.toString(findNum(new int[] { 1, 1, 5, 5, 8, 2, 8, 3 })));
    }

    public static int[] findNum(int[] nums) {
        int xorResult = 0;
        for (int num : nums) {
            xorResult ^= num;
        }

        int testNum = 1;
        for (int i = 0; i <= 31; i++) {
            if ((xorResult & testNum) == 1) {
                break;
            }
            testNum = testNum << 1;
        }

        // nums in group1 & testNum == 0
        List<Integer> group1 = new ArrayList<Integer>();
        // nums in group2 & testNum == 1
        List<Integer> group2 = new ArrayList<Integer>();
        for (int num : nums) {
            if ((num & testNum) == 0) {
                group1.add(num);
            } else {
                group2.add(num);
            }
        }

        int result1 = 0;
        for (int num : group1) {
            result1 ^= num;
        }
        int result2 = 0;
        for (int num : group2) {
            result2 ^= num;
        }

        return new int[] { result1, result2 };
    }
}

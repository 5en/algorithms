// Check if all integers in the list can be grouped into 5 consecutive number.
// For example [1,2,2,3,3,4,4,5,5,6] should return True because it can be grouped into (1,2,3,4,5)(2,3,4,5,6) with no other elements left.

package com.htyleo.algorithms;

import java.util.LinkedHashMap;
import java.util.Map;

public class GroupConsecutiveNumbers {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 2, 3, 3, 4, 4, 5, 5, 6 };
        System.out.println(fiveConsecutive(nums, 5));
        System.out.println(fiveConsecutive(nums, 4));
    }

    public static boolean fiveConsecutive(int[] nums, int K) {
        Map<Integer, Integer> numCount = new LinkedHashMap<Integer, Integer>();
        for (int num : nums) {
            numCount.put(num, numCount.containsKey(num) ? numCount.get(num) + 1 : 1);
        }

        while (!numCount.isEmpty()) {
            Integer n1 = numCount.keySet().iterator().next();
            for (int num = n1; num <= n1 + K - 1; num++) {
                if (!numCount.containsKey(num)) {
                    return false;
                } else if (numCount.get(num) == 1) {
                    numCount.remove(num);
                } else {
                    numCount.put(num, numCount.get(num) - 1);
                }
            }
        }

        return true;
    }
}

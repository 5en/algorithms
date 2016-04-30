// Given a list of integers, find the highest value obtainable by concatenating these together.
//        For example, given 9, 918, 917 - The answer is 9918917.
//        But given 1,112,113 - The answer is 1131121

package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class MaxNumCombination {
    public static void main(String[] args) {
        System.out.println(maxNum(new String[] { "9", "918", "917" }));
        System.out.println(maxNum(new String[] { "1", "112", "113" }));
    }

    public static String maxNum(String[] nums) {
        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int i1 = 0;
                int i2 = 0;
                for (; i1 < s1.length() && i2 < s2.length(); i1++, i2++) {
                    if (s1.charAt(i1) > s2.charAt(i2)) {
                        return -1;
                    } else if (s1.charAt(i1) < s2.charAt(i2)) {
                        return 1;
                    }
                }

                for (; i1 < s1.length(); i1++) {
                    if (s1.charAt(i1) > s1.charAt(0)) {
                        return -1;
                    } else if (s1.charAt(i1) < s1.charAt(0)) {
                        return 1;
                    }
                }

                for (; i2 < s2.length(); i2++) {
                    if (s2.charAt(i2) > s2.charAt(0)) {
                        return 1;
                    } else if (s2.charAt(i2) < s2.charAt(0)) {
                        return -1;
                    }
                }

                return 0;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String num : nums) {
            sb.append(num);
        }

        return sb.toString();
    }
}

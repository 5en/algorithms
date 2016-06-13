// https://leetcode.com/problems/different-ways-to-add-parentheses/

package com.htyleo.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        String[] numStrs = input.split("[\\+\\-\\*]");
        List<Integer> nums = new ArrayList<Integer>(numStrs.length);
        for (String numStr : numStrs) {
            nums.add(Integer.valueOf(numStr));
        }
        String[] opStrs = input.split("\\d+");
        List<Character> ops = new ArrayList<Character>(opStrs.length);
        for (String opStr : opStrs) {
            if (opStr.length() > 0) {
                ops.add(opStr.charAt(0));
            }
        }

        return diffWaysToComputeSR(nums, 0, nums.size() - 1, ops, 0, ops.size() - 1);
    }

    private List<Integer> diffWaysToComputeSR(List<Integer> nums, int nl, int nh,
                                              List<Character> ops, int ol, int oh) {
        List<Integer> result = new ArrayList<Integer>();

        if (nl == nh) {
            result.add(nums.get(nl));
            return result;
        }

        for (int i = ol; i <= oh; i++) {
            // operator ops[i]
            List<Integer> left = diffWaysToComputeSR(nums, nl, i, ops, ol, i - 1);
            List<Integer> right = diffWaysToComputeSR(nums, i + 1, nh, ops, i + 1, oh);
            for (int num1 : left) {
                for (int num2 : right) {
                    switch (ops.get(i)) {
                        case '-':
                            result.add(num1 - num2);
                            break;
                        case '+':
                            result.add(num1 + num2);
                            break;
                        case '*':
                            result.add(num1 * num2);
                            break;
                    }
                }
            }
        }

        return result;
    }
}

// https://leetcode.com/problems/different-ways-to-add-parentheses/

package com.htyleo.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {
    // DP
    public List<Integer> diffWaysToCompute(String input) {
        // nums[i], ops[i], nums[i+1], ....
        String[] numStrs = input.split("[\\+\\-\\*]");
        int[] nums = new int[numStrs.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(numStrs[i]);
        }
        String[] opStrs = input.split("\\d+");
        List<Character> ops = new ArrayList<Character>(opStrs.length);
        for (String opStr : opStrs) {
            if (opStr.length() > 0) {
                ops.add(opStr.charAt(0));
            }
        }

        // mem[i][j].r contains all possible results from nums[i...j]
        int N = nums.length; // # of integers
        Mem[][] mem = new Mem[N][N];
        for (int i = 0; i < N; i++) {
            mem[i][i] = new Mem();
            mem[i][i].r.add(nums[i]);
        }

        for (int step = 1; step <= N - 1; step++) {
            for (int i = 0; i < N; i++) {
                int j = i + step;
                if (j >= N) {
                    continue;
                }

                // compute mem[i][j].r
                mem[i][j] = new Mem();

                // for each operator ops[k]
                for (int k = i; k < j; k++) {
                    List<Integer> lefts = mem[i][k].r;
                    List<Integer> rights = mem[k + 1][j].r;
                    for (int left : lefts) {
                        for (int right : rights) {
                            switch (ops.get(k)) {
                                case '-':
                                    mem[i][j].r.add(left - right);
                                    break;
                                case '+':
                                    mem[i][j].r.add(left + right);
                                    break;
                                case '*':
                                    mem[i][j].r.add(left * right);
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return mem[0][N - 1].r;
    }

    private static class Mem {
        public final List<Integer> r = new ArrayList<Integer>();
    }

    // Divide and Conquer
    public List<Integer> diffWaysToCompute2(String input) {
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

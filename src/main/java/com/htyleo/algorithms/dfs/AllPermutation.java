package com.htyleo.algorithms.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllPermutation {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };

        System.out.println("perm");
        for (List<Integer> perm : perm(nums)) {
            System.out.println(perm);
        }

        System.out.println("perm with memo");
        for (List<Integer> perm : permMemo(nums)) {
            System.out.println(perm);
        }

        int[] nums2 = { 1, 2, 2 };
        System.out.println("perm with duplicates");
        for (List<Integer> perm : permDuplicate(nums2)) {
            System.out.println(perm);
        }
    }

    public static List<List<Integer>> perm(int[] nums) {
        List<List<Integer>> perms = new ArrayList<List<Integer>>();
        permSR(perms, nums, 0);

        return perms;
    }

    // nums[start, nums.size()-1]
    private static void permSR(List<List<Integer>> perms, int[] nums, int start) {
        if (start == nums.length - 1) {
            List<Integer> perm = new ArrayList<Integer>();
            for (int num : nums) {
                perm.add(num);
            }
            perms.add(perm);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // swap nums[start] and nums[i]
            int tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;

            permSR(perms, nums, start + 1);

            // backtrack
            tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
        }
    }

    // with memo
    public static List<List<Integer>> permMemo(int[] nums) {
        // map items to set of its permutations
        Map<Set<Integer>, List<List<Integer>>> memo = new HashMap<Set<Integer>, List<List<Integer>>>();

        return permMemoSR(memo, nums, 0);
    }

    // nums[start, nums.size()-1]
    private static List<List<Integer>> permMemoSR(Map<Set<Integer>, List<List<Integer>>> memo,
                                                  int[] nums, int start) {
        Set<Integer> key = new HashSet<Integer>();
        for (int i = start; i < nums.length; i++) {
            key.add(nums[i]);
        }

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (start == nums.length - 1) {
            List<Integer> perm = new ArrayList<Integer>(key);
            List<List<Integer>> perms = new ArrayList<List<Integer>>();
            perms.add(perm);
            memo.put(key, perms);
            return perms;
        }

        List<List<Integer>> perms = new ArrayList<List<Integer>>();
        for (int i = start; i < nums.length; i++) {
            // swap nums[start] and nums[i]
            int tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;

            for (List<Integer> subPerm : permMemoSR(memo, nums, start + 1)) {
                List<Integer> perm = new ArrayList<Integer>();
                perm.add(nums[start]);
                perm.addAll(subPerm);
                perms.add(perm);
            }

            // backtrack
            tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
        }
        memo.put(key, perms);

        return perms;
    }

    // handle duplicates
    public static List<List<Integer>> permDuplicate(int[] nums) {
        List<List<Integer>> perms = new ArrayList<List<Integer>>();
        permuteUniqueSR(perms, nums, 0);
        return perms;
    }

    private static void permuteUniqueSR(List<List<Integer>> perms, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> perm = new ArrayList<Integer>();
            for (int num : nums) {
                perm.add(num);
            }
            perms.add(perm);
            return;
        }

        Set<Integer> swaps = new HashSet<Integer>();

        for (int i = start; i < nums.length; i++) {
            if (swaps.contains(nums[i])) {
                continue;
            }

            // swap nums[start] and nums[i]
            int tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;

            swaps.add(nums[start]);

            permuteUniqueSR(perms, nums, start + 1);

            // backtrack
            tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
        }
    }

}

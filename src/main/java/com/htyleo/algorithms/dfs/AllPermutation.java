package com.htyleo.algorithms.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllPermutation {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3 }));

        System.out.println("perm");
        for (List<Integer> perm : perm(a)) {
            System.out.println(perm);
        }

        System.out.println("perm with memo");
        for (List<Integer> perm : permMemo(a)) {
            System.out.println(perm);
        }
    }

    public static Set<List<Integer>> perm(List<Integer> a) {
        Set<List<Integer>> permSet = new HashSet<List<Integer>>();
        permSub(a, 0, permSet);

        return permSet;
    }

    // a[start, a.size()-1]
    private static void permSub(List<Integer> a, int start, Set<List<Integer>> permSet) {
        if (start == a.size() - 1) {
            permSet.add(new ArrayList<Integer>(a));
            return;
        }

        for (int i = start; i < a.size(); i++) {
            // swap a[start] and a[i]
            int tmp = a.get(start);
            a.set(start, a.get(i));
            a.set(i, tmp);

            permSub(a, start + 1, permSet);

            // backtrack
            tmp = a.get(start);
            a.set(start, a.get(i));
            a.set(i, tmp);
        }
    }

    // with memo
    public static Set<List<Integer>> permMemo(List<Integer> a) {
        // map items to set of its permutations
        Map<Set<Integer>, Set<List<Integer>>> memo = new HashMap<Set<Integer>, Set<List<Integer>>>();

        return permMemoSR(a, 0, memo);
    }

    // a[start, a.size()-1]
    private static Set<List<Integer>> permMemoSR(List<Integer> a, int start,
                                                 Map<Set<Integer>, Set<List<Integer>>> memo) {
        Set<Integer> key = new HashSet<Integer>(a.subList(start, a.size()));

        if (memo.containsKey(key)) {
            // found in memo
            return memo.get(key);
        }

        if (start == a.size() - 1) {
            List<Integer> perm = new ArrayList<Integer>(key);
            Set<List<Integer>> permSet = new HashSet<List<Integer>>();
            permSet.add(perm);
            memo.put(key, permSet);

            return memo.get(key);
        }

        Set<List<Integer>> permSet = new HashSet<List<Integer>>();
        for (int i = start; i < a.size(); i++) {
            // swap a[start] and a[i]
            int tmp = a.get(start);
            a.set(start, a.get(i));
            a.set(i, tmp);

            for (List<Integer> subPerm : permMemoSR(a, start + 1, memo)) {
                List<Integer> perm = new ArrayList<Integer>(a.size() - start);
                perm.add(a.get(start));
                perm.addAll(subPerm);
                permSet.add(perm);
            }

            // backtrack
            tmp = a.get(start);
            a.set(start, a.get(i));
            a.set(i, tmp);
        }
        memo.put(key, permSet);

        return permSet;
    }
}

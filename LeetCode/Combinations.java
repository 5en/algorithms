// https://leetcode.com/problems/combinations/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        return combineSR(1, n, k);
    }

    // choose k from start ... n
    private List<List<Integer>> combineSR(int start, int n, int k) {
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        if (n - start + 1 == k) {
            List<Integer> comb = new LinkedList<Integer>();
            for (int i = start; i <= n; i++) {
                comb.add(i);
            }
            combs.add(comb);
            return combs;
        }
        if (k == 1) {
            for (int i = start; i <= n; i++) {
                List<Integer> comb = new LinkedList<Integer>();
                comb.add(i);
                combs.add(comb);
            }
            return combs;
        }

        // do not pick "start"
        combs.addAll(combineSR(start + 1, n, k));
        // pick "start"
        for (List<Integer> comb : combineSR(start + 1, n, k - 1)) {
            comb.add(0, start);
            combs.add(comb);
        }

        return combs;
    }
}

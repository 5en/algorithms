// https://leetcode.com/problems/minimum-height-trees/
// https://leetcode.com/discuss/71763/share-some-thoughts

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }

        List<Set<Integer>> adjs = new ArrayList<Set<Integer>>(n);
        for (int i = 0; i < n; i++) {
            adjs.add(new HashSet<Integer>());
        }
        for (int[] edge : edges) {
            adjs.get(edge[0]).add(edge[1]);
            adjs.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (adjs.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        while (n > 2) {
            List<Integer> newLeaves = new ArrayList<Integer>();
            for (int node : leaves) {
                int neighbor = adjs.get(node).iterator().next();
                adjs.get(neighbor).remove(node);
                if (adjs.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }

            n -= leaves.size();

            leaves = newLeaves;
        }

        return leaves;
    }
}

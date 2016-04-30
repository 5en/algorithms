// Given:
// p[0, 1, ..., N-1] contains 0, 1, ..., N-1
// a[K][N]
// a[0][N] contains 0, 1, ..., N-1
// a[k][i] = p[a[k-1][i]]
//
// Calculate a[K][]

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayIndex {
    public static void main(String[] args) {
        int[] p = { 3, 0, 2, 1 };
        int[] a0 = { 0, 1, 2, 3 };
        System.out.println(Arrays.toString(calAk(p, a0, 0))); // 0, 1, 2, 3
        System.out.println(Arrays.toString(calAk(p, a0, 1))); // 3, 0, 2, 1
        System.out.println(Arrays.toString(calAk(p, a0, 2))); // 1, 3, 2, 0
        System.out.println(Arrays.toString(calAk(p, a0, 3))); // 0, 1, 2, 3
    }

    // O(N) time, O(N) space
    public static int[] calAk(int[] p, int[] a0, int K) {
        if (K == 0) {
            return a0;
        }

        // preprocess p
        // map: p[i] -> (chainIndex, Chain)
        Set<Integer> processed = new HashSet<Integer>(); // set of processed indices
        Map<Integer, Info> infoMap = new HashMap<Integer, Info>();
        for (int i = 0; i < p.length; i++) {
            List<Integer> chain = new ArrayList<Integer>();
            for (int curr = i, chainIdx = 0; !processed.contains(curr); chainIdx++) {
                // p[curr] -> chain.get(chainIdx)
                chain.add(p[curr]);
                infoMap.put(p[curr], new Info(chainIdx, chain));

                processed.add(curr);
                curr = p[curr];
            }
        }

        // calculate
        int[] ak = new int[a0.length];
        for (int i = 0; i < a0.length; i++) {
            Info info = infoMap.get(a0[i]);
            int chainSize = info.chain.size();

            // also handles the case that chainSize = 1
            ak[i] = info.chain.get((info.index + K) % chainSize);
        }

        return ak;
    }

    private static class Info {
        int           index;
        List<Integer> chain;

        public Info(int index, List<Integer> chain) {
            this.index = index;
            this.chain = chain;
        }
    }
}

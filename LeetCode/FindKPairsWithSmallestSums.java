// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by htyleo on 6/28/17.
 */
public class FindKPairsWithSmallestSums {

  // heap, O(k*logN) time
  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<int[]> res = new ArrayList<>();

    final int M = nums1.length;
    final int N = nums2.length;
    PriorityQueue<Integer> m = new PriorityQueue<>((x, y) -> {
      int x1 = x / N;
      int x2 = x % N;
      int y1 = y / N;
      int y2 = y % N;
      return nums1[x1] + nums2[x2] <= nums1[y1] + nums2[y2] ? -1 : 1;
    });
    if (0 < nums1.length && 0 < nums2.length) {
      m.add(0);
    }

    Set<Integer> used = new HashSet<>();

    while (res.size() < k && !m.isEmpty()) {
      int x = m.remove();
      int x1 = x / N;
      int x2 = x % N;
      res.add(new int[]{nums1[x1], nums2[x2]});

      if (x1 + 1 < nums1.length) {
        int cand = (x1 + 1) * N + x2;
        if (!used.contains(cand)) {
          m.add(cand);
          used.add(cand);
        }
      }
      if (x2 + 1 < nums2.length) {
        int cand = x1 * N + x2 + 1;
        if (!used.contains(cand)) {
          m.add(cand);
          used.add(cand);
        }
      }
    }

    return res;
  }
}

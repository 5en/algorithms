// https://leetcode.com/problems/4sum-ii/#/description

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by htyleo on 6/12/17.
 */
public class FourSumII {

  public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    // find A[i] + B[j] == C[k] + D[l]
    Map<Integer, Integer> abSumCnt = cntSum(A, B);
    Map<Integer, Integer> cdSumCnt = cntSum(C, D);

    int res = 0;
    for (int abSum : abSumCnt.keySet()) {
      if (cdSumCnt.containsKey(-abSum)) {
        res += abSumCnt.get(abSum) * cdSumCnt.get(-abSum);
      }
    }

    return res;
  }

  // O(N^2) time
  private Map<Integer, Integer> cntSum(int[] A, int[] B) {
    Map<Integer, Integer> res = new HashMap<>();
    for (int a : A) {
      for (int b : B) {
        int sum = a + b;
        res.compute(sum, (k, v) -> v == null ? 1 : v + 1);
      }
    }

    return res;
  }

}

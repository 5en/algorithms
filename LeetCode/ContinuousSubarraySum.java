// https://leetcode.com/problems/continuous-subarray-sum/#/description

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by htyleo on 7/9/17.
 */
public class ContinuousSubarraySum {

  // O(N) time, O(k) space
  public boolean checkSubarraySum(int[] nums, int k) {
    // prefix sum % k -> end index
    Map<Integer, Integer> sum2endIdx = new HashMap<>();
    sum2endIdx.put(0, -1);
    for (int sum = 0, i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (k != 0) {
        sum %= k;
      }

      // find previous sum s such that (sum - s) == 0 && i - sum2endIdx >= 2
      Integer index = sum2endIdx.get(sum);
      if (index == null) {
        sum2endIdx.putIfAbsent(sum, i);
      } else if (i - index >= 2) {
        return true;
      }
    }

    return false;
  }

  // O(N^2) time, O(N) space
  public boolean checkSubarraySum2(int[] nums, int k) {
    int N = nums.length;
    int[] prefixSum = new int[N];
    for (int i = 0; i < N; i++) {
      prefixSum[i] = nums[i] + (i > 0 ? prefixSum[i - 1] : 0);
    }

    for (int i = 0; i < N - 1; i++) {
      for (int j = i + 1; j < N; j++) {
        int sum = prefixSum[j] - (i > 0 ? prefixSum[i - 1] : 0);
        if ((k == 0 && sum == 0) || (k != 0 && sum % k == 0)) {
          return true;
        }
      }
    }

    return false;
  }
}

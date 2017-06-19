// https://leetcode.com/problems/subarray-sum-equals-k/#/description

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by htyleo on 6/19/17.
 */
public class SubarraySumEqualsK {

  // O(N) time, O(N) space
  public int subarraySum(int[] nums, int k) {
    int res = 0;

    Map<Integer, Integer> prefixSumCnt = new HashMap<>();
    prefixSumCnt.put(0, 1);
    int sum = 0;
    for (int num : nums) {
      sum += num;
      // find sum - x == k
      if (prefixSumCnt.containsKey(sum - k)) {
        res += prefixSumCnt.get(sum - k);
      }
      prefixSumCnt.compute(sum, (key, val) -> val == null ? 1 : val + 1);
    }

    return res;
  }

  // O(N^2) time, O(1) space
  public int subarraySum2(int[] nums, int k) {
    int N = nums.length;
    int res = 0;
    for (int i = 0; i < N; i++) {
      int prevSum = 0;
      for (int j = i; j < N; j++) {
        int sum = prevSum + nums[j];
        if (sum == k) {
          res++;
        }
        prevSum = sum;
      }
    }

    return res;
  }

  // O(N^2) time, O(N) space
  public int subarraySum3(int[] nums, int k) {
    int N = nums.length;
    // prefixSum[i] = nums[0] + nums[i] + ... + nums[i]
    int[] prefixSum = new int[N];
    for (int i = 0; i < N; i++) {
      prefixSum[i] = nums[i] + (i == 0 ? 0 : prefixSum[i - 1]);
    }

    // nums[i] + ... + nums[j] = prefixSum[j] - prefixSum[i-1]
    int res = 0;
    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        int sum = prefixSum[j] - (i == 0 ? 0 : prefixSum[i - 1]);
        if (sum == k) {
          res++;
        }
      }
    }

    return res;
  }

}

// https://leetcode.com/problems/largest-divisible-subset/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by htyleo on 6/26/17.
 */
public class LargestDivisibleSubset {

  // DP, O(N^2) time, O(N) space
  public List<Integer> largestDivisibleSubset(int[] nums) {
    // sort(nums)
    //
    // If nums[i] can be put into the set, nums[i] must be divisible by the largest element in the set
    // dp[i]: length of the LDS whose largest num is nums[i]
    // dp[i+1] = max{ 1 + dp[j] | if nums[i+1] % nums[j] == 0, for 0 <= j <= i}
    int N = nums.length;

    Arrays.sort(nums);
    int[] dp = new int[N];
    int[] prev = new int[N];
    int maxLen = 0;
    int maxEnd = -1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }
      if (dp[i] == 0) {
        dp[i] = 1;
        prev[i] = -1;
      }

      // find maxLen
      if (dp[i] > maxLen) {
        maxLen = dp[i];
        maxEnd = i;
      }
    }

    // backward from dp[i]
    List<Integer> res = new ArrayList<>();
    while (maxEnd != -1) {
      res.add(nums[maxEnd]);
      maxEnd = prev[maxEnd];
    }

    return res;
  }
}

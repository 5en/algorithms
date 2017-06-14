// https://leetcode.com/problems/target-sum/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/14/17.
 */
public class TargetSum {

  // DP, O(N*L) time, O(L) space
  public int findTargetSumWays(int[] nums, int S) {
    int N = nums.length;

    // dp[j]: # of ways for nums to the left to have sum == j - 1000
    // dp[j] = dp[j - nums[i+1]] + dp[j + nums[i+1]]
    int[] dp = new int[2001];
    dp[nums[0] + 1000] += 1;
    dp[-nums[0] + 1000] += 1;
    for (int i = 1; i < N; i++) {
      int[] newDp = new int[2001];
      for (int j = 0; j <= 2000; j++) {
        int cnt = 0;
        if (j - nums[i] >= 0) {
          cnt += dp[j - nums[i]];
        }
        if (j + nums[i] <= 2000) {
          cnt += dp[j + nums[i]];
        }
        newDp[j] = cnt;
      }
      dp = newDp;
    }

    return (S >= -1000 && S <= 1000) ? dp[S + 1000] : 0;
  }

  // DP, O(N*L) time, O(N*L) space
  public int findTargetSumWays2(int[] nums, int S) {
    int N = nums.length;

    // dp[i][j]: # of ways for nums[0...i] to have sum == j - 1000
    // dp[i+1][j] = dp[i][j - nums[i+1]] + dp[i][j + nums[i+1]]
    int[][] dp = new int[N][2001];
    dp[0][nums[0] + 1000] += 1;
    dp[0][-nums[0] + 1000] += 1;
    for (int i = 1; i < N; i++) {
      for (int j = 0; j <= 2000; j++) {
        int cnt = 0;
        if (j - nums[i] >= 0) {
          cnt += dp[i - 1][j - nums[i]];
        }
        if (j + nums[i] <= 2000) {
          cnt += dp[i - 1][j + nums[i]];
        }
        dp[i][j] = cnt;
      }
    }

    return (S >= -1000 && S <= 1000) ? dp[N - 1][S + 1000] : 0;
  }
}

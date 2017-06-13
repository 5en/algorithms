// https://leetcode.com/problems/predict-the-winner/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/13/17.
 */
public class PredictTheWinner {

  // O(N^2) time, O(N) space
  public boolean PredictTheWinner(int[] nums) {
    int N = nums.length;
    // dp[i]: max score for first-in-action player in range nums[i...i+len-1]
    // len is incremented in each iteration
    int[] dp = new int[N];
    for (int i = 0; i < N; i++) {
      dp[i] = nums[i];
    }
    for (int len = 2; len <= N; len++) {
      for (int left = 0; left + len - 1 < N; left++) {
        int right = left + len - 1;
        // max(take nums[left], take nums[right])
        // currently
        //      dp[0]...dp[left] are for length len
        //      dp[left+1]... are for length len - 1
        dp[left] = Math.max(nums[left] - dp[left + 1], nums[right] - dp[left]);
      }
    }

    return dp[0] >= 0;
  }

  // O(N^2) time, O(N^2) space
  public boolean PredictTheWinner2(int[] nums) {
    int N = nums.length;
    // dp[i][j]: max score for first-in-action player in range nums[i...j]
    // take: score > 0
    // taken by the other player: score < 0
    int[][] dp = new int[N][N];
    for (int i = 0; i < N; i++) {
      dp[i][i] = nums[i];
    }
    for (int len = 2; len <= N; len++) {
      for (int left = 0; left + len - 1 < N; left++) {
        int right = left + len - 1;
        // max(take nums[left], take nums[right])
        dp[left][right] = Math
            .max(nums[left] - dp[left + 1][right], nums[right] - dp[left][right - 1]);
      }
    }

    return dp[0][N - 1] >= 0;
  }
}

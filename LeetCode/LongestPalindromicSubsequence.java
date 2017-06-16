// https://leetcode.com/problems/longest-palindromic-subsequence/#/description

package com.htyleo.algorithms;

import java.util.Arrays;

/**
 * Created by htyleo on 6/16/17.
 */
public class LongestPalindromicSubsequence {

  // O(N^2) time, O(N) space
  public int longestPalindromeSubseq(String s) {
    int N = s.length();

    // dp[i][j] is LPS for s[i...j]
    // dp[i][j] = dp[i+1][j-1] + 2              , if s[i] == s[j]
    //          = max(dp[i+1][j], dp[i][j-1])   , otherwise
    //
    // keep three levels
    int[] dpLenM2 = null;
    int[] dpLenM1 = new int[N];
    Arrays.fill(dpLenM1, 1);

    for (int len = 2; len <= N; len++) {
      int[] dp = new int[N];
      for (int left = 0; left + len - 1 < N; left++) {
        int right = left + len - 1;
        int res = 2 + (left + 1 <= right - 1 ? dpLenM2[right - 1] : 0);
        if (s.charAt(left) != s.charAt(right)) {
          res = Math.max(dpLenM1[right], dpLenM1[right - 1]);
        }
        dp[right] = res;
      }
      dpLenM2 = dpLenM1;
      dpLenM1 = dp;
    }

    return dpLenM1[N - 1];
  }

  // O(N^2) time, O(N^2) space
  public int longestPalindromeSubseq2(String s) {
    int N = s.length();

    // dp[i][j] is LPS for s[i...j]
    // dp[i][j] = dp[i+1][j-1] + 2              , if s[i] == s[j]
    //          = max(dp[i+1][j], dp[i][j-1])   , otherwise
    int[][] dp = new int[N][N];
    for (int i = 0; i < N; i++) {
      dp[i][i] = 1;
    }

    for (int len = 2; len <= N; len++) {
      for (int left = 0; left + len - 1 < N; left++) {
        int right = left + len - 1;
        int res = 2 + (left + 1 <= right - 1 ? dp[left + 1][right - 1] : 0);
        if (s.charAt(left) != s.charAt(right)) {
          res = Math.max(dp[left + 1][right], dp[left][right - 1]);
        }
        dp[left][right] = res;
      }
    }

    return dp[0][N - 1];
  }

}

// https://leetcode.com/problems/delete-operation-for-two-strings/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/14/17.
 */
public class DeleteOperationForTwoStrings {

  // DP, O(MN) time, O(min(M, N)) space
  public int minDistance(String word1, String word2) {
    int M = word1.length();
    int N = word2.length();
    if (M < N) {
      return minDistance(word2, word1);
    }

    // make sure M >= N

    // dp[i][j]: minimum number of steps to make word1[0...i-1] and word2[0...j-1] the same
    // dp[i][j] =
    //      dp[i-1][j-1], if (word1[i] == word2[j])
    //      min(dp[i-1][j], dp[i][j-1]) + 1, otherwise
    int[] dp = new int[N + 1];
    for (int j = 0; j <= N; j++) {
      dp[j] = j;
    }
    for (int i = 1; i <= M; i++) {
      int[] newDp = new int[N + 1];
      newDp[0] = i; // critical!

      for (int j = 1; j <= N; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          newDp[j] = dp[j - 1];
        } else {
          newDp[j] = Math.min(dp[j], newDp[j - 1]) + 1;
        }
      }
      dp = newDp;
    }

    return dp[N];
  }

  // DP, O(MN) time, O(MN) space
  public int minDistance2(String word1, String word2) {
    int M = word1.length();
    int N = word2.length();
    // dp[i][j]: minimum number of steps to make word1[0...i-1] and word2[0...j-1] the same
    // dp[i][j] =
    //      dp[i-1][j-1], if (word1[i] == word2[j])
    //      min(dp[i-1][j], dp[i][j-1]) + 1, otherwise
    int[][] dp = new int[M + 1][N + 1];
    for (int i = 0; i < M + 1; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j < N + 1; j++) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= N; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
        }
      }
    }

    return dp[M][N];
  }
}

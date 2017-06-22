// https://leetcode.com/problems/ones-and-zeroes/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/22/17.
 */
public class OnesAndZeroes {

  // O(Nmn) time, O(mn) space
  public int findMaxForm(String[] strs, int m, int n) {
    int N = strs.length;
    int[] strs0 = new int[N];
    int[] strs1 = new int[N];
    for (int i = 0; i < N; i++) {
      String str = strs[i];
      for (int j = 0; j < str.length(); j++) {
        if (str.charAt(j) == '0') {
          strs0[i]++;
        } else {
          strs1[i]++;
        }
      }
    }

    // dp[i][j][k] is the max number of strings in strs[0...i] that can be formed using j 0s and k 1s.
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i < N; i++) {
      int[][] newDp = new int[m + 1][n + 1];
      for (int j = 0; j <= m; j++) {
        for (int k = 0; k <= n; k++) {
          // form strs[i]
          if (j >= strs0[i] && k >= strs1[i]) {
            newDp[j][k] = 1;
          }
          if ((i - 1 >= 0) && (j - strs0[i] >= 0) && (k - strs1[i] >= 0)) {
            newDp[j][k] += dp[j - strs0[i]][k - strs1[i]];
          }

          // do not form strs[i]
          if (i - 1 >= 0) {
            newDp[j][k] = Math.max(newDp[j][k], dp[j][k]);
          }
        }
      }
      dp = newDp;
    }

    return dp[m][n];
  }

  // O(Nmn) time, O(Nmn) space
  public int findMaxForm2(String[] strs, int m, int n) {
    int N = strs.length;
    int[] strs0 = new int[N];
    int[] strs1 = new int[N];
    for (int i = 0; i < N; i++) {
      String str = strs[i];
      for (int j = 0; j < str.length(); j++) {
        if (str.charAt(j) == '0') {
          strs0[i]++;
        } else {
          strs1[i]++;
        }
      }
    }

    // dp[i][j][k] is the max number of strings in strs[0...i] that can be formed using j 0s and k 1s.
    int[][][] dp = new int[N][m + 1][n + 1];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <= m; j++) {
        for (int k = 0; k <= n; k++) {
          // form strs[i]
          if (j >= strs0[i] && k >= strs1[i]) {
            dp[i][j][k] = 1;
          }
          if ((i - 1 >= 0) && (j - strs0[i] >= 0) && (k - strs1[i] >= 0)) {
            dp[i][j][k] += dp[i - 1][j - strs0[i]][k - strs1[i]];
          }

          // do not form strs[i]
          if (i - 1 >= 0) {
            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);
          }
        }
      }
    }

    return dp[N - 1][m][n];
  }
}

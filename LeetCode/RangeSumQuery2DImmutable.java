// https://leetcode.com/problems/range-sum-query-2d-immutable/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 7/8/17.
 */
public class RangeSumQuery2DImmutable {

  private int[][] sum;

  public RangeSumQuery2DImmutable(int[][] matrix) {
    int M = matrix.length;
    int N = M == 0 ? 0 : matrix[0].length;
    if (M == 0 || N == 0) {
      return;
    }

    // sum[i][j] = matrix[0][0] + ... + matrix[i][j]
    sum = new int[M][N];
    sum[0][0] = matrix[0][0];
    for (int i = 1; i < M; i++) {
      sum[i][0] = matrix[i][0] + sum[i - 1][0];
    }
    for (int j = 1; j < N; j++) {
      sum[0][j] = matrix[0][j] + sum[0][j - 1];
    }

    for (int i = 1; i < M; i++) {
      for (int j = 1; j < N; j++) {
        sum[i][j] = matrix[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    if (sum == null) {
      return 0;
    }

    int res = sum[row2][col2];
    if (row1 > 0) {
      res -= sum[row1 - 1][col2];
    }
    if (col1 > 0) {
      res -= sum[row2][col1 - 1];
    }
    if (row1 > 0 && col1 > 0) {
      res += sum[row1 - 1][col1 - 1];
    }

    return res;
  }
}

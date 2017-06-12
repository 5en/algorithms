// https://leetcode.com/problems/n-queens/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by htyleo on 6/12/17.
 */
public class NQueens {

  public List<List<String>> solveNQueens(int n) {
    List<List<String>> res = new ArrayList<>();
    int[] qs = new int[n];
    Arrays.fill(qs, -1);
    find(res, qs, 0);
    return res;
  }

  // row: current row
  // n: number of queens left
  private void find(List<List<String>> res, int[] qs, int row) {
    int N = qs.length;
    if (row == N) {
      List<String> r = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        char[] resRow = new char[N];
        for (int j = 0; j < N; j++) {
          resRow[j] = (qs[i] == j ? 'Q' : '.');
        }
        r.add(String.valueOf(resRow));
      }
      res.add(r);
      return;
    }

    for (int col = 0; col < N; col++) {
      // try to put queen at (row, col)
      boolean valid = true;
      for (int r = 0; r < row; r++) {
        int c = qs[r];
        // check if (r, c) is in conflict with (row, col)
        if (c != -1 && !isValid(row, col, r, c)) {
          valid = false;
          break;
        }
      }
      if (valid) {
        qs[row] = col;
        find(res, qs, row + 1);
        qs[row] = -1;
      }
    }
  }

  private boolean isValid(int row1, int col1, int row2, int col2) {
    return row1 != row2 && col1 != col2 && (Math.abs(row1 - row2) != Math.abs(col1 - col2));
  }
}

// https://leetcode.com/problems/diagonal-traverse/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/11/17.
 */
public class DiagonalTraverse {

  public int[] findDiagonalOrder(int[][] matrix) {
    int NUM_ROWS = matrix.length;
    if (NUM_ROWS == 0) {
      return new int[0];
    }
    int NUM_COLS = matrix[0].length;
    int[] res = new int[NUM_ROWS * NUM_COLS];
    boolean up = true;
    for (int i = 0, row = 0, col = 0; i < res.length; i++) {
      res[i] = matrix[row][col];

      if (up) {
        if (row - 1 >= 0 && col + 1 < NUM_COLS) {
          row--;
          col++;
        } else if (col + 1 < NUM_COLS) {
          col++;
          up = false;
        } else {
          row++;
          up = false;
        }
      } else {
        if (row + 1 < NUM_ROWS && col - 1 >= 0) {
          row++;
          col--;
        } else if (row + 1 < NUM_ROWS) {
          row++;
          up = true;
        } else {
          col++;
          up = true;
        }
      }
    }

    return res;
  }

}

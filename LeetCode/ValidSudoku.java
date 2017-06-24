// https://leetcode.com/problems/valid-sudoku/#/description

package com.htyleo.algorithms;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by htyleo on 6/24/17.
 */
public class ValidSudoku {

  public boolean isValidSudoku(char[][] board) {
    // check row
    for (int row = 0; row < 9; row++) {
      Set<Character> used = new HashSet<>();
      for (int col = 0; col < 9; col++) {
        if (board[row][col] == '.') {
          continue;
        }
        if (used.contains(board[row][col])) {
          return false;
        }
        used.add(board[row][col]);
      }
    }

    // check col
    for (int col = 0; col < 9; col++) {
      Set<Character> used = new HashSet<>();
      for (int row = 0; row < 9; row++) {
        if (board[row][col] == '.') {
          continue;
        }
        if (used.contains(board[row][col])) {
          return false;
        }
        used.add(board[row][col]);
      }
    }

    // check square
    for (int i = 0; i <= 6; i += 3) {
      for (int j = 0; j <= 6; j += 3) {
        Set<Character> used = new HashSet<>();
        for (int rinc = 0; rinc < 3; rinc++) {
          for (int cinc = 0; cinc < 3; cinc++) {
            if (board[i + rinc][j + cinc] == '.') {
              continue;
            }
            if (used.contains(board[i + rinc][j + cinc])) {
              return false;
            }
            used.add(board[i + rinc][j + cinc]);
          }
        }
      }
    }

    return true;
  }
}

// https://leetcode.com/problems/minesweeper/#/description

package com.htyleo.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by htyleo on 6/6/17.
 */
public class Minesweeper {

  public char[][] updateBoard(char[][] board, int[] click) {
    int NUM_ROWS = board.length;
    int NUM_COLS = board[0].length;

    if (board[click[0]][click[1]] == 'M') {
      board[click[0]][click[1]] = 'X';
      return board;
    }

    // bfs
    Queue<int[]> q = new LinkedList<>();
    q.add(click);
    while (!q.isEmpty()) {
      int[] pos = q.remove();
      int row = pos[0];
      int col = pos[1];

      // check its mines
      // if it has neighboring mines, stop
      int numMines = calMines(board, row, col);
      if (numMines > 0) {
        board[row][col] = (char) ('0' + numMines);
        continue;
      }

      // if blank, check its neighbors
      board[row][col] = 'B';
      for (int rowInc = -1; rowInc <= 1; rowInc++) {
        for (int colInc = -1; colInc <= 1; colInc++) {
          if (rowInc == 0 && colInc == 0) {
            continue;
          }
          int nrow = row + rowInc;
          int ncol = col + colInc;
          if (nrow < 0 || nrow >= NUM_ROWS || ncol < 0 || ncol >= NUM_COLS) {
            continue;
          }

          if (board[nrow][ncol] == 'E') {
            q.add(new int[]{nrow, ncol});
            board[nrow][ncol] = 'B'; // avoid to be added again
          }
        }
      }
    }

    return board;
  }

  private int calMines(char[][] board, int row, int col) {
    int numMines = 0;

    for (int rowInc = -1; rowInc <= 1; rowInc++) {
      for (int colInc = -1; colInc <= 1; colInc++) {
        if (rowInc == 0 && colInc == 0) {
          continue;
        }
        int nrow = row + rowInc;
        int ncol = col + colInc;
        if (nrow < 0 || nrow >= board.length || ncol < 0 || ncol >= board[0].length) {
          continue;
        }
        if (board[nrow][ncol] == 'M') {
          numMines++;
        }
      }
    }

    return numMines;
  }
}

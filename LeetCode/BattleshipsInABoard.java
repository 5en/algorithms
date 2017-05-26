// https://leetcode.com/problems/battleships-in-a-board/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 5/26/17.
 */
public class BattleshipsInABoard {

  public int countBattleships(char[][] board) {
    int cnt = 0;
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        if (board[row][col] != 'X') {
          continue;
        }
        if (col > 0 && board[row][col - 1] == 'X') {
          continue;
        }
        if (row > 0 && board[row - 1][col] == 'X') {
          continue;
        }
        cnt++;
      }
    }

    return cnt;
  }

}

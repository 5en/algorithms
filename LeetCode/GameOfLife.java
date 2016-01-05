// https://leetcode.com/problems/game-of-life/

package com.htyleo.algorithms;

public class GameOfLife {
    // 0: dead, 1: live
    // <next><now>
    public void gameOfLife(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int state = board[row][col] & 1;
                int count = numLiveNeighbors(board, row, col);
                if (state == 1) {
                    if (count < 2 || count > 3) {
                        board[row][col] = 1; // live -> die: 01
                    } else {
                        board[row][col] = 3; // live -> live: 11
                    }
                } else if (state == 0) {
                    if (count == 3) {
                        board[row][col] = 2; // die -> live: 10
                    }
                }
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = board[row][col] >> 1;
            }
        }
    }

    private int numLiveNeighbors(int[][] board, int row, int col) {
        int count = 0;

        for (int rowInc = -1; rowInc <= 1; rowInc++) {
            for (int colInc = -1; colInc <= 1; colInc++) {
                if (rowInc == 0 && colInc == 0) {
                    continue;
                }

                int newRow = row + rowInc;
                int newCol = col + colInc;
                if (newRow < 0 || newRow >= board.length) {
                    continue;
                }
                if (newCol < 0 || newCol >= board[0].length) {
                    continue;
                }

                if ((board[newRow][newCol] & 1) == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}

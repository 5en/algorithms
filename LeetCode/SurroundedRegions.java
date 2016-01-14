// https://leetcode.com/problems/surrounded-regions/

package com.htyleo.algorithms;

public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }

        int M = board.length;
        int N = board[0].length;
        for (int row = 0; row < M; row++) {
            dfs(board, row, 0);
            dfs(board, row, N - 1);
        }
        for (int col = 0; col < N; col++) {
            dfs(board, 0, col);
            dfs(board, M - 1, col);
        }

        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                } else if (board[row][col] == '*') {
                    board[row][col] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        if (board[row][col] == 'O') {
            board[row][col] = '*';
            if (row - 1 > 0) {
                dfs(board, row - 1, col);
            }
            if (row + 1 < board.length - 1) {
                dfs(board, row + 1, col);
            }
            if (col - 1 > 0) {
                dfs(board, row, col - 1);
            }
            if (col + 1 < board[0].length - 1) {
                dfs(board, row, col + 1);
            }
        }
    }
}

// https://oj.leetcode.com/problems/word-search/
// Given a 2D board and a word, find if the word exists in the grid.
// The word can be constructed from letters of sequentially adjacent cell,
// where "adjacent" cells are those horizontally or vertically neighboring.
// The same letter cell may not be used more than once.

package dfs;

public class WordSearch {
    public static void main(String[] args) {
        // ABCE
        // SFCS
        // ADEE
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "ABCCED"));
        System.out.println(exist(board, "SEE"));
        System.out.println(exist(board, "ABCB"));
    }

    public static boolean exist(char[][] board, String word) {
        int M = board.length;
        int N = board[0].length;
        boolean[][] visited = new boolean[M][N];
        
        for (int x = 0; x < M; x++) {
            for (int y = 0; y < N; y++) {
                if (board[x][y] == word.charAt(0) && search(board, word, 0, x, y, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    private static boolean search (char[][] board, String word, int idx, int x, int y, boolean[][] visited) {
        if (idx == word.length()) {
            return true;
        }
        
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        
        if (!visited[x][y] && board[x][y] == word.charAt(idx)) {
            visited[x][y] = true;
            if (search(board, word, idx+1, x+1, y, visited)) {
                return true;
            }
            if (search(board, word, idx+1, x-1, y, visited)) {
                return true;
            }
            if (search(board, word, idx+1, x, y+1, visited)) {
                return true;
            }
            if (search(board, word, idx+1, x, y-1, visited)) {
                return true;
            }
            visited[x][y] = false; // back track
        }
        
        return false;
    }
}

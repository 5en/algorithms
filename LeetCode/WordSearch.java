// https://oj.leetcode.com/problems/word-search/

public class WordSearch {
    public static void main(String[] args) {
        // ABCE
        // SFCS
        // ADEE
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "ABCCED")); // true
        System.out.println(exist(board, "SEE")); // true
        System.out.println(exist(board, "ABCB")); // false
    }

    public static boolean exist(char[][] board, String word) {
        int M = board.length;
        int N = board[0].length;
        
        for (int x = 0; x < M; x++) {
            for (int y = 0; y < N; y++) {
                if (search(board, word, 0, x, y)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    private static boolean search (char[][] board, String word, int idx, int x, int y) {
        if (idx == word.length()) {
            return true;
        }
        
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == ' ' || board[x][y] != word.charAt(idx)) {
            return false;
        }
        
        char tmp = board[x][y];
        board[x][y] = ' ';
        if (search(board, word, idx+1, x+1, y) || 
                search(board, word, idx+1, x-1, y) ||
                search(board, word, idx+1, x, y+1) ||
                search(board, word, idx+1, x, y-1)) {
            board[x][y] = tmp; // back track
            return true;
        }
        board[x][y] = tmp; // back track
        
        return false;
    }
}

// https://leetcode.com/problems/number-of-islands/

public class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (dfs(grid, row, col)) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean dfs(char[][] grid, int row, int col) {
        if (grid[row][col] != '1') {
            return false;
        }

        grid[row][col] = '0';
        if (row - 1 >= 0) {
            dfs(grid, row-1, col);
        }
        if (row + 1 < grid.length) {
            dfs(grid, row+1, col);
        }
        if (col - 1 >= 0) {
            dfs(grid, row, col-1);
        }
        if (col + 1 < grid[0].length) {
            dfs(grid, row, col+1);
        }

        return true;
    }
}

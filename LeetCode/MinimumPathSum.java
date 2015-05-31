// https://leetcode.com/problems/minimum-path-sum/

public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = {{1, 2}, {1, 1}};
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                int upper = row - 1 >= 0 ? grid[row-1][col] : -1;
                int left = col - 1 >= 0 ? grid[row][col-1] : -1;
                if (upper != -1 && left != -1) {
                    grid[row][col] += Math.min(upper, left);
                } else if (upper == -1 && left != -1) {
                    grid[row][col] += left;
                } else if (upper != -1 && left == -1) {
                    grid[row][col] += upper;
                }
            }
        }
        
        return grid[M-1][N-1];
    }
}

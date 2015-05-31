// https://leetcode.com/problems/unique-paths/
// https://leetcode.com/problems/unique-paths-ii/

public class UniquePaths {
    // O(n) space
    public static int uniquePaths(int m, int n) {
        int[] posbs = new int[n];
        
        // first row
        for (int col = 0; col < n; col++) {
            posbs[col] = 1;
        }
        
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                posbs[col] += posbs[col-1]; // left + upper
            }
        }
        
        return posbs[n-1];
    }
    
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[] posbs = new int[n];
        
        // first row
        posbs[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int col = 1; col < n; col++) {
            posbs[col] = obstacleGrid[0][col] == 1 ? 0 : posbs[col-1];
        }
        
        for (int row = 1; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (obstacleGrid[row][col] == 1) {
                    posbs[col] = 0;
                    continue;
                }
                
                if (col - 1 >= 0) {
                    posbs[col] += posbs[col-1];
                }
            }
        }
        
        return posbs[n-1];
    }
}

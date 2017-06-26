// https://leetcode.com/problems/pacific-atlantic-water-flow/#/description

package com.htyleo.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by htyleo on 6/26/17.
 */
public class PacificAtlanticWaterFlow {

  // Let the Pacific/Alantic water flow into the cells with higher/equal height
  public List<int[]> pacificAtlantic(int[][] matrix) {
    int R = matrix.length;
    int C = R == 0 ? 0 : matrix[0].length;
    List<int[]> res = new ArrayList<>();

    Queue<int[]> q = new ArrayDeque<>();

    // First flow Pacific water toward bottom-right
    int[][] memoPac = new int[R][C];
    // first row and first col
    for (int c = 0; c < C; c++) {
      memoPac[0][c] = -1;
      q.add(new int[]{0, c});
    }
    for (int r = 1; r < R; r++) {
      memoPac[r][0] = -1;
      q.add(new int[]{r, 0});
    }
    bfs(matrix, q, memoPac);

    // Next, flow Atlantic water into cells;
    int[][] memoAtl = new int[R][C];
    // last row and last col
    for (int c = 0; c < C; c++) {
      memoAtl[R - 1][c] = -1;
      q.add(new int[]{R - 1, c});
    }
    for (int r = 0; r < R - 1; r++) {
      memoAtl[r][C - 1] = -1;
      q.add(new int[]{r, C - 1});
    }
    bfs(matrix, q, memoAtl);

    // find common items
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (memoPac[r][c] == 1 && memoAtl[r][c] == 1) {
          res.add(new int[]{r, c});
        }
      }
    }

    return res;
  }

  // memo[r][c]:
  //  -1: in-progress
  //   1: visited
  private void bfs(int[][] matrix, Queue<int[]> q, int[][] memo) {
    int R = matrix.length;
    int C = R == 0 ? 0 : matrix[0].length;

    while (!q.isEmpty()) {
      int[] curr = q.remove();
      int r = curr[0];
      int c = curr[1];
      memo[r][c] = 1;

      for (int[] nb : new int[][]{{r - 1, c}, {r + 1, c}, {r, c - 1}, {r, c + 1}}) {
        int nr = nb[0];
        int nc = nb[1];
        // flow only to higher/equal height
        if (nr < 0 || nr >= R || nc < 0 || nc >= C || memo[nr][nc] != 0
            || matrix[r][c] > matrix[nr][nc]) {
          continue;
        }
        memo[nr][nc] = -1;
        q.add(nb);
      }
    }
  }
}

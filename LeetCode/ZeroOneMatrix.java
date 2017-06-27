// https://leetcode.com/problems/01-matrix/#/description

package com.htyleo.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by htyleo on 6/27/17.
 */
public class ZeroOneMatrix {

  public int[][] updateMatrix(int[][] matrix) {
    int R = matrix.length;
    int C = matrix[0].length;

    Queue<int[]> q = new LinkedList<>();
    int[][] res = new int[R][C];
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (matrix[r][c] == 0) {
          q.add(new int[]{r, c});
        } else {
          res[r][c] = Integer.MAX_VALUE;
        }
      }
    }

    while (!q.isEmpty()) {
      int[] curr = q.remove();
      int r = curr[0];
      int c = curr[1];

      for (int[] inc : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
        int nr = r + inc[0];
        int nc = c + inc[1];
        if (nr < 0 || nr >= R || nc < 0 || nc >= C || res[nr][nc] <= res[r][c] + 1) {
          continue;
        }
        res[nr][nc] = res[r][c] + 1;
        q.add(new int[]{nr, nc});
      }
    }

    return res;
  }

}

// https://leetcode.com/problems/friend-circles/#/description

package com.htyleo.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by htyleo on 6/7/17.
 */
public class FriendCircles {

  public int findCircleNum(int[][] M) {
    int res = 0;
    int N = M.length;
    for (int n = 0; n < N; n++) {
      if (M[n][n] == -1) {
        continue;
      }

      Queue<Integer> friends = new LinkedList<>();
      friends.add(n);
      while (!friends.isEmpty()) {
        int i = friends.remove();
        if (M[i][i] == -1) {
          continue;
        }
        M[i][i] = -1;

        for (int j = 0; j < N; j++) {
          if (M[i][j] == 1) {
            friends.add(j);
          }
        }
      }

      res++;
    }

    return res;
  }

}

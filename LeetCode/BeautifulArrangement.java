// https://leetcode.com/problems/beautiful-arrangement/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 5/30/17.
 */
public class BeautifulArrangement {

  int count = 0;

  public int countArrangement(int N) {
    count = 0;
    // visited[i] indicates whether num i has been used
    boolean[] visited = new boolean[N + 1];
    cal(N, visited, 1);
    return count;
  }

  // backtrack
  private void cal(int N, boolean[] visited, int pos) {
    if (pos == N + 1) {
      count++;
      return;
    }

    for (int i = 1; i <= N; i++) {
      if (visited[i]) {
        continue;
      }

      // check num i at position pos
      if (i % pos == 0 || pos % i == 0) {
        visited[i] = true;
        cal(N, visited, pos + 1);
        visited[i] = false;
      }
    }
  }
  
}

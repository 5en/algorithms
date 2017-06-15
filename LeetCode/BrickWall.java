// https://leetcode.com/problems/brick-wall/#/description

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by htyleo on 6/15/17.
 */
public class BrickWall {

  // find the most frequent edge
  public int leastBricks(List<List<Integer>> wall) {
    Map<Integer, Integer> edgeCnt = new HashMap<>();
    for (List<Integer> row : wall) {
      int edge = 0;
      // ignore the last brick
      for (int i = 0; i < row.size() - 1; i++) {
        int width = row.get(i);
        edge += width;
        edgeCnt.compute(edge, (k, v) -> v == null ? 1 : v + 1);
      }
    }

    int height = wall.size();
    int maxCnt = 0;
    for (int cnt : edgeCnt.values()) {
      maxCnt = Math.max(maxCnt, cnt);
    }

    return height - maxCnt;
  }
}

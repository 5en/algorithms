// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/#/description

package com.htyleo.algorithms;

import java.util.Arrays;

/**
 * Created by htyleo on 6/14/17.
 */
public class MinimumNumberOfArrowsToBurstBalloons {

  // Problem: Given a list of intervals, find the minimum # of partitions such that intervals in each partition are overlapped
  public int findMinArrowShots(int[][] points) {
    int N = points.length;
    if (N == 0) {
      return 0;
    }

    // sort by end, then by start
    Arrays.sort(points, (p1, p2) -> p1[1] <= p2[1] ? -1 : 1);

    int res = 1;
    int[] prev = points[0];
    for (int i = 1; i < N; i++) {
      if (points[i][0] > prev[1]) {
        res++;
        prev = points[i];
      }
    }

    return res;
  }
}

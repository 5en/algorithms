// https://leetcode.com/problems/non-overlapping-intervals/#/description

package com.htyleo.algorithms;

import java.util.Arrays;

/**
 * Created by htyleo on 6/20/17.
 */
public class NonOverlappingIntervals {

  public int eraseOverlapIntervals(Interval[] intervals) {
    if (intervals.length == 0) {
      return 0;
    }

    Arrays.sort(intervals, (i1, i2) -> {
      return i1.end <= i2.end ? -1 : 1;
    });

    int res = 0;
    int prevEnd = intervals[0].end;
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i].start < prevEnd) {
        res++;
      } else {
        prevEnd = intervals[i].end;
      }
    }

    return res;
  }

  private static class Interval {

    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }
}
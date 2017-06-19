// https://leetcode.com/problems/find-right-interval/#/description

package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by htyleo on 6/19/17.
 */
public class FindRightInterval {

  // TreeMap. O(N*logN) time, O(N) space
  public int[] findRightInterval(Interval[] intervals) {
    TreeMap<Integer, Integer> start2index = new TreeMap<>();
    for (int i = 0; i < intervals.length; i++) {
      start2index.put(intervals[i].start, i);
    }

    int[] res = new int[intervals.length];
    for (int i = 0; i < res.length; i++) {
      Map.Entry<Integer, Integer> entry = start2index.ceilingEntry(intervals[i].end);
      res[i] = entry == null ? -1 : entry.getValue();
    }

    return res;
  }

  // Binary search. O(N*logN) time, O(N) space
  public int[] findRightInterval2(Interval[] intervals) {
    int N = intervals.length;

    int[] ends = new int[N];
    // interval -> index
    Map<Interval, Integer> interval2index = new HashMap<>();
    for (int i = 0; i < N; i++) {
      interval2index.put(intervals[i], i);
      ends[i] = intervals[i].end;
    }

    // sort by start point
    Arrays.sort(intervals, (i1, i2) -> i1.start <= i2.start ? -1 : 1);

    int[] res = new int[N];
    for (int i = 0; i < N; i++) {
      Interval interval = binarySearch(intervals, ends[i]);
      res[i] = interval == null ? -1 : interval2index.get(interval);
    }

    return res;
  }

  private Interval binarySearch(Interval[] intervals, int end) {
    int N = intervals.length;
    int low = 0;
    int high = N - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      int start = intervals[mid].start;
      if (start < end) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return low < N ? intervals[low] : null;
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

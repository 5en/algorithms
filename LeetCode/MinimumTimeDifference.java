// https://leetcode.com/problems/minimum-time-difference/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by htyleo on 6/12/17.
 */
public class MinimumTimeDifference {

  public int findMinDifference(List<String> timePoints) {
    List<Integer> minutes = new ArrayList<>(timePoints.size());
    timePoints.forEach(timePoint -> {
      String[] parts = timePoint.split(":");
      int hour = Integer.parseInt(parts[0]);
      int minute = Integer.parseInt(parts[1]);
      minutes.add(60 * hour + minute);
    });

    Collections.sort(minutes);

    int res = Integer.MAX_VALUE;
    for (int i = 0; i < minutes.size(); i++) {
      int diff = i > 0 ? minutes.get(i) - minutes.get(i - 1)
          : minutes.get(i) - minutes.get(minutes.size() - 1) + 1440;
      res = Math.min(res, diff);
    }

    return res;
  }
}

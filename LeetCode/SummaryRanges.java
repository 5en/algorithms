// https://leetcode.com/problems/summary-ranges/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htyleo on 7/1/17.
 */
public class SummaryRanges {

  public List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<>();
    if (nums.length == 0) {
      return res;
    }

    int start = nums[0];
    int prev = start;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != prev + 1) {
        res.add(start == prev ? start + "" : start + "->" + prev);
        start = nums[i];
      }
      prev = nums[i];
    }

    res.add(start == nums[nums.length - 1] ? start + "" : start + "->" + nums[nums.length - 1]);

    return res;
  }
}

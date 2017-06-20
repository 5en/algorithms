// https://leetcode.com/problems/increasing-subsequences/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by htyleo on 6/20/17.
 */
public class IncreasingSubsequences {

  public List<List<Integer>> findSubsequences(int[] nums) {
    Set<List<Integer>> res = new HashSet<>();
    find(res, nums, new ArrayList<>(), 0);
    return new ArrayList<>(res);
  }

  private void find(Set<List<Integer>> res, int[] nums, List<Integer> curr, int i) {
    if (i == nums.length) {
      return;
    }

    find(res, nums, curr, i + 1);

    if (curr.isEmpty() || nums[i] >= curr.get(curr.size() - 1)) {
      curr.add(nums[i]);
      if (curr.size() >= 2) {
        res.add(new ArrayList<>(curr));
      }
      find(res, nums, curr, i + 1);
      curr.remove(curr.size() - 1);
    }
  }
}

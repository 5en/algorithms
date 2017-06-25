// https://leetcode.com/problems/matchsticks-to-square/#/description

package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by htyleo on 6/25/17.
 */
public class MatchsticksToSquare {

  public boolean makesquare(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if (sum == 0 || sum % 4 != 0) {
      return false;
    }

    Arrays.sort(nums);
    // reverse
    for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
      int tmp = nums[l];
      nums[l] = nums[r];
      nums[r] = tmp;
    }

    return dfs(nums, 0, new int[4], sum / 4);
  }

  private boolean dfs(int[] nums, int curr, int[] lens, int target) {
    if (curr == nums.length) {
      return lens[0] == target && lens[1] == target && lens[2] == target;
    }

    Set<Integer> used = new HashSet<Integer>();
    for (int i = 0; i < lens.length; i++) {
      // try to put nums[curr] to lens[i]
      if (!used.contains(lens[i]) && nums[curr] + lens[i] <= target) {
        used.add(lens[i]);
        lens[i] += nums[curr];

        if (dfs(nums, curr + 1, lens, target)) {
          return true;
        }

        // backtrack
        lens[i] -= nums[curr];
      }
    }

    return false;
  }
}

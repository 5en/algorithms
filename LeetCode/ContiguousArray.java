// https://leetcode.com/problems/contiguous-array/#/description

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by htyleo on 6/22/17.
 */
public class ContiguousArray {

  // prefix sum, O(N) time, O(N) space
  public int findMaxLength(int[] nums) {
    int N = nums.length;
    int maxLen = 0;

    // cnt2idx: count(nums[0...index]: #0 - #1) -> index
    Map<Integer, Integer> cnt2idx = new HashMap<>();
    cnt2idx.put(0, -1);
    for (int i = 0, prev = 0; i < N; i++) {
      int curr = (nums[i] == 0 ? 1 : -1) + prev;
      prev = curr;

      Integer start = cnt2idx.get(curr);
      if (start != null) {
        // nums[start+1, ..., i]
        maxLen = Math.max(maxLen, i - start);
      }

      // keep the smaller index
      cnt2idx.putIfAbsent(curr, i);
    }

    return maxLen;
  }
}

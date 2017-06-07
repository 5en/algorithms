// https://leetcode.com/problems/array-nesting/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/7/17.
 */
public class ArrayNesting {

  // O(N) time, O(1) space
  public int arrayNesting(int[] nums) {
    int maxLen = 0;
    for (int start = 0; start < nums.length; start++) {
      if (nums[start] == -1) {
        continue;
      }

      int len = 1;
      for (int next = nums[start]; nums[start] != nums[next]; ) {
        len++;
        int tmp = nums[next];
        nums[next] = -1;
        next = tmp;
      }
      maxLen = Math.max(len, maxLen);
    }

    return maxLen;
  }
}

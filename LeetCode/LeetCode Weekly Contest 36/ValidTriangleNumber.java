// https://leetcode.com/contest/leetcode-weekly-contest-36/problems/valid-triangle-number/

package com.htyleo.algorithms;

import java.util.Arrays;

/**
 * Created by htyleo on 6/11/17.
 */
public class ValidTriangleNumber {

  public int triangleNumber(int[] nums) {
    int res = 0;

    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      for (int j = i + 1; j < nums.length - 1; j++) {
        int k = Arrays.binarySearch(nums, j + 1, nums.length, nums[i] + nums[j]);
        if (k < 0) {
          int insertionPoint = -k - 1;
          k = insertionPoint - 1;
        } else {
          while (k > j && nums[i] + nums[j] <= nums[k]) {
            k--;
          }
        }

        if (k > j) {
          res += k - j;
        }
      }
    }

    return res;
  }

}

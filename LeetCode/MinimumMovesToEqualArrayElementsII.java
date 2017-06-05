// https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/#/description

package com.htyleo.algorithms;

import java.util.Arrays;

/**
 * Created by htyleo on 6/5/17.
 */
public class MinimumMovesToEqualArrayElementsII {

  // O(N) time, quick select
  public int minMoves2(int[] nums) {
    // find median
    int median = findNth(nums, (nums.length + 1) / 2, 0, nums.length - 1);
    int move = 0;
    for (int num : nums) {
      move += Math.abs(num - median);
    }

    return move;
  }

  private int findNth(int[] nums, int n, int start, int end) {
    if (end - start <= 1) {
      return n == 1 ? Math.min(nums[start], nums[end]) : Math.max(nums[start], nums[end]);
    }

    int pivotIdx = (start + end) / 2;
    int pivot = nums[pivotIdx];

    swap(nums, pivotIdx, end);

    pivotIdx = start;
    for (int i = start; i <= end - 1; i++) {
      if (nums[i] < pivot) {
        swap(nums, i, pivotIdx);
        pivotIdx++;
      }
    }

    swap(nums, pivotIdx, end);

    if (pivotIdx - start + 1 > n) {
      return findNth(nums, n, start, pivotIdx - 1);
    } else if (pivotIdx - start + 1 < n) {
      return findNth(nums, n - (pivotIdx - start + 1), pivotIdx + 1, end);
    } else {
      return pivot;
    }
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  // O(NlogN) time
  public int minMoves2NlogN(int[] nums) {
    int move = 0;

    Arrays.sort(nums);
    for (int lo = 0, hi = nums.length - 1; lo < hi; lo++, hi--) {
      move += nums[hi] - nums[lo];
    }

    return move;
  }
}

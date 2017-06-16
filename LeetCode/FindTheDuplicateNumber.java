// https://leetcode.com/problems/find-the-duplicate-number/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/16/17.
 */
public class FindTheDuplicateNumber {

  // O(N) time, O(1) space.
  // similar to finding cycle in a linked list
  public int findDuplicate(int[] nums) {
    int slow = nums[0];
    int fast = nums[0];
    while (true) {
      slow = nums[slow];
      fast = nums[nums[fast]];
      if (slow == fast) {
        break;
      }
    }

    // not start from original position and the meet position
    int curr = nums[0];
    while (curr != slow) {
      curr = nums[curr];
      slow = nums[slow];
    }

    return curr;
  }

  // O(N * logN) time, O(1) space
  public int findDuplicate2(int[] nums) {
    int N = nums.length;
    int left = 1;
    int right = N - 1;
    // count # of mid
    // if there is no duplication for numbers <= mid, count <= mid. (1, 2, 3, ... mid). search space: mid + 1 ~ right
    // otherwise, search space: left ~ mid

    while (left < right) {
      int mid = (left + right) / 2;
      int cntLE = 0;
      int cntG = 0;
      for (int num : nums) {
        if (num <= mid) {
          cntLE++;
        } else {
          cntG++;
        }
      }

      if (cntLE <= mid) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }
}

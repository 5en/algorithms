// https://leetcode.com/problems/132-pattern/#/description

package com.htyleo.algorithms;

import java.util.Stack;

/**
 * Created by htyleo on 7/2/17.
 */
public class OneThreeTwoPattern {

  // O(N) time, O(N) space
  public boolean find132pattern(int[] nums) {
    int N = nums.length;
    if (N <= 2) {
      return false;
    }

    // ones[i] is the 1 when nums[i] acts as 3
    // ones[i] = min{nums[0] ... nums[i-1]}
    int[] ones = new int[N];
    int min = nums[0];
    for (int i = 1; i < N - 1; i++) {
      ones[i] = min;
      min = Math.min(min, nums[i]);
    }

    // when nums[i] acts as 3, ones[i] acts as 1, find min{nums[j] | j > i, nums[j] > ones[i]}
    // use a stack
    // when analyzing nums[i] (ones[i] < nums[i]), pop all stack.top <= ones[i], check if stack.pop < nums[i], push nums[i]
    // (since ones[i-1] >= ones[i], the popped numbers will not be used any longer.)

    // use the nums array (after index i) as the stack
    int topIdx = N - 1;
    for (int i = N - 2; i >= 1; i--) {
      while (topIdx < N && nums[topIdx] <= ones[i]) {
        topIdx++;
      }
      if (topIdx < N && ones[i] < nums[topIdx] && nums[topIdx] < nums[i]) {
        return true;
      }
      if (topIdx == N || nums[i] < nums[topIdx]) {
        nums[--topIdx] = nums[i];
      }
    }

    return false;
  }

  // O(N) time, O(N) space
  public boolean find132pattern2(int[] nums) {
    int N = nums.length;
    if (N <= 2) {
      return false;
    }

    // ones[i] is the 1 when nums[i] acts as 3
    // ones[i] = min{nums[0] ... nums[i-1]}
    int[] ones = new int[N];
    int min = nums[0];
    for (int i = 1; i < N - 1; i++) {
      ones[i] = min;
      min = Math.min(min, nums[i]);
    }

    // when nums[i] acts as 3, ones[i] acts as 1, find min{nums[j] | j > i, nums[j] > ones[i]}
    // use a stack
    // when analyzing nums[i] (ones[i] < nums[i]), pop all stack.top <= ones[i], check if stack.pop < nums[i], push nums[i]
    // (since ones[i-1] >= ones[i], the popped numbers will not be used any longer.)
    Stack<Integer> s = new Stack<>();
    s.push(nums[N - 1]);
    for (int i = N - 2; i >= 1; i--) {
      while (!s.isEmpty() && s.peek() <= ones[i]) {
        s.pop();
      }
      if (!s.isEmpty() && ones[i] < s.peek() && s.peek() < nums[i]) {
        return true;
      }
      if (s.isEmpty() || nums[i] < s.peek()) {
        s.push(nums[i]);
      }
    }

    return false;
  }

  // O(N^2) time, O(1) space
  public boolean find132pattern3(int[] nums) {
    if (nums.length <= 2) {
      return false;
    }

    int one = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int three = nums[i];
      if (one >= three) {
        one = three;
        continue;
      }

      for (int j = i + 1; j < nums.length; j++) {
        int two = nums[j];
        if (one < two && three > two) {
          return true;
        }
      }
    }

    return false;
  }
}

// https://leetcode.com/problems/next-greater-element-ii/#/description

package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by htyleo on 6/8/17.
 */
public class NextGreaterElementII {

  // O(N) time
  public int[] nextGreaterElements(int[] nums) {
    int N = nums.length;
    int[] res = new int[N];
    Arrays.fill(res, -1);

    // stack top is the index of largest element on the left
    Stack<Integer> s = new Stack<>();
    for (int i = 0; i < N * 2; i++) {
      while (!s.isEmpty() && nums[s.peek()] < nums[i % N]) {
        res[s.pop()] = nums[i % N];
      }
      s.push(i % N);
    }

    return res;
  }

}

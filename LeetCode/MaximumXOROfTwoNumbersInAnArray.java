// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/#/description

package com.htyleo.algorithms;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by htyleo on 6/13/17.
 */
public class MaximumXOROfTwoNumbersInAnArray {

  public int findMaximumXOR(int[] nums) {
    int max = 0;
    int mask = 0;
    for (int i = 31; i >= 0; i--) {
      // consider the most significant bits
      mask = mask | (1 << i);
      Set<Integer> msbs = new HashSet<>();
      for (int num : nums) {
        msbs.add(num & mask);
      }

      int candidate = max | (1 << i);
      for (int msb : msbs) {
        if (msbs.contains(msb ^ candidate)) {
          max = candidate;
          break;
        }
      }
    }

    return max;
  }
}

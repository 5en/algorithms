// https://leetcode.com/problems/total-hamming-distance/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/10/17.
 */
public class TotalHammingDistance {

  // O(N) time
  public int totalHammingDistance(int[] nums) {
    int res = 0;

    int[] countOnes = new int[32];
    for (int num : nums) {
      for (int i = 0; num != 0; i++) {
        if ((num & 1) == 1) {
          countOnes[i]++;
        }
        num = num >>> 1;
      }
    }

    for (int c : countOnes) {
      res += c * (nums.length - c);
    }

    return res;
  }

}

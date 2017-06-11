// https://leetcode.com/problems/shuffle-an-array/#/description

package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by htyleo on 6/11/17.
 */
public class ShuffleAnArray {

  private int[] nums;
  private int N;
  private Random rand = new Random();

  public ShuffleAnArray(int[] nums) {
    this.nums = nums;
    this.N = nums.length;
  }

  /**
   * Resets the array to its original configuration and return it.
   */
  public int[] reset() {
    return nums;
  }

  /**
   * Returns a random shuffling of the array.
   */
  public int[] shuffle() {
    int[] copy = Arrays.copyOf(nums, N);
    for (int i = 0; i < N - 1; i++) {
      // choose num for index i
      int selected = rand.nextInt(N - i) + i;
      int tmp = copy[i];
      copy[i] = copy[selected];
      copy[selected] = tmp;
    }

    return copy;
  }
}

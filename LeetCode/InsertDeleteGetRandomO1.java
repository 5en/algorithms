// https://leetcode.com/problems/insert-delete-getrandom-o1/#/description

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by htyleo on 6/21/17.
 */
public class InsertDeleteGetRandomO1 {

  private Map<Integer, Integer> idx2val = new HashMap<>();
  private Map<Integer, Integer> val2idx = new HashMap<>();
  private int size = 0;
  private Random rand = new Random();

  /**
   * Initialize your data structure here.
   */
  public InsertDeleteGetRandomO1() {

  }

  /**
   * Inserts a value to the set. Returns true if the set did not already contain the specified
   * element.
   */
  public boolean insert(int val) {
    if (val2idx.containsKey(val)) {
      return false;
    }

    val2idx.put(val, size);
    idx2val.put(size, val);
    size++;
    return true;
  }

  /**
   * Removes a value from the set. Returns true if the set contained the specified element.
   */
  public boolean remove(int val) {
    if (!val2idx.containsKey(val)) {
      return false;
    }

    int idx = val2idx.remove(val);
    idx2val.remove(idx);
    if (idx != size - 1) {
      // change element with index size-1 to idx
      int lastVal = idx2val.remove(size - 1);
      val2idx.put(lastVal, idx);
      idx2val.put(idx, lastVal);
    }
    size--;

    return true;
  }

  /**
   * Get a random element from the set.
   */
  public int getRandom() {
    return idx2val.get(rand.nextInt(size));
  }
}

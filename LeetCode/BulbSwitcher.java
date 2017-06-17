// https://leetcode.com/problems/bulb-switcher/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/17/17.
 */
public class BulbSwitcher {

  public int bulbSwitch(int n) {
    // bulb i is on if it is turned odd number of times
    // bulb i is on if i has an odd number of divisors, which means that i is a square bumber
    // to find number of all square numbers <= n
    return (int) Math.sqrt(n);
  }
}

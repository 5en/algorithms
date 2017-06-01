// https://leetcode.com/problems/teemo-attacking/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/1/17.
 */
public class TeemoAttacking {

  public int findPoisonedDuration(int[] timeSeries, int duration) {
    int res = 0;
    int poisonEnd = -1;
    for (int attack : timeSeries) {
      int nextPoisonEnd = attack + duration - 1;
      if (nextPoisonEnd > poisonEnd) {
        res += (attack > poisonEnd) ? duration : nextPoisonEnd - poisonEnd;
      }
      poisonEnd = nextPoisonEnd;
    }

    return res;
  }
}

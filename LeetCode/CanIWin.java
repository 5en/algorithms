// https://leetcode.com/problems/can-i-win/#/description

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by htyleo on 7/8/17.
 */
public class CanIWin {

  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    int rest = 0;
    int sum = 0;
    for (int i = 1; i <= maxChoosableInteger; i++) {
      rest = (rest << 1) | 1;
      sum += i;
    }

    // if sum{nums} < desiredTotal, no one can win
    if (sum < desiredTotal) {
      return false;
    }

    return helper(new HashMap<>(), maxChoosableInteger, desiredTotal, rest);
  }

  private boolean helper(Map<Integer, Boolean> memo, int maxChoosableInteger, int desiredTotal,
      Integer rest) {
    Boolean win = memo.get(rest);
    if (win != null) {
      return win;
    }

    win = false;

    for (int num = 1, mask = 1; num <= maxChoosableInteger; num++, mask <<= 1) {
      if ((rest & mask) == 0) {
        // number has been used
        continue;
      }

      if (num >= desiredTotal) {
        win = true;
        break;
      }

      // pick num

      // mask = 00000100000
      // mask ^ Integer.MAX_VALUE (011...1) = 01...11111011111
      rest &= (mask ^ Integer.MAX_VALUE);
      boolean otherWin = helper(memo, maxChoosableInteger, desiredTotal - num, rest);
      rest |= mask;

      if (!otherWin) {
        win = true;
        break;
      }
    }

    memo.put(rest, win);
    return win;
  }

}

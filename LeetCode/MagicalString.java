// https://leetcode.com/problems/magical-string/#/description

package com.htyleo.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by htyleo on 6/12/17.
 */
public class MagicalString {

  public int magicalString(int n) {
    if (n == 0) {
      return 0;
    }

    // start from the 4th number
    int res = 1;
    Queue<Integer> q = new LinkedList<>();

    // "freq": the frequency that is to be generated
    int freq = 2;
    // "prev": the previous number
    int prev = 2;
    for (int i = 4; i <= n; freq = q.remove()) {
      if (freq == 1 && prev == 1) {
        q.add(2);
        prev = 2;
        i++;
      } else if (freq == 1 && prev == 2) {
        q.add(1);
        prev = 1;
        i++;
        res++;
      } else if (freq == 2 && prev == 1) {
        q.add(2);
        q.add(2);
        prev = 2;
        i += 2;
      } else if (freq == 2 && prev == 2) {
        q.add(1);
        i++;
        res++;
        if (i > n) {
          break;
        }
        q.add(1);
        prev = 1;
        i++;
        res++;
      }
    }

    return res;
  }
}

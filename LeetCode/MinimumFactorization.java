// https://leetcode.com/problems/minimum-factorization/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 7/1/17.
 */
public class MinimumFactorization {

  public int smallestFactorization(int a) {
    if (a == 1) {
      return 1;
    }

    // factor to 2, 3, 5, 7
    int[] cnt = new int[10];
    for (int i : new int[]{2, 3, 5, 7}) {
      while (a % i == 0) {
        a /= i;
        cnt[i]++;
      }
    }

    if (a != 1) {
      return 0;
    }

    // 2*2*2 -> 8
    cnt[8] = cnt[2] / 3;
    cnt[2] %= 3;

    // 3*3 -> 9
    cnt[9] = cnt[3] / 2;
    cnt[3] %= 2;

    // 2*3 -> 6
    if (cnt[2] > 0 && cnt[3] > 0) {
      cnt[6]++;
      cnt[2]--;
      cnt[3]--;
    }

    // 2*2 -> 4
    cnt[4] = cnt[2] / 2;
    cnt[2] %= 2;

    long res = 0;
    for (int i = 2; i <= 9; i++) {
      while (cnt[i] > 0) {
        res = res * 10 + i;
        cnt[i]--;
      }
    }

    return res > Integer.MAX_VALUE ? 0 : (int) res;
  }
}
